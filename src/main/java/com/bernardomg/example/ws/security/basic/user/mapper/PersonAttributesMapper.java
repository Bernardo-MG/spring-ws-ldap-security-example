
package com.bernardomg.example.ws.security.basic.user.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.bernardomg.example.ws.security.basic.user.model.Person;

public class PersonAttributesMapper implements AttributesMapper<Person> {

    @Override
    public Person mapFromAttributes(Attributes attributes)
            throws NamingException {
        Person person = new Person();

        Attribute name = attributes.get("name");
        if (name != null) {
            person.setName((String) name.get());
        }

        Attribute displayname = attributes.get("displayname");
        if (displayname != null) {
            person.setDisplayName((String) displayname.get());
        }

        Attribute lastname = attributes.get("sn");
        if (lastname != null) {
            person.setLastName((String) lastname.get());
        }

        Attribute firstname = attributes.get("givenname");
        if (firstname != null) {
            person.setFirstName((String) firstname.get());
        }

        Attribute mail = attributes.get("mail");
        if (mail != null) {
            person.setMail((String) mail.get());
        }

        Attribute userid = attributes.get("uid");
        if (userid != null) {
            person.setUserID((String) userid.get());
        }

        System.out.println(person.toString());

        return person;
    }

}
