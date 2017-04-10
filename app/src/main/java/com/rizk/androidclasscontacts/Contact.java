package com.rizk.androidclasscontacts;

/**
 * Created by rizk on 4/9/17.
 */

public class Contact {

    private String name;
    private long id;
    private int phoneNumber;

    public static Contact create(long id, String name, int digits) {
        Contact contact = new Contact();
        contact.name = name;
        contact.phoneNumber = digits;
        contact.id = id;
        return contact;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
