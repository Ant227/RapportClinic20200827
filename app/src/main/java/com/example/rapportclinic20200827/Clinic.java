package com.example.rapportclinic20200827;

import android.content.ContentValues;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class Clinic implements java.io.Serializable {

    @Getter @Setter private Integer ID;
    @Getter @Setter private String Name;
    @Getter @Setter private String Address;
    @Getter @Setter private String Phone_No;

    public Clinic(ContentValues cv){
        this.ID = cv.getAsInteger(MyDataBaseHelper.CLINIC_ID_COLUMN);
        this.Name = cv.getAsString(MyDataBaseHelper.CLINIC_NAME_COLUMN);
        this.Address = cv.getAsString(MyDataBaseHelper.CLINIC_ADDRESS_COLUMN);
        this.Phone_No = cv.getAsString(MyDataBaseHelper.CLINIC_PHONE_NO_COLUMN);
    }


    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put(MyDataBaseHelper.CLINIC_ID_COLUMN,this.ID);
        cv.put(MyDataBaseHelper.CLINIC_NAME_COLUMN,this.Name);
        cv.put(MyDataBaseHelper.CLINIC_ADDRESS_COLUMN, this.Address);
        cv.put(MyDataBaseHelper.CLINIC_PHONE_NO_COLUMN,this.Phone_No);
        return cv;
    }
}
