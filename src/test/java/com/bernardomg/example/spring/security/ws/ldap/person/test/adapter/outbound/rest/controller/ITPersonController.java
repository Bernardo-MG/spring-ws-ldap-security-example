
package com.bernardomg.example.spring.security.ws.ldap.person.test.adapter.outbound.rest.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.bernardomg.example.spring.security.ws.ldap.test.configuration.annotation.MvcIntegrationTest;

@MvcIntegrationTest
@DisplayName("Person controller")
public class ITPersonController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAccessWithInvalidCredentials() throws Exception {
        mockMvc.perform(get("/person").with(httpBasic("invalid", "password")))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAccessWithValidCredentials() throws Exception {
        mockMvc.perform(get("/person").with(httpBasic("bmg", "1234")))
            .andExpect(status().isOk());
    }

}
