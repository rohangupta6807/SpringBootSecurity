package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class CustomSessionListener implements HttpSessionListener {

    @Autowired
    private InMemoryDataStorageService inMemoryDataStorageService;

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Invalidating session. Id : " + se.getSession().getId());
        inMemoryDataStorageService.addRedirectionUrlFor(se.getSession().getId(), "https://www.google.com/");
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
