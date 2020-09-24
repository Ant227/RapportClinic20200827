package com.example.rapportclinic20200827;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyDataBaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "RapportClinical.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PatientRecord";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE= "age";
    private static final String COLUMN_GENDER= "gender";
    private static final String COLUMN_DATE = "date";

    private final String 
        CREATE_PatientRecord_TABLE =
            "CREATE TABLE " + TABLE_NAME +
            " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_AGE + " INT, " +
            COLUMN_GENDER + " TEXT, " +
            COLUMN_DATE + " TEXT);" ;


    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PatientRecord_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);
    }

    //add Patient data into Patient table of sqlite database

    public void addPatient(Patient patient){
        SQLiteDatabase db = this.getWritableDatabase();



        long result =   db.insert(TABLE_NAME,null ,patient.getContentValues());
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // get all data from database
    public Cursor readPatient(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        ArrayList<Patient> patients = new ArrayList<>();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

        /*


        while (cursor.moveToNext()) {

            patients.add(new Patient(
                    new Integer(cursor.getString(0)),
                    cursor.getString(1),
                    new Integer(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4)
                    )
            );

          return patients;
         */
    }

    // get all data from database
    public ArrayList<Patient>   readPatients(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        ArrayList<Patient> patients = new ArrayList<>();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }



        while (cursor.moveToNext()) {

            patients.add(new Patient(
                    new Integer(cursor.getString(0)),
                    cursor.getString(1),
                    new Integer(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4)
                    )
            );
        }
          return patients;

    }




    //get Patient by name
    public Cursor getPatientByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={COLUMN_ID,
                COLUMN_NAME,
                COLUMN_AGE,
                COLUMN_GENDER,
                COLUMN_DATE};

        qb.setTables(TABLE_NAME);
        Cursor cursor = qb.query(db, sqlSelect, COLUMN_NAME + " LIKE ?",
                new String[]{"%"+name+"%"}, null, null, null);

        return cursor;
    }

    public ArrayList<Patient> getPatientsByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        ArrayList<Patient> patients = new ArrayList<>();

        String[] sqlSelect={COLUMN_ID,
                COLUMN_NAME,
                COLUMN_AGE,
                COLUMN_GENDER,
                COLUMN_DATE};

        qb.setTables(TABLE_NAME);
        Cursor cursor = qb.query(db, sqlSelect, COLUMN_NAME + " LIKE ?",
                new String[]{"%"+name+"%"}, null, null, null);

        while (cursor.moveToNext()) {

            patients.add(new Patient(
                            new Integer(cursor.getString(0)),
                            cursor.getString(1),
                            new Integer(cursor.getString(2)),
                            cursor.getString(3),
                            cursor.getString(4)
                    )
            );
        }
        return patients;

    }

}
