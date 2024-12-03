
package com.bernardomg.example.spring.security.ws.ldap.test.testcontainer;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.MountableFile;

public final class LdapExtension implements Extension, BeforeAllCallback, AfterAllCallback {

    @SuppressWarnings("resource")
    private static final GenericContainer<?> LDAP_CONTAINER = new GenericContainer<>("bitnami/openldap:2.6.8")
        .withExposedPorts(389)
        .withEnv("LDAP_ROOT", "dc=bernardomg,dc=com")
        .withEnv("LDAP_DOMAIN", "example.com")
        .withEnv("LDAP_GROUP", "people")
        .withEnv("LDAP_ADMIN_PASSWORD", "admin")
        .withEnv("LDAP_CONFIG_ADMIN_PASSWORD", "admin")
        .withEnv("LDAP_PORT_NUMBER", "389");

    @Override
    public final void afterAll(final ExtensionContext context) {
        LDAP_CONTAINER.stop();
    }

    @Override
    public final void beforeAll(final ExtensionContext context) {
        LDAP_CONTAINER.start();
        LDAP_CONTAINER.copyFileToContainer(MountableFile.forClasspathResource("/schema/custom.ldif"),
            "/ldap-db-users.ldif");

        System.setProperty("security.ldap.url",
            "ldap://localhost:" + LDAP_CONTAINER.getMappedPort(389) + "/dc=bernardomg,dc=com");
        System.setProperty("security.ldap.base", "ou=groups");
        System.setProperty("security.ldap.pattern", "uid={0},ou=people");

        // Set LDAP properties to environment
        System.setProperty("spring.ldap.urls", "ldap://localhost:" + LDAP_CONTAINER.getMappedPort(389));
        System.setProperty("spring.ldap.base", "dc=bernardomg,dc=com");
        System.setProperty("spring.ldap.username", "uid=bmg,ou=people,dc=bernardomg,dc=com");
        System.setProperty("spring.ldap.password", "1234");

        System.setProperty("spring.security.ldap.base", "ou=people,dc=bernardomg,dc=com");
        System.setProperty("spring.security.ldap.user-dn-pattern", "uid={0},ou=people");
        System.setProperty("spring.security.ldap.password-attribute", "1234");
    }

}
