
package com.bernardomg.example.ws.security.basic.user.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;

import com.bernardomg.example.ws.security.basic.user.mapper.PersonAttributesMapper;
import com.bernardomg.example.ws.security.basic.user.model.Person;

@Component
public class PersonRepository {

    private LdapTemplate ldapTemplate;

    public PersonRepository(LdapTemplate ldapTemplate) {
        super();

        this.ldapTemplate = ldapTemplate;
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<Person>();
        try {
            List search = ldapTemplate.search("", "(objectClass=person)",
                new PersonAttributesMapper());
            persons.addAll(search);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return persons;
    }

    public List findUserByCommonName(String commonName) {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass", "person"));
        andFilter.and(new EqualsFilter("cn", commonName));
        return ldapTemplate.search("", andFilter.encode(),
            new PersonAttributesMapper());
    }

}
