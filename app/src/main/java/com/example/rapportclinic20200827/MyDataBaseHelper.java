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


import java.lang.reflect.Array;
import java.util.ArrayList;


import com.example.rapportclinic20200827.Visit;
import com.example.rapportclinic20200827.Patient;



public class MyDataBaseHelper extends SQLiteOpenHelper  {

    private static MyDataBaseHelper sInstance;


    private Context context;

    public static final String DATABASE_NAME = "RapportClinical.db";
    private static final int DATABASE_VERSION = 1;


    public static final String PATIENT_RECORD_TABLE = "PatientRecord";
    public static final String PATIENT_ID_COLUMN = "_id";
    public static final String PATIENT_NAME_COLUMN = "name";
    public static final String PATIENT_AGE_COLUMN = "age";
    public static final String PATIENT_GENDER_COLUMN = "gender";
    public static final String PATIENT_DATE_COLUMN = "date";
    private final String CREATE_PatientRecord_TABLE =
            "CREATE TABLE " + PATIENT_RECORD_TABLE +
            " (" + PATIENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PATIENT_NAME_COLUMN + " TEXT, " +
                    PATIENT_AGE_COLUMN + " INT, " +
                    PATIENT_GENDER_COLUMN + " TEXT, " +
                    PATIENT_DATE_COLUMN + " TEXT);";


    public static final String DOCTOR_RECORD_TABLE = "DoctorRecord";
    public static final String DOCTOR_ID_COLUMN   = "_id";
    public static final String DOCTOR_NAME_COLUMN = "name";
    public static final String DOCTOR_QUALIFICATIONS_COLUMN = "qualifications";
    private final String CREATE_DoctorRecord_TABLE =
            "CREATE TABLE " + DOCTOR_RECORD_TABLE +
            " (" + DOCTOR_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DOCTOR_NAME_COLUMN + " TEXT, " +
                    DOCTOR_QUALIFICATIONS_COLUMN + " TEXT); ";


    public static final String CLINIC_RECORD_TABLE = "ClinicRecord";
    public static final String CLINIC_ID_COLUMN = "_id";
    public static final String CLINIC_NAME_COLUMN = "name";
    public static final String CLINIC_ADDRESS_COLUMN = "address";
    public static final String CLINIC_PHONE_NO_COLUMN = "phone_no";
    private final String CREATE_ClinicRecord_TABLE =
            "CREATE TABLE " + CLINIC_RECORD_TABLE +
            " (" + CLINIC_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CLINIC_NAME_COLUMN + " TEXT, " +
                    CLINIC_ADDRESS_COLUMN + " TEXT, " +
                    CLINIC_PHONE_NO_COLUMN + " TEXT ); ";

    public static final String BILL_RECORD_TABLE = "BillRecord";
    public static final String BILL_ID_COLUMN = "_id";
    public static final String BILL_VISIT_ID_COLUMN = "visit_id";
    public static final String BILL_PATIENT_ID_COLUMN = "patient_id";
    public static final String BILL_DOCTOR_ID_COLUMN = "doctor_id";
    public static final String BILL_CLINIC_ID_COLUMN = "clinic_id";
    public static final String BILL_DATE_COLUMN = "date";
    public static final String BILL_TOTAL_PRICE_COLUMN = "total_price";
    private final String CREATE_BillRecord_TABLE =
            "CREATE TABLE " + BILL_RECORD_TABLE +
                    " (" + BILL_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BILL_VISIT_ID_COLUMN + " INTEGER, " +
                    BILL_PATIENT_ID_COLUMN + " INTEGER, " +
                    BILL_DOCTOR_ID_COLUMN + " INTEGER, " +
                    BILL_CLINIC_ID_COLUMN + " INTEGER, " +
                    BILL_DATE_COLUMN + " TEXT, " +
                    BILL_TOTAL_PRICE_COLUMN + " INTEGER ); " ;



