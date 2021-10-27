package com.epam.edu.module2securityapplications.util;

import com.epam.edu.module2securityapplications.model.SecretInformation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class SecretInformationRowMapper implements RowMapper<SecretInformation> {

    @Override
    public SecretInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SecretInformation(UUID.fromString(rs.getString("id")), rs.getString("encodedPayload"));
    }
}
