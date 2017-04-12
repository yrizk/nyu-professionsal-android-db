package com.rizk.androidclasscontacts;

import android.util.Log;

/**
 * Created by rizk on 4/9/17.
 */

public class Contact {

    private String name;
    private long id;
    private long digits;


    public static Contact create(long id, String name, String digits) {
        Log.d("Contact", "name: " + name + " digits: " + digits);
        Contact contact = new Contact();
        contact.name = name;
        contact.digits = Long.valueOf(digits);
        contact.id = id;
        return contact;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getFormattedDigits() {
        return String.valueOf(digits).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
    }


}
