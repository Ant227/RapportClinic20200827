package com.example.rapportclinic20200827;

import android.content.ContentValues;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class BillItem implements java.io.Serializable {

    @Getter @Setter private Integer ID;
    @Getter @Setter private Integer Bill_ID;
    @Getter @Setter private String Description;
    @Getter @Setter private Integer Quantity;
    @Getter @Setter private Integer Price_Per_Item;
    @Getter  private Integer Price;

    public void Calculate_Price(){
        Price = Quantity*Price_Per_Item;
    }

    public BillItem(ContentValues cv){
        this.ID = cv.getAsInteger(MyDataBaseHelper.BILL_ITEM_ID_COLUMN);
        this.Bill_ID = cv.getAsInteger(MyDataBaseHelper.BILL_ITEM_BILL_ID_COLUMN);
        this.Description = cv.getAsString(MyDataBaseHelper.BILL_ITEM_DESCRIPTION_COLUMN);
        this.Quantity = cv.getAsInteger(MyDataBaseHelper.BILL_ITEM_QUANTITY_COLUMN);
        this.Price_Per_Item = cv.getAsInteger(MyDataBaseHelper.BILL_ITEM_PRICE_PER_ITEM_COLUMN);
        this.Price = cv.getAsInteger(MyDataBaseHelper.BILL_ITEM_PRICE_COLUMN);

    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put(MyDataBaseHelper.BILL_ITEM_ID_COLUMN,this.ID);
        cv.put(MyDataBaseHelper.BILL_ITEM_BILL_ID_COLUMN,this.Bill_ID);
        cv.put(MyDataBaseHelper.BILL_ITEM_DESCRIPTION_COLUMN, this.Description);
        cv.put(MyDataBaseHelper.BILL_ITEM_QUANTITY_COLUMN, this.Quantity);
        cv.put(MyDataBaseHelper.BILL_ITEM_PRICE_PER_ITEM_COLUMN, this.Price_Per_Item);
        cv.put(MyDataBaseHelper.BILL_ITEM_PRICE_COLUMN, this.Price);

        return cv;
    }
}
