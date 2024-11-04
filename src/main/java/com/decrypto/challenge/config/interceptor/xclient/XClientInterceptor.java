package com.decrypto.challenge.config.interceptor.xclient;

import com.decrypto.challenge.config.interceptor.xclient.exception.AuthForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class XClientInterceptor implements HandlerInterceptor {

    public static final String X_CLIENT = "x-client";
    public static final String SWAGGER_UI_SECURITY_HEADER = "api_key";

    private final XClientService clientService;

    public XClientInterceptor(XClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {
        this.analyzeSecurityHeader(request, X_CLIENT);
        return true;
    }

    private void analyzeSecurityHeader(final HttpServletRequest request,
                                         final String headerName) {
        if (isNotBlank(request.getHeader(headerName)) ||
                isNotBlank(request.getHeader(headerName.toUpperCase()))) {
            final String xClientToken = request.getHeader(headerName);

            if (clientService.validateXClient(xClientToken)) {
                throw new AuthForbiddenException(String.format("Invalid x-client header %s", xClientToken));
            }
        } else {
            throw new AuthForbiddenException("Invalid x-client header");
        }
    }

}
