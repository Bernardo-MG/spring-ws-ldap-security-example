
package com.bernardomg.example.spring.security.ws.ldap.person.test.adapter.inbound.ldap.repository.integration;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bernardomg.example.spring.security.ws.ldap.person.domain.model.Person;
import com.bernardomg.example.spring.security.ws.ldap.person.domain.repository.PersonRepository;
import com.bernardomg.example.spring.security.ws.ldap.test.configuration.annotation.LdapIntegrationTest;

@LdapIntegrationTest
@DisplayName("LdapPersonRepository")
public class ITLdapPersonRepository {

    @Autowired
    private PersonRepository repository;

    @Test
    @DisplayName("When finding all, all the people are returned")
    public void testFindAll() {
        final Collection<Person> persons;

        // WHEN
        persons = repository.findAll();

        // THEN
        Assertions.assertThat(persons)
            .as("persons")
            .isNotEmpty();
    }

}
