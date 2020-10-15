package com.example.rapportclinic20200827;

import android.content.ContentValues;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Bill implements java.io.Serializable {

    @Getter @Setter private Integer ID;
    @Getter @Setter private Integer Visit_ID;
    @Getter @Setter private Integer Patient_ID;
    @Getter @Setter private Integer Doctor_ID;
    @Getter @Setter private Integer Clinic_ID;
    @Getter @Setter private String Date;
    @Getter @Setter private Integer Total_Price;

    public Bill(ContentValues cv){
        this.ID = cv.getAsInteger(MyDataBaseHelper.BILL_ID_COLUMN);
        this.Visit_ID = cv.getAsInteger(MyDataBaseHelper.BILL_VISIT_ID_COLUMN);
        this.Patient_ID = cv.getAsInteger(MyDataBaseHelper.BILL_PATIENT_ID_COLUMN);
        this.Doctor_ID = cv.getAsInteger(MyDataBaseHelper.BILL_DOCTOR_ID_COLUMN);
        this.Clinic_ID = cv.getAsInteger(MyDataBaseHelper.BILL_CLINIC_ID_COLUMN);
        this.Date = cv.getAsString(MyDataBaseHelper.BILL_DATE_COLUMN);
        this.Total_Price = cv.getAsInteger(MyDataBaseHelper.BILL_TOTAL_PRICE_COLUMN);
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID!=null) cv.put(MyDataBaseHelper.BILL_ID_COLUMN, this.ID);
        cv.put(MyDataBaseHelper.BILL_VISIT_ID_COLUMN,this.Visit_ID);
        cv.put(MyDataBaseHelper.BILL_PATIENT_ID_COLUMN,this.Patient_ID);
        cv.put(MyDataBaseHelper.BILL_DOCTOR_ID_COLUMN,this.Doctor_ID);
        cv.put(MyDataBaseHelper.BILL_CLINIC_ID_COLUMN, this.Clinic_ID);
        cv.put(MyDataBaseHelper.BILL_DATE_COLUMN, this.Date);
        cv.put(MyDataBaseHelper.BILL_TOTAL_PRICE_COLUMN, this.Total_Price);
        return cv;
    }
}
