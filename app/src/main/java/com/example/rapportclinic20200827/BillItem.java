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
    @Getter @Setter private String Item_Description;
    @Getter @Setter private Integer Item_Quantity;
    @Getter @Setter private Integer Price_Per_Item;
    @Getter  private Integer Price;

    public void Calculate_Price(){
        Price = Item_Quantity*Price_Per_Item;
    }

    public BillItem(ContentValues cv){
        this.ID = cv.getAsInteger("_id");
        this.Bill_ID = cv.getAsInteger("bill_id");
        this.Item_Description = cv.getAsString("item_description");
        this.Item_Quantity = cv.getAsInteger("item_quantity");
        this.Price_Per_Item = cv.getAsInteger("price_per_item");
        this.Price = cv.getAsInteger("price");

    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        if(this.ID != null) cv.put("_id",this.ID);
        cv.put("bill_id",this.Bill_ID);
        cv.put("item_description", this.Item_Description);
        cv.put("item_quantity", this.Item_Quantity);
        cv.put("price_per_item", this.Price_Per_Item);
        cv.put("price", this.Price);

        return cv;
    }
}