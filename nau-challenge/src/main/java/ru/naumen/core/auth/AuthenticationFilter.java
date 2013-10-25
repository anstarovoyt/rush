package ru.naumen.core.auth;

import static org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext;
import static ru.naumen.core.info.Params.ACCESS_KEY_PARAM;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component("authFilter")
public class AuthenticationFilter implements Filter {

    @Inject
    Authenticator authenticator;

    /**
     * Ошибка аутентификации
     * 
     * @author serce
     * @since 25 окт. 2013 г.
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

            Set<String> notFilter = new HashSet<>();
            notFilter.add("/accessDenied");
            notFilter.add("/index.jsp");
            notFilter.add("/resources/");
            notFilter.add("/complete-register/");

            //RULES
            for (String noFilter : notFilter) {
                if (requestURI.startsWith(noFilter)) {
                    chain.doFilter(httpServletRequest, response);
                    return;
                }
            }
            
            if(requestURI.equals("/")) {
                chain.doFilter(httpServletRequest, response);
                return;
            }

            HttpSession session = httpServletRequest.getSession(false);
            if (session != null) {
                String accessKey = (String) session.getAttribute(ACCESS_KEY_PARAM);
                if (accessKey == null) {

                    accessKey = request.getParameter(ACCESS_KEY_PARAM);
                }
                authorized = authenticator.authByAccessKey(accessKey);
            }
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
