package com.rizk.androidclasscontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by rizk on 4/10/17.
 */

public class ContactsDBHelper extends SQLiteOpenHelper {


    public static final String CONTACTS_TABLE = "contacts";
    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String[] NAMES = new String[]{"Abed Nadeer", "Troy Barnes", "Britta Perry", "Shirley", "Jeff", "Pierce"};
    private static final String[] PHONE_NUMBERS = new String[NAMES.length];

    // initialize phone numbers; just 10 digit long random numbers.
    static {
        for (int i = 0; i< PHONE_NUMBERS.length; i++) {
            PHONE_NUMBERS[i] = "" + (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        }
    }

    private final static String CREATE_STATEMENT = "CREATE TABLE " + CONTACTS_TABLE + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_PHONE + " integer not null);";

    private static boolean FIRST_TIME_INSERTION = true;

    public static final String INSERT_STATEMENT = "INSERT INTO " + CONTACTS_TABLE + " VALUES (%s, %s);";

    public ContactsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STATEMENT);
        if (FIRST_TIME_INSERTION) {
            for (int i = 0; i < NAMES.length; i++) {
                sqLiteDatabase.execSQL(String.format(Locale.ENGLISH, INSERT_STATEMENT, NAMES[i], PHONE_NUMBERS[i]));
            }
            FIRST_TIME_INSERTION = false;
        }
        Log.d(TAG, "CREATE statement executed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


}
