package com.intranet.onlineshop.web.interceptors;

import com.intranet.onlineshop.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interceptor class user for setting page titles
 */
@Component
public class TitleInterceptor extends HandlerInterceptorAdapter {

    private final Environment environment;

    @Autowired
    public TitleInterceptor(Environment environment) {
        this.environment = environment;
    }

    /**
     * intercepts the response and sets the page title
     * @see HandlerInterceptorAdapter#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String title = "ShopNow";

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            if (handler instanceof HandlerMethod) {
                PageTitle pageTitle = ((HandlerMethod) handler).getMethodAnnotation(PageTitle.class);

                if (pageTitle != null) {
                    String message = environment.getProperty(pageTitle.value());
                    modelAndView
                            .addObject("title", title + " - " + message);
                }
            }
        }
    }
}
