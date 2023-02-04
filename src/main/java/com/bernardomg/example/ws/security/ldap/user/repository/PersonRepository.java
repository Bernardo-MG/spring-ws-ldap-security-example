
package com.bernardomg.example.ws.security.ldap.user.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;

import com.bernardomg.example.ws.security.ldap.user.mapper.PersonAttributesMapper;
import com.bernardomg.example.ws.security.ldap.user.model.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonRepository {

    private final LdapTemplate ldapTemplate;

    public PersonRepository(final LdapTemplate ldapTemplate) {
        super();

        this.ldapTemplate = ldapTemplate;
    }

    public List findUserByCommonName(final String commonName) {
        final AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass", "person"));
        andFilter.and(new EqualsFilter("cn", commonName));
        return ldapTemplate.search("", andFilter.encode(), new PersonAttributesMapper());
    }

    public List<Person> getAllPersons() {
        final List<Person> persons = new ArrayList<>();
        try {
            final List search = ldapTemplate.search("", "(objectClass=person)", new PersonAttributesMapper());
            persons.addAll(search);
        } catch (final Exception e) {
            System.out.println("Error: " + e);
        }
        log.debug("Users: {}", persons);
        return persons;
    }

}
