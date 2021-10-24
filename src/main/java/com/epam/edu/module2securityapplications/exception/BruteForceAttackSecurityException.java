package com.epam.edu.module2securityapplications.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Stop hacking my application!")
public class BruteForceAttackSecurityException extends AuthenticationException {
    
    public BruteForceAttackSecurityException(String msg) {
        super(msg);
    }
}
