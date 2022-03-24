#!/usr/bin/env bash
sleep 10
ldapadd -h auth-server -c -D cn=admin,dc=bernardomg,dc=com -w admin -f /ldif_files/openldap-data.ldif