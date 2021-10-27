package com.epam.edu.module2securityapplications.repository;

import com.epam.edu.module2securityapplications.model.SecretInformation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public class SecretInformationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<SecretInformation> rowMapper;

    public SecretInformationRepository(JdbcTemplate jdbcTemplate, RowMapper<SecretInformation> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public void save(SecretInformation secretInformation) {
        jdbcTemplate.update("INSERT INTO secret_information (id, encodedPayload) VALUES (?, ?)", secretInformation.getId(), secretInformation.getEncodedPayload());
    }

    public SecretInformation getById(UUID id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM secret_information WHERE id=?", rowMapper, id.toString());
        } catch (EmptyResultDataAccessException empty) {
            return null;
        }
    }
    
    public void deleteById(UUID id) {
        jdbcTemplate.update("DELETE FROM secret_information WHERE id=?", id.toString());
    }

}
