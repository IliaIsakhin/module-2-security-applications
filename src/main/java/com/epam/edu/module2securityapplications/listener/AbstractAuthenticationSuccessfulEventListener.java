package com.epam.edu.module2securityapplications.listener;

import com.epam.edu.module2securityapplications.repository.BruteForceProtectionCheckRegistry;
import com.epam.edu.module2securityapplications.util.HttpRequestUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class AbstractAuthenticationSuccessfulEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final BruteForceProtectionCheckRegistry registry;

    public AbstractAuthenticationSuccessfulEventListener(BruteForceProtectionCheckRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var sourceIp = HttpRequestUtils.extractSourceIp(request);
        
        registry.registerSuccessLoginAttempt(sourceIp);
    }
}
