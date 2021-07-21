package com.example;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryDataStorageService {
    private final Map<String, String> invalidSessionIdToRedirectionUrl = new ConcurrentHashMap<>();

    public void addRedirectionUrlFor(String sessionId, String redirectionUrl) {
        this.invalidSessionIdToRedirectionUrl.put(sessionId, redirectionUrl);
    }

    public String getRedirectionUrlFor(String sessionId) {
        return this.invalidSessionIdToRedirectionUrl.get(sessionId);
    }
}
