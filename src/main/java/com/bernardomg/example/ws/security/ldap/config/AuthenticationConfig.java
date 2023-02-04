/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2017-2020 the original author or authors.
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

package com.bernardomg.example.ws.security.ldap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bernardomg.example.ws.security.ldap.security.user.repository.PrivilegeRepository;
import com.bernardomg.example.ws.security.ldap.security.user.repository.UserRepository;
import com.bernardomg.example.ws.security.ldap.security.userdetails.PersistentUserDetailsService;

/**
 * Authentication configuration.
 *
 * @author Bernardo Martínez Garrido
 *
 */
@Configuration
public class AuthenticationConfig {

    public AuthenticationConfig() {
        super();
    }

    @Bean
    public LdapContextSource contextSource(@Value("${spring.ldap.url}") final String url,
            @Value("${spring.ldap.base}") final String base, @Value("${spring.ldap.username}") final String username,
            @Value("${spring.ldap.password}") final String password) {
        final LdapContextSource contextSource = new LdapContextSource();

        contextSource.setUrl(url);
        contextSource.setBase(base);
        contextSource.setUserDn(username);
        contextSource.setPassword(password);

        return contextSource;
    }

    @Bean("passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("userDetailsService")
    public UserDetailsService getUserDetailsService(final UserRepository userRepository,
            final PrivilegeRepository privilegeRepository) {
        return new PersistentUserDetailsService(userRepository, privilegeRepository);
    }

    @Bean
    public LdapTemplate ldapTemplate(final LdapContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }

}
