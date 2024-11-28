
package com.bernardomg.example.spring.security.ws.ldap.person.domain.repository;

import java.util.Collection;

import com.bernardomg.example.spring.security.ws.ldap.person.domain.model.Person;

public interface PersonRepository {

    public Collection<Person> findAll();

}
