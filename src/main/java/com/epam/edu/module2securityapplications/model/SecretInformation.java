package com.epam.edu.module2securityapplications.model;

import java.util.Objects;
import java.util.UUID;

public class SecretInformation {

    private final UUID id;
    private final String encodedPayload;

    public SecretInformation(UUID id, String encodedPayload) {
        this.id = id;
        this.encodedPayload = encodedPayload;
    }

    public UUID getId() {
        return id;
    }

    public String getEncodedPayload() {
        return encodedPayload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecretInformation that = (SecretInformation) o;
        return Objects.equals(id, that.id) && Objects.equals(encodedPayload, that.encodedPayload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, encodedPayload);
    }
}
