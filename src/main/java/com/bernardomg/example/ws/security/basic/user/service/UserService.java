
package com.bernardomg.example.ws.security.basic.user.service;

import com.bernardomg.example.ws.security.basic.user.model.User;

public interface UserService {

    public Iterable<? extends User> getUsers();

}
