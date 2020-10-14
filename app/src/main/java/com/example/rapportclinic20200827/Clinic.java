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
        this.ID = cv.getAsInteger("_id");
        this.Name = cv.getAsString("name");
        this.Address = cv.getAsString("address");
        this.Phone_No = cv.getAsString("phone_no");
    }


    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put("_id",this.ID);
        cv.put("name",this.Name);
        cv.put("address", this.Address);
        cv.put("phone_no",this.Phone_No);
        return cv;
    }
}
