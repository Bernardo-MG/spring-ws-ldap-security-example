
package com.bernardomg.example.spring.security.ws.ldap.person.adapter.inbound.ldap.repository;

import java.util.Collection;
import java.util.Objects;

import javax.naming.directory.Attributes;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bernardomg.example.spring.security.ws.ldap.person.domain.model.Person;
import com.bernardomg.example.spring.security.ws.ldap.person.domain.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public final class LdapPersonRepository implements PersonRepository {

    private final LdapTemplate ldapTemplate;

    public LdapPersonRepository(final LdapTemplate template) {
        super();

        ldapTemplate = Objects.requireNonNull(template);
    }

    @Override
    public final Collection<Person> findAll() {
        final Collection<Person> persons;
        final LdapQuery          query;

        log.debug("Finding all the persons");

        query = LdapQueryBuilder.query()
            .where("objectclass")
            .is("person");

        persons = ldapTemplate.search(query, (final Attributes attrs) -> {
            final String id      = (String) attrs.get("uid")
                .get();
            final String name    = (String) attrs.get("cn")
                .get();
            final String surname = (String) attrs.get("sn")
                .get();
            return new Person(id, name, surname);
        });

        log.debug("Found all the persons: {}", persons);

        return persons;
    }

}
