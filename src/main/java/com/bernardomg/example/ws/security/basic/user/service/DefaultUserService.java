
package com.bernardomg.example.ws.security.basic.user.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardomg.example.ws.security.basic.user.model.User;

@Service
public final class DefaultUserService implements UserService {

    @Autowired
    public DefaultUserService() {
        super();
    }

    @Override
    public final Iterable<? extends User> getUsers() {
        return Collections.emptyList();
    }

}
