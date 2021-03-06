package com.epam.edu.module2securityapplications.listener;

import com.epam.edu.module2securityapplications.repository.BruteForceProtectionCheckRegistry;
import com.epam.edu.module2securityapplications.util.HttpRequestUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AbstractAuthenticationFailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    private final BruteForceProtectionCheckRegistry registry;

    public AbstractAuthenticationFailureEventListener(BruteForceProtectionCheckRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void onApplicationEvent(@NonNull AbstractAuthenticationFailureEvent event) {
        var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var sourceIp = HttpRequestUtils.extractSourceIp(request);
        
        registry.registerFailedLoginAttempt(sourceIp);
    }
}
