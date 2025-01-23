
package com.bernardomg.example.spring.security.ws.ldap.test.testcontainer;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.MountableFile;

public final class LdapExtension implements Extension, BeforeAllCallback {

    private static final String              LDAP_ROOT      = "dc=bernardomg,dc=com";

    private static final String              PASSWORD       = "1234";

    private static final String              PATTERN        = "uid={0},ou=people";

    @SuppressWarnings("resource")
    private static final GenericContainer<?> LDAP_CONTAINER = new GenericContainer<>("bitnami/openldap:2.6.8")
        .withExposedPorts(389)
        .withEnv("LDAP_ROOT", LDAP_ROOT)
        .withEnv("LDAP_DOMAIN", "example.com")
        .withEnv("LDAP_GROUP", "people")
        .withEnv("LDAP_ADMIN_PASSWORD", "admin")
        .withEnv("LDAP_CONFIG_ADMIN_PASSWORD", "admin")
        .withEnv("LDAP_PORT_NUMBER", "389");

    @Override
    public final void beforeAll(final ExtensionContext context) {
        final String url;

        // Start and setup
        LDAP_CONTAINER.start();
        LDAP_CONTAINER.copyFileToContainer(MountableFile.forClasspathResource("/schema/custom.ldif"),
            "/ldifs/custom.ldif");

        url = "ldap://" + LDAP_CONTAINER.getHost() + ":" + LDAP_CONTAINER.getMappedPort(389);

        // Security properties
        System.setProperty("security.ldap.url", url + "/" + LDAP_ROOT);
        System.setProperty("security.ldap.base", "ou=groups");
        System.setProperty("security.ldap.pattern", PATTERN);

        // Spring LDAP properties
        System.setProperty("spring.ldap.urls", url);
        System.setProperty("spring.ldap.base", LDAP_ROOT);
        System.setProperty("spring.ldap.username", "uid=bmg,ou=people,dc=bernardomg,dc=com");
        System.setProperty("spring.ldap.password", PASSWORD);

        // Spring Security LDAP properties
        System.setProperty("spring.security.ldap.base", "ou=people,dc=bernardomg,dc=com");
        System.setProperty("spring.security.ldap.user-dn-pattern", PATTERN);
        System.setProperty("spring.security.ldap.password-attribute", PASSWORD);
    }

}
