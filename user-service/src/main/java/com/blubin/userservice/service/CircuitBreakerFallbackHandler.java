package com.blubin.userservice.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CircuitBreakerFallbackHandler {
    protected void handleBodilessFallback(Throwable throwable) throws Throwable {
        handleError(throwable);
    }

    protected <T> T handleTypedFallback(Throwable throwable) throws Throwable {
        handleError(throwable);
        return null;
    }

    protected void handleError(Throwable throwable) throws Throwable {
        log.error("Circuit breaker records an error. Detail {}", throwable.getMessage());
        throw throwable;
    }
}
