/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2022-2025 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.spring.security.ws.ldap.user.adapter.inbound.ldap.repository;

import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Repository;

import com.bernardomg.example.spring.security.ws.ldap.user.domain.model.User;
import com.bernardomg.example.spring.security.ws.ldap.user.domain.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JPA implementation of the user repository.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Slf4j
@Repository
@AllArgsConstructor
public final class LdapTemplateUserRepository implements UserRepository {

    private final LdapTemplate ldapTemplate;

    @Override
    public final Collection<User> findAll() {
        final Collection<User> users;
        final LdapQuery        query;

        log.debug("Finding all the users");

        query = LdapQueryBuilder.query()
            .where("objectclass")
            .is("person");

        users = ldapTemplate.search(query, this::toUser);

        log.debug("Found all the users: {}", users);

        return users;
    }

    private final User toUser(final Attributes attributes) throws NamingException {
        final String id;
        final String name;

        id = (String) attributes.get("uid")
            .get();
        name = (String) attributes.get("cn")
            .get();
        return new User("", id, name, true, false, false, false, List.of());
    }

}
