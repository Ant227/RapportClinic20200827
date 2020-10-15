package com.example.rapportclinic20200827;

import android.content.ContentValues;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Drug implements java.io.Serializable {

    @Getter @Setter private Integer ID;
    @Getter @Setter private String Brand_Name;
    @Getter @Setter private String Generic_Name;
    @Getter @Setter private String Company_Info;
    @Getter @Setter private String Form;
    @Getter @Setter private Integer Per_Card;
    @Getter @Setter private Integer Per_Package;
    @Getter @Setter private String Category;

    public Drug(ContentValues cv){
        this.ID = cv.getAsInteger(MyDataBaseHelper.DRUG_ID_COLUMN);
        this.Brand_Name = cv.getAsString(MyDataBaseHelper.DRUG_BRAND_NAME_COLUMN);
        this.Generic_Name = cv.getAsString(MyDataBaseHelper.DRUG_GENERIC_NAME_COLUMN);
        this.Company_Info = cv.getAsString(MyDataBaseHelper.DRUG_COMPANY_INFO_COLUMN);
        this.Form = cv.getAsString(MyDataBaseHelper.DRUG_FORM_COLUMN);
        this.Per_Card = cv.getAsInteger(MyDataBaseHelper.DRUG_PER_CARD_COLUMN);
        this.Per_Package = cv.getAsInteger(MyDataBaseHelper.DRUG_PER_PACKAGE_COLUMN);
        this.Category = cv.getAsString(MyDataBaseHelper.DRUG_CATEGORY_COLUMN);
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put(MyDataBaseHelper.DRUG_ID_COLUMN,this.ID);
        cv.put(MyDataBaseHelper.DRUG_BRAND_NAME_COLUMN,this.Brand_Name);
        cv.put(MyDataBaseHelper.DRUG_GENERIC_NAME_COLUMN,this.Generic_Name);
        cv.put(MyDataBaseHelper.DRUG_COMPANY_INFO_COLUMN,this.Company_Info);
        cv.put(MyDataBaseHelper.DRUG_FORM_COLUMN,this.Form);
        cv.put(MyDataBaseHelper.DRUG_PER_CARD_COLUMN,this.Per_Card);
        cv.put(MyDataBaseHelper.DRUG_PER_PACKAGE_COLUMN,this.Per_Package);
        cv.put(MyDataBaseHelper.DRUG_CATEGORY_COLUMN,this.Category);
        return cv;
    }
}
