
package com.bernardomg.example.ws.security.ldap.user.service;

import com.bernardomg.example.ws.security.ldap.user.model.User;

public interface UserService {

    public Iterable<? extends User> getUsers();

}
