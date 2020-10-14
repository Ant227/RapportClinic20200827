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
    @Getter @Setter private Integer Clinic_ID;
    @Getter @Setter private String Date;
    @Getter @Setter private Integer Total_Price;

    public Bill(ContentValues cv){
        this.ID = cv.getAsInteger("_id");
        this.Visit_ID = cv.getAsInteger("visit_id");
        this.Patient_ID = cv.getAsInteger("patient_id");
        this.Clinic_ID = cv.getAsInteger("clinic_id");
        this.Date = cv.getAsString("date");
        this.Total_Price = cv.getAsInteger("total_price");
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID!=null) cv.put("_id", this.ID);
        cv.put("visit_id",this.Visit_ID);
        cv.put("patient_id",this.Patient_ID);
        cv.put("clinic_id", this.Clinic_ID);
        cv.put("date", this.Date);
        cv.put("total_price", this.Total_Price);
        return cv;
    }
}
