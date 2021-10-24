package com.epam.edu.module2securityapplications;

import com.epam.edu.module2securityapplications.exception.BruteForceAttackSecurityException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class Module2SecurityApplicationsApplicationTests {

    private static final String ADMIN_LOGIN = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin_pass";
    private static final String USER_LOGIN = "user@gmail.com";
    private static final String USER_PASSWORD = "user_pass";
    private static final String WRONG_PASSWORD = "i`m lucky!";

    @Container
    @SuppressWarnings("rawtypes")
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.0") {{
        withInitScript("database/schema.sql");
    }};

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAdminEndpoint200() throws Exception {
        mockMvc.perform(get("/api/admin").contentType(MediaType.APPLICATION_JSON).with(httpBasic(ADMIN_LOGIN, ADMIN_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().string(is("Hello Admin")));
    }

    @Test
    public void testGetInfoEndpoint200() throws Exception {
        mockMvc.perform(get("/api/info").contentType(MediaType.APPLICATION_JSON).with(httpBasic(USER_LOGIN, USER_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().string(is("MVC APPLICATION")));
    }

    @Test
    public void testGetInfoEndpoint401() throws Exception {
        mockMvc.perform(get("/api/info").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAboutEndpointAlways200() throws Exception {
        mockMvc.perform(get("/api/about").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(is("ABOUT ENDPOINT")));
    }

    @Test
    public void testBruteForceLogins() {
        assertThrows(BruteForceAttackSecurityException.class, () -> {
                    for (int i = 0; i < 4; i++) {
                        mockMvc.perform(get("/api/info")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(httpBasic(ADMIN_LOGIN, WRONG_PASSWORD))
                                .with(differentRemoteHost()));
                    }
                }
        );
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    private static RequestPostProcessor differentRemoteHost() {
        return request -> {
            request.setRemoteAddr("1.2.3.4");
            return request;
        };
    }
}