    public static final String BILL_ITEM_RECORD_TABLE = "BillItemRecord";
    public static final String BILL_ITEM_ID_COLUMN = "_id";
    public static final String BILL_ITEM_BILL_ID_COLUMN = "bill_id";
    public static final String BILL_ITEM_DESCRIPTION_COLUMN = "description";
    public static final String BILL_ITEM_QUANTITY_COLUMN = "quantity";
    public static final String BILL_ITEM_PRICE_PER_ITEM_COLUMN = "price_per_item";
    public static final String BILL_ITEM_PRICE_COLUMN = "price";
    private final String CREATE_BillItemRecord_TABLE =
            "CREATE TABLE " + BILL_ITEM_RECORD_TABLE +
                    " (" + BILL_ITEM_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BILL_ITEM_BILL_ID_COLUMN + " INTEGER, " +
                    BILL_ITEM_DESCRIPTION_COLUMN + " TEXT, " +
                    BILL_ITEM_QUANTITY_COLUMN + " INTEGER, " +
                    BILL_ITEM_PRICE_PER_ITEM_COLUMN + " INTEGER, " +
                    BILL_ITEM_PRICE_COLUMN + " INTEGER ); ";


    public static final String VISIT_RECORD_TABLE = "VisitRecord";
    public static final String VISIT_ID_COLUMN = "_id";
    public static final String VISIT_PATIENT_ID_COLUMN = "patient_id";
    public static final String VISIT_DATE_COLUMN = "date";
    public static final String VISIT_HISTORY_COLUMN = "history";
    public static final String VISIT_EXAMINATION_COLUMN = "examination";
    public static final String VISIT_TREATMENT_COLUMN = "treatment";
    private final String
        CREATE_VisitRecord_TABLE =
            "CREATE TABLE " + VISIT_RECORD_TABLE +
                    " (" + VISIT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VISIT_PATIENT_ID_COLUMN + " INTEGER, " +
                    VISIT_DATE_COLUMN + " TEXT, " +
                    VISIT_HISTORY_COLUMN + " TEXT ," +
                    VISIT_EXAMINATION_COLUMN + " TEXT," +
                    VISIT_TREATMENT_COLUMN + " TEXT ); " ;


    public static final String DRUG_RECORD_TABLE = "DrugRecord";
    public static final String DRUG_ID_COLUMN = "_id";
    public static final String DRUG_BRAND_NAME_COLUMN = "brand_name";
    public static final String DRUG_GENERIC_NAME_COLUMN = "generic_name";
    public static final String DRUG_COMPANY_INFO_COLUMN = "company_info";
    public static final String DRUG_FORM_COLUMN = "form";
    public static final String DRUG_PER_CARD_COLUMN = "per_card";
    public static final String DRUG_PER_PACKAGE_COLUMN = "per_package";
    public static final String DRUG_CATEGORY_COLUMN = "category";
    private final String CREATE_DrugRecord_TABLE =
            "CREATE TABLE " + DRUG_RECORD_TABLE +
                    " (" + DRUG_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DRUG_BRAND_NAME_COLUMN + " TEXT, " +
                    DRUG_GENERIC_NAME_COLUMN + " TEXT, " +
                    DRUG_COMPANY_INFO_COLUMN + " TEXT, " +
                    DRUG_FORM_COLUMN + " TEXT, " +
                    DRUG_PER_CARD_COLUMN + " INTEGER, " +
                    DRUG_PER_PACKAGE_COLUMN + " INTEGER, " +
                    DRUG_CATEGORY_COLUMN + " TEXT ); " ;

    public static final String STOCK_RECORD_TABLE = "StockRecord";
    public static final String STOCK_ID_COLUMN = "_id";
    public static final String STOCK_DRUG_ID_COLUMN = "drug_id";
    public static final String STOCK_DATE_COLUMN = "date";
    public static final String STOCK_STOCK_ENTRY_COLUMN = "stock_entry";
    private final String CREATE_StockRecord_TABLE =
            "CREATE TABLE " + STOCK_RECORD_TABLE +
                    " ( " + STOCK_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    STOCK_DRUG_ID_COLUMN + " INTEGER, " +
                    STOCK_DATE_COLUMN + " TEXT, " +
                    STOCK_STOCK_ENTRY_COLUMN + " INTEGER ); ";






