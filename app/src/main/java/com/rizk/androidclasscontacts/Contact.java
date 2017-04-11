package com.rizk.androidclasscontacts;

import android.text.TextUtils;

/**
 * Created by rizk on 4/9/17.
 */

public class Contact {

    private String name;
    private long id;
    private long digits;


    public static Contact create(long id, String name, long digits) {
        Contact contact = new Contact();
        if (id < 0 || TextUtils.isEmpty(name) || digits < 1_000_000_000) {
            throw new IllegalArgumentException("bad arguments for contact");
        }
        contact.name = name;
        contact.digits = digits;
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
