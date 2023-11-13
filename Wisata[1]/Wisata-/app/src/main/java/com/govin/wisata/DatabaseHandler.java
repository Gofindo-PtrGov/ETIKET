package com.govin.wisata;
import android.app.Service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dimas Maulana on 2/27/17.
 * Email : araymaulana66@gmail.com
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "wisata";

    // table name
    private static final String TABLE_TALL = "destinasi";

    // column tables
    private static final String KEY_ID = "id_";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "desc";
    private static final String KEY_PRICE = "price";
    private static final String KEY_RATING = "rating";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_KOORDINAT = "koordinat";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TALL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT," +KEY_PRICE+" TEXT,"+KEY_RATING+" TEXT,"+KEY_IMAGE+" TEXT, "+KEY_KOORDINAT+ " TEXT"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TALL);
        onCreate(db);
    }
}