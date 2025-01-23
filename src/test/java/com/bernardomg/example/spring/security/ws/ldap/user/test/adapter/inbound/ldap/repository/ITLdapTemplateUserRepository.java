
package com.bernardomg.example.spring.security.ws.ldap.user.test.adapter.inbound.ldap.repository;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bernardomg.example.spring.security.ws.ldap.test.configuration.annotation.LdapIntegrationTest;
import com.bernardomg.example.spring.security.ws.ldap.user.domain.model.User;
import com.bernardomg.example.spring.security.ws.ldap.user.domain.repository.UserRepository;

@LdapIntegrationTest
@DisplayName("LdapTemplateUserRepository")
public class ITLdapTemplateUserRepository {

    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("When finding all, all the users are returned")
    public void testFindAll() {
        final Collection<User> users;

        // WHEN
        users = repository.findAll();

        // THEN
        Assertions.assertThat(users)
            .as("users")
            .isNotEmpty();
    }

}
