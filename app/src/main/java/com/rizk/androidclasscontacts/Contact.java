package com.rizk.androidclasscontacts;

import java.util.Random;

/**
 * Created by rizk on 4/9/17.
 */

public class Contact {

    private String name;
    private int id;
    private String phoneNumber;
    private static final Random ID_GENERATOR = new Random();

    public static Contact create(String name, String digits) {
        Contact contact = new Contact();
        contact.name = name;
        contact.phoneNumber = digits;
        contact.id = ID_GENERATOR.nextInt();
        return contact;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }



}
