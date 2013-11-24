package ru.naumen.core.auth;

import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.naumen.model.dao.UserDAO;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext;
import static ru.naumen.core.info.Params.ACCESS_KEY_PARAM;

/**
 * Filter for auth
 *
 * @author serce
 * @since 27 oct. 2013 г.
 */
@Component("authFilter")
public class AuthenticationFilter implements Filter {

    @Inject
    Authenticator authenticator;
    @Inject
    UserDAO userDAO;

    //@formatter:off
    private static final Set<String> permittedPages = ImmutableSet.of(
            "/accessDenied",
            "/index.jsp",
            "/resources/",
            "/complete-register/",
            "/login/",
            "/logout/",
            "/favicon.ico"
    );
    //@formatter:on

    /**
     * Auth error
     * 
     * @author serce
     * @since 25 oct. 2013 г.
     */
    public static final class AuthenticationException extends RuntimeException {
        private static final long serialVersionUID = 1728365144281801472L;

        public AuthenticationException(String msg) {
            super(msg);
        }
    }

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        ServletContext servletContext = filterConfig.getServletContext();
        WebApplicationContext webApplicationContext = getWebApplicationContext(servletContext);
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.configureBean(this, "authFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        boolean authorized = false;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String requestURI = httpServletRequest.getRequestURI();

            //RULES
            for (String noFilter : permittedPages) {
                if (requestURI.startsWith(noFilter)) {
                    chain.doFilter(httpServletRequest, response);
                    return;
                }
            }

            if (requestURI.equals("/")) {
                chain.doFilter(httpServletRequest, response);
                return;
            }

            HttpSession session = httpServletRequest.getSession(false);
            String accessKey = request.getParameter(ACCESS_KEY_PARAM);
            if (accessKey == null && session != null) {
                accessKey = (session != null) ? (String) session.getAttribute(ACCESS_KEY_PARAM) : null;
            }
            authorized = authenticator.authByAccessKey(accessKey);
        }

        if (authorized) {
            chain.doFilter(request, response);
        } else if (filterConfig != null) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
            filterConfig.getServletContext().getRequestDispatcher("/accessDenied/").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
