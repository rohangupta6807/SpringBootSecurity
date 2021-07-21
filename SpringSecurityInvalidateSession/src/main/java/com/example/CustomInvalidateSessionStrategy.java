package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomInvalidateSessionStrategy implements InvalidSessionStrategy {

    @Autowired
    private InMemoryDataStorageService storageService;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        System.out.println("Invalid Session Id : " + httpServletRequest.getRequestedSessionId());
        String redirectionUrl = storageService.getRedirectionUrlFor(httpServletRequest.getRequestedSessionId());
        httpServletResponse.sendRedirect(null == redirectionUrl ? "/login" : redirectionUrl);
    }
}
