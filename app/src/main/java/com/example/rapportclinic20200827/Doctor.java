package com.example.rapportclinic20200827;

import android.content.ContentValues;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements java.io.Serializable {

    @Getter @Setter private Integer ID = null;
    @Getter @Setter private String Name = null;
    @Getter @Setter private String Qualifications = null;


    public Doctor(ContentValues cv){
        this.ID = cv.getAsInteger(MyDataBaseHelper.DOCTOR_ID_COLUMN);
        this.Name = cv.getAsString(MyDataBaseHelper.DOCTOR_NAME_COLUMN);
        this.Qualifications = cv.getAsString(MyDataBaseHelper.DOCTOR_QUALIFICATIONS_COLUMN);
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put(MyDataBaseHelper.DOCTOR_ID_COLUMN, this.ID);
        cv.put(MyDataBaseHelper.DOCTOR_NAME_COLUMN,this.Name);
        cv.put(MyDataBaseHelper.DOCTOR_QUALIFICATIONS_COLUMN,this.Qualifications);
        return cv;
    }


}
