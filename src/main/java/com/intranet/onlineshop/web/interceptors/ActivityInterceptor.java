package com.intranet.onlineshop.web.interceptors;
import com.intranet.onlineshop.domain.models.service.UserActivityServiceModel;
import com.intranet.onlineshop.service.UserActivityService;
import com.intranet.onlineshop.web.annotations.ActionLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

@Component
@PropertySource("classpath:method.properties")
public class ActivityInterceptor extends HandlerInterceptorAdapter {

    private final UserActivityService userActivityService;
    private final Environment environment;

    @Autowired
    public ActivityInterceptor(UserActivityService userActivityService, Environment environment) {
        this.userActivityService = userActivityService;
        this.environment = environment;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isValidAction = !(request.getRequestURI().contains("/css") || request.getRequestURI().contains("/js"));
        if(request.getUserPrincipal() != null && isValidAction) {
            UserActivityServiceModel model = new UserActivityServiceModel();
            String clientIP = getClientIpAddress(request);
            model.setUserIP(clientIP);
            model.setUrl(request.getRequestURI());
            model.setUsername(request.getUserPrincipal().getName());
            model.setTimeOfActivity(LocalDateTime.now());
            model.setMessage("no message");
            if(handler instanceof HandlerMethod) {
                ActionLogger actionLogger = ((HandlerMethod) handler).getMethodAnnotation(ActionLogger.class);
                if(actionLogger != null) {
                    String message = environment.getProperty(actionLogger.value());
                    model.setMessage(message);
                }
            }

            if(request.isUserInRole("ROLE_SELLER")) {
                model.setUserAuthority("seller");
            } else if(request.isUserInRole("ROLE_CUSTOMER")) {
                model.setUserAuthority("customer");
            }
            else if(request.isUserInRole("ROLE_ROOT")) {
                model.setUserAuthority("admin");
            }
            else {
                //empty until other roles come in use
            }
            userActivityService.createUserActivity(model);
        }
        return true;
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
        }
    }
}

