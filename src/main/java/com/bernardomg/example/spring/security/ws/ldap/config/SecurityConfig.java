/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2022-2024 the original author or authors.
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

package com.bernardomg.example.spring.security.ws.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * Security configuration.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableConfigurationProperties(LdapProperties.class)
@Slf4j
public class SecurityConfig {

    /**
     * LDAP configuration properties.
     */
    @Autowired
    private LdapProperties ldapProperties;

    /**
     * Password encoder for checking against encrypted passwords.
     */
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        log.info("Connecting to LDAP at {}. Pattern {} and search base {}", ldapProperties.getUrl(),
            ldapProperties.getPattern(), ldapProperties.getBase());
        auth.ldapAuthentication()
            .userDnPatterns(ldapProperties.getPattern())
            .groupSearchBase(ldapProperties.getBase())
            .contextSource()
            .url(ldapProperties.getUrl())
            // Check against encrypted password
            .and()
            .passwordCompare()
            // .passwordEncoder(passwordEncoder)
            .passwordAttribute("userPassword");
    }

    /**
     * Password encoder. Used to match the received password to the one securely stored in the DB.
     *
     * @return the password encoder
     */
    @Bean("passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
