package com.epam.edu.module2securityapplications.controller;

import com.epam.edu.module2securityapplications.service.SecretInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/secret")
public class SecretInformationController {

    private final SecretInformationService provider;

    public SecretInformationController(SecretInformationService provider) {
        this.provider = provider;
    }

    @GetMapping
    @RequestMapping("/{secretPath}")
    public ResponseEntity<String> evictSecret(@PathVariable UUID secretPath) {
        var secret = provider.evictSecret(secretPath);
        
        if (secret == null) {
            return notFound().build();
        } else {
            return ok()
                    .body(String.valueOf(secret));
        }
    }

    @PostMapping()
    public ResponseEntity<String> createSecret(@RequestBody String payload) {
        var secretId = provider.createSecretUrl(payload.toCharArray());

        return ok()
                .body(secretId);
    }
}
