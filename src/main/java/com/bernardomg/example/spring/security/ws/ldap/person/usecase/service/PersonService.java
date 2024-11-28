
package com.bernardomg.example.spring.security.ws.ldap.person.usecase.service;

import java.util.Collection;

import com.bernardomg.example.spring.security.ws.ldap.person.domain.model.Person;

public interface PersonService {

    public Collection<Person> getAll();

}
