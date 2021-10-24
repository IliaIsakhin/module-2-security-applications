package com.epam.edu.module2securityapplications.filter;

import com.epam.edu.module2securityapplications.exception.BruteForceAttackSecurityException;
import com.epam.edu.module2securityapplications.service.BruteForceProtectionCheckService;
import com.epam.edu.module2securityapplications.util.HttpRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BruteForceProtectionServletFilter extends OncePerRequestFilter {

    private final BruteForceProtectionCheckService checkService;

    public BruteForceProtectionServletFilter(BruteForceProtectionCheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var sourceIp = HttpRequestUtils.extractSourceIp(request);

        if (checkService.checkFailedLoginAttempt(sourceIp)) {
            throw new BruteForceAttackSecurityException(String.format("Source ip %s is not trusted. Banned for some time", sourceIp));
        }

        filterChain.doFilter(request, response);
    }
}
