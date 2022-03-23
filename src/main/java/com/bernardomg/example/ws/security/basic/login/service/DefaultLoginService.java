
package com.bernardomg.example.ws.security.basic.login.service;

import org.springframework.stereotype.Service;

import com.bernardomg.example.ws.security.basic.login.model.UserForm;

@Service
public final class DefaultLoginService implements LoginService {

    public DefaultLoginService() {
        super();
    }

    @Override
    public final UserForm login(final String username, final String password) {
        return null;
    }

}
