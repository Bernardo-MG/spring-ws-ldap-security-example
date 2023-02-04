
package com.bernardomg.example.ws.security.ldap.security.login.service;

import com.bernardomg.example.ws.security.ldap.security.login.model.UserForm;

public interface LoginService {

    public UserForm login(final String username, final String password);

}
