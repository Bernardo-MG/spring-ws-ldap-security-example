
package com.bernardomg.example.ws.security.basic.login.service;

import com.bernardomg.example.ws.security.basic.login.model.UserForm;

public interface LoginService {

    public UserForm login(final String username, final String password);

}
