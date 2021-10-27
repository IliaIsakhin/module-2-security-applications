package com.epam.edu.module2securityapplications.service;

import com.epam.edu.module2securityapplications.model.SecretInformation;
import com.epam.edu.module2securityapplications.repository.SecretInformationRepository;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.UUID;

@Service
public class SecretInformationService {
    
    private final TextEncryptor secretEncryptor;
    private final SecretInformationRepository repository;
    
    public SecretInformationService(TextEncryptor secretEncryptor, SecretInformationRepository repository) {
        this.secretEncryptor = secretEncryptor;
        this.repository = repository;
    }

    public String createSecretUrl(char[] payload) {
        var encodedSecret = secretEncryptor.encrypt(toHexString(payload));
        var id = UUID.randomUUID();
        var baseUrl = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUriString();
        
        repository.save(new SecretInformation(id, encodedSecret));
        
        return String.format("%s/%s", baseUrl, id);
    }


    public char[] evictSecret(UUID secretId) {
        var information = repository.getById(secretId);
        
        if (information == null) {
            return null;    
        }
        
        repository.deleteById(secretId);
        
        return fromHexString(secretEncryptor.decrypt(information.getEncodedPayload()));
    }


    private String toHexString(char[] payload) {
        return Hex.encodeHexString(String.valueOf(payload).getBytes(Charset.defaultCharset()));
    }
    
    private char[] fromHexString(String str) {
        try {
            return new String(Hex.decodeHex(str), Charset.defaultCharset()).toCharArray();
        } catch (DecoderException ignored) {
            return null;
        }
    }
}
