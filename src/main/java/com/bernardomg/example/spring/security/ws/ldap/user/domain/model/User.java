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

package com.bernardomg.example.spring.security.ws.ldap.user.domain.model;

import java.util.Collection;

import lombok.Builder;

/**
 * User.
 *
 * @param email
 *            user email
 * @param username
 *            user username
 * @param name
 *            user name
 * @param enabled
 *            user enabled flag
 * @param expired
 *            user expired flag
 * @param locked
 *            user locked flag
 * @param passwordExpired
 *            user password expired flag
 * @param privileges
 *            user privileges
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Builder(setterPrefix = "with")
public record User(String email, String username, String name, boolean enabled, boolean expired, boolean locked,
        boolean passwordExpired, Collection<Privilege> privileges) {

}
