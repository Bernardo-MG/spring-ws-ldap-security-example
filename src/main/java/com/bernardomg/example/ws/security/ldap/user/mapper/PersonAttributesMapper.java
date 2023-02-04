
package com.bernardomg.example.ws.security.ldap.user.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.bernardomg.example.ws.security.ldap.user.model.Person;

public class PersonAttributesMapper implements AttributesMapper<Person> {

    @Override
    public Person mapFromAttributes(final Attributes attributes) throws NamingException {
        final Person    person = new Person();

        final Attribute name   = attributes.get("name");
        if (name != null) {
            person.setName((String) name.get());
        }

        final Attribute displayname = attributes.get("displayname");
        if (displayname != null) {
            person.setDisplayName((String) displayname.get());
        }

        final Attribute lastname = attributes.get("sn");
        if (lastname != null) {
            person.setLastName((String) lastname.get());
        }

        final Attribute firstname = attributes.get("givenname");
        if (firstname != null) {
            person.setFirstName((String) firstname.get());
        }

        final Attribute mail = attributes.get("mail");
        if (mail != null) {
            person.setMail((String) mail.get());
        }

        final Attribute userid = attributes.get("uid");
        if (userid != null) {
            person.setUserID((String) userid.get());
        }

        System.out.println(person.toString());

        return person;
    }

}
