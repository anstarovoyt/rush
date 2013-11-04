package ru.naumen.core;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Access to appcontext and spring beans
 *
 * @author astarovoyt
 *
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T)appContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return appContext.getBean(name, clazz);
    }

    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
