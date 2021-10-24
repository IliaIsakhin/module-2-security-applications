package com.epam.edu.module2securityapplications.repository;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class BruteForceProtectionCheckRegistry {

    private final ConcurrentMap<String, Integer> failedLoginRegistry = new ConcurrentHashMap<>();
    
    public void registerFailedLoginAttempt(String key) {
        failedLoginRegistry.merge(key, 1, (i1, i2) -> i1 + 1);
    }
    
    public void registerSuccessLoginAttempt(String key) {
        failedLoginRegistry.put(key, 0);
    }
    
    public Integer getFailedLoginAttempts(String key) {
        return failedLoginRegistry.getOrDefault(key, 0);
    }
}
