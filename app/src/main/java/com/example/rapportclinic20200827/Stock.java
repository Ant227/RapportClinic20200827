package com.example.rapportclinic20200827;

import android.content.ContentValues;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Stock implements java.io.Serializable {


    @Getter @Setter private Integer ID;
    @Getter @Setter private Integer Drug_ID;
    @Getter @Setter private String Date;
    @Getter @Setter private Integer Stock_Entry;

    public Stock(ContentValues cv){
        this.ID = cv.getAsInteger(MyDataBaseHelper.STOCK_ID_COLUMN);
        this.Drug_ID = cv.getAsInteger(MyDataBaseHelper.STOCK_DRUG_ID_COLUMN);
        this.Date = cv.getAsString(MyDataBaseHelper.STOCK_DATE_COLUMN);
        this.Stock_Entry =cv.getAsInteger(MyDataBaseHelper.STOCK_STOCK_ENTRY_COLUMN);
    }


    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put(MyDataBaseHelper.STOCK_ID_COLUMN, this.ID);
        cv.put(MyDataBaseHelper.STOCK_DRUG_ID_COLUMN,this.Drug_ID);
        cv.put(MyDataBaseHelper.STOCK_DATE_COLUMN,this.Date);
        cv.put(MyDataBaseHelper.STOCK_STOCK_ENTRY_COLUMN,this.Stock_Entry);
        return  cv;
    }
}
