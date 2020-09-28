package com.example.rapportclinic20200827;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;


import com.example.rapportclinic20200827.Visit;
import com.example.rapportclinic20200827.Patient;



public class MyDataBaseHelper extends SQLiteOpenHelper  {

    private static MyDataBaseHelper sInstance;


    private Context context;
    private static final String DATABASE_NAME = "RapportClinical.db";
    private static final int DATABASE_VERSION = 1;
    private static final String PATIENT_RECORD_TABLE = "PatientRecord";
    private static final String PATIENT_ID_COLUMN = "_id";
    private static final String PATIENT_NAME_COLUMN = "name";
    private static final String PATIENT_AGE_COLUMN= "age";
    private static final String PATIENT_GENDER_COLUMN= "gender";
    private static final String PATIENT_DATE_COLUMN = "date";


    private static final String VISIT_RECORD_TABLE = "VisitRecord";
    private static final String VISIT_ID_COLUMN = "_id";
    private static final String VISIT_PATIENT_ID_COLUMN = "patient_id";
    private static final String VISIT_DATE_COLUMN = "date";
    private static final String VISIT_HISTORY_COLUMN = "history";
    private static final String VISIT_EXAMINATION_COLUMN = "examination";
    private static final String VISIT_TREATMENT_COLUMN = "treatment";

    private final String 
        CREATE_PatientRecord_TABLE =
            "CREATE TABLE " + PATIENT_RECORD_TABLE +
            " ("+ PATIENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PATIENT_NAME_COLUMN + " TEXT, " +
            PATIENT_AGE_COLUMN + " INT, " +
            PATIENT_GENDER_COLUMN + " TEXT, " +
            PATIENT_DATE_COLUMN + " TEXT);" ;


    private final String
        CREATE_VisitRecord_TABLE =
            "CREATE TABLE " + VISIT_RECORD_TABLE +
                    "(" + VISIT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VISIT_PATIENT_ID_COLUMN + " INTEGER, " +
                    VISIT_DATE_COLUMN + " TEXT, " +
                    VISIT_HISTORY_COLUMN + " TEXT ," +
                    VISIT_EXAMINATION_COLUMN + " TEXT," +
                    VISIT_TREATMENT_COLUMN + " TEXT ); " ;

    public static synchronized MyDataBaseHelper getInstance(Context context){

        if(sInstance == null){
            sInstance = new MyDataBaseHelper(context.getApplicationContext());

        }

        return  sInstance;
    }





    private MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PatientRecord_TABLE);
        db.execSQL(CREATE_VisitRecord_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ PATIENT_RECORD_TABLE );
        db.execSQL("DROP TABLE IF EXISTS "+ VISIT_RECORD_TABLE );
        onCreate(db);
    }

    //add Patient data into Patient table of sqlite database

    public long addPatient(Patient patient){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =   db.insert(PATIENT_RECORD_TABLE,null ,patient.getContentValues());

        return result;
        /*
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
        */
    }

    //add Visit to VisitRecord table in database

    public long addVisit(Visit visit){

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(VISIT_RECORD_TABLE,null,visit.getContentValues());

        return result;
        /*
        if (result == -1) {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added Successfully!", Toast.LENGTH_SHORT).show();
        }

         */

    }


    public Patient getLastPatient(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT * FROM PatientRecord ORDER BY _id DESC LIMIT 1 ";
        Patient lastPatient = null;

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }

        if(cursor.moveToNext()) {

             lastPatient = new Patient(
                    new Integer(cursor.getString(0)),
                    cursor.getString(1),
                    new Integer(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        }
        Log.d("getLastPatient : ",lastPatient.toString());

        return lastPatient;



    }


    // read all patients record from database
    public ArrayList<Patient>   readPatients(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ PATIENT_RECORD_TABLE;
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


    //read all visits from record
    public ArrayList<Visit> readVisits(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + VISIT_RECORD_TABLE;
        ArrayList<Visit> visits = new ArrayList<>();

        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            visits.add(
                new Visit(
                    new Integer(cursor.getString(0)),
                    new Integer(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
                )
            );


        }

        return visits;
    }


    //read visits of a patient from record
    public ArrayList<Visit> readVisits(Patient patient){


        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + VISIT_RECORD_TABLE +
               " WHERE   " + VISIT_PATIENT_ID_COLUMN + "  =  " + patient.getID().toString();


        ArrayList<Visit> visits = new ArrayList<>();

        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            visits.add(
                    new Visit(
                            new Integer(cursor.getString(0)),
                            new Integer(cursor.getString(1)),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)
                    )
            );


        }

        return visits;
    }






    //get Patient by name
    public Cursor getPatientByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={PATIENT_ID_COLUMN,
                PATIENT_NAME_COLUMN,
                PATIENT_AGE_COLUMN,
                PATIENT_GENDER_COLUMN,
                PATIENT_DATE_COLUMN};

        qb.setTables(PATIENT_RECORD_TABLE);
        Cursor cursor = qb.query(db, sqlSelect, PATIENT_NAME_COLUMN + " LIKE ?",
                new String[]{"%"+name+"%"}, null, null, null);

        return cursor;
    }

    public ArrayList<Patient> getPatientsByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        ArrayList<Patient> patients = new ArrayList<>();

        String[] sqlSelect={PATIENT_ID_COLUMN,
                PATIENT_NAME_COLUMN,
                PATIENT_AGE_COLUMN,
                PATIENT_GENDER_COLUMN,
                PATIENT_DATE_COLUMN};

        qb.setTables(PATIENT_RECORD_TABLE);
        Cursor cursor = qb.query(db, sqlSelect, PATIENT_NAME_COLUMN + " LIKE ?",
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

    public ArrayList<Patient> queryPatients(String query){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Patient> patients = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);
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
