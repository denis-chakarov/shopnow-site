package com.intranet.onlineshop.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Bean class used for implementing a login security handler
 */
@Component
public class CustomLoginSecurityHandler implements AuthenticationSuccessHandler {
    /**
     *
     * @param httpServletRequest the http request
     * @param httpServletResponse the http response
     * @param authentication contains the user authorities
     * @throws IOException when the response cannot redirect
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ROOT")) {
            httpServletResponse.sendRedirect("/admin/register");
        }
        else if(roles.contains("ROLE_CUSTOMER")) {
            httpServletResponse.sendRedirect("/customer/categories");
        }
        else if(roles.contains("ROLE_ORDER_MANAGER")) {
            httpServletResponse.sendRedirect("/manager/orders/confirmed");
        }
        else {
            httpServletResponse.sendRedirect("/seller/requests");
        }
    }
}
