package com.rizk.androidclasscontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by rizk on 4/10/17.
 */

public class ContactsDBController {
    private ContactsDBHelper dbHelper;
    private SQLiteDatabase database;
    private final String[] ALL_COLUMNS = new String[]{ContactsDBHelper.COLUMN_ID,
            ContactsDBHelper.COLUMN_NAME, ContactsDBHelper.COLUMN_PHONE};


    public ContactsDBController(Context context) {
        dbHelper = new ContactsDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public Contact createContact(String name, int phoneNumber) {
        ContentValues values = new ContentValues();
        values.put(ContactsDBHelper.COLUMN_NAME, name);
        values.put(ContactsDBHelper.COLUMN_PHONE, phoneNumber);
        long insertID = database.insert(ContactsDBHelper.TABLE_NAME, null /* only relevant if inserting an empty row.
        in that case, this would be the name of the nullable column so they can insert a NULL for you*/, values);
        Log.d(TAG, "inserting contact. insertId = " + insertID);
        Cursor cursor = database.query(
                ContactsDBHelper.TABLE_NAME /* name of the table */,
                ALL_COLUMNS /* columns of interest */,
                ContactsDBHelper.COLUMN_ID + " = " + insertID /* the SELECT */ ,
                null /*selection args, this is if you use placeholders in the previous parameter */,
                null /*group by*/,
                null/*order by*/,
                null /*limit clause */);
        cursor.moveToFirst();
        Contact firstContact =  convertToContact(cursor);
        cursor.close();
        return firstContact;
    }

    public void deleteContact(Contact contact) {
        long id = contact.getId();
        Log.d(TAG, "deleting contact id: " + id);
        database.delete(ContactsDBHelper.TABLE_NAME, ContactsDBHelper.COLUMN_ID + " = " + id, null /* whereArgs. same thing like selectionArgs.
        If this were printf("hello %s, i have %d dollars") we'd have to provide values for those placeholders, right? this is an array for thoes values.*/);
    }

    public List<Contact> getAllContacts() {
        // this is a probably a good exercise
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = database.query(ContactsDBHelper.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null); // refer to the other call for documentation.
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Contact c = convertToContact(cursor);
            contacts.add(c);
            cursor.moveToNext();
        }
        return contacts;
    }

    private Contact convertToContact(Cursor cursor) {
        // note the order and type that I am extracting here is completely relevant.
        return Contact.create(cursor.getLong(0), cursor.getString(1), cursor.getInt(2));
    }
}
