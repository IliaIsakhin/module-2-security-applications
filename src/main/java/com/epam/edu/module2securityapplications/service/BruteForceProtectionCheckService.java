package com.epam.edu.module2securityapplications.service;

import com.epam.edu.module2securityapplications.repository.BruteForceProtectionCheckRegistry;
import org.springframework.stereotype.Component;

@Component
public class BruteForceProtectionCheckService {

    private static final int MAX_FAILED_LOGIN_ATTEMPTS = 3;
    
    private final BruteForceProtectionCheckRegistry registry;

    public BruteForceProtectionCheckService(BruteForceProtectionCheckRegistry registry) {
        this.registry = registry;
    }

    public boolean checkFailedLoginAttempt(String key) {
        return registry.getFailedLoginAttempts(key) >= MAX_FAILED_LOGIN_ATTEMPTS;
    }
}