    public static synchronized MyDataBaseHelper getInstance(Context context){

        if(sInstance == null){
            sInstance = new MyDataBaseHelper(context.getApplicationContext());

        }

        return  sInstance;
    }

    public static synchronized MyDataBaseHelper getInstance(){
         return sInstance;
    }



    private MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PatientRecord_TABLE);
        db.execSQL(CREATE_VisitRecord_TABLE);
        db.execSQL(CREATE_DoctorRecord_TABLE);
        db.execSQL(CREATE_ClinicRecord_TABLE);
        db.execSQL(CREATE_BillRecord_TABLE);
        db.execSQL(CREATE_BillItemRecord_TABLE);
        db.execSQL(CREATE_DrugRecord_TABLE);
        db.execSQL(CREATE_StockRecord_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ PATIENT_RECORD_TABLE );
        db.execSQL("DROP TABLE IF EXISTS "+ VISIT_RECORD_TABLE );
        db.execSQL("DROP TABLE IF EXISTS "+ DOCTOR_RECORD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ CLINIC_RECORD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ BILL_RECORD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ BILL_ITEM_RECORD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ DRUG_RECORD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ STOCK_RECORD_TABLE);
        onCreate(db);
    }

    //insert Patient data into Patient table of sqlite database


    public long insert(Patient patient){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =   db.insert(PATIENT_RECORD_TABLE,null ,patient.getContentValues());

        return result;


    }

    public int delete(Patient patient){
        SQLiteDatabase db = this.getWritableDatabase();


        String where =PATIENT_ID_COLUMN + "=?";

        int numberOfEntriesDeleted = db.delete(PATIENT_RECORD_TABLE,where,new String[]{patient.getID().toString()});
        //delete patient data PatientRecord Table

        int numberOfVisitsDeleted = db.delete(VISIT_RECORD_TABLE,VISIT_PATIENT_ID_COLUMN +"=?",new String[]{patient.getID().toString()});
        //delete all visits of patient from VisitRecord Table

        Log.d("Number of Entry Deleted Successfully : ",numberOfEntriesDeleted+"");
        return numberOfEntriesDeleted;
    }

    public int delete(Visit visit){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = VISIT_ID_COLUMN +"=?";
        int numberOfEntriesDeleted = db.delete(VISIT_RECORD_TABLE,where, new String[]{visit.getID().toString()});


        Log.d("Number of Entry Deleted Successfully : ",numberOfEntriesDeleted+"");
        return numberOfEntriesDeleted;
    }

    //insert Visit to VisitRecord table in database

    public long insert(Visit visit){

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(VISIT_RECORD_TABLE,null,visit.getContentValues());

        return result;

    }

    public int update(Patient patient){

        return 0;
    }

    public int update(Visit visit){
        return 0;
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
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        }


        return lastPatient;



    }


    // read all patients record from database
    public ArrayList<Patient>   readPatients(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT * FROM PatientRecord ORDER BY _id DESC ";
        ArrayList<Patient> patients = new ArrayList<>();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }


        while (cursor.moveToNext()) {

            patients.add(new Patient(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
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
        String query = "SELECT * FROM " + VISIT_RECORD_TABLE + " ORDER BY _id DESC ";
        ArrayList<Visit> visits = new ArrayList<>();

        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            visits.add(
                new Visit(
                    cursor.getInt(0),
                    cursor.getInt(1),
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
               " WHERE   " + VISIT_PATIENT_ID_COLUMN + "  =  "
                + patient.getID().toString() + " ORDER BY _id DESC ";




        ArrayList<Visit> visits = new ArrayList<>();

        Cursor cursor = null;
        if (db != null) cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            visits.add(
                    new Visit(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)
                    )
            );


        }

        return visits;
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
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
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
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    )
            );
        }
        return patients;


    }

}
