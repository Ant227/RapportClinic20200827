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
        this.ID = cv.getAsInteger("_id");
        this.Name = cv.getAsString("name");
        this.Qualifications = cv.getAsString("qualifications");
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put("_id", this.ID);
        cv.put("name",this.Name);
        cv.put("qualificatins",this.Qualifications);
        return cv;
    }


}
