package com.example.rapportclinic20200827;

import android.content.ContentValues;
import lombok.Getter;
import lombok.Setter;


public class Patient implements java.io.Serializable{
	
	@Getter @Setter private Integer ID = null;
	@Getter @Setter private String Name = null;
	@Getter @Setter private Integer Age = null;
	@Getter @Setter private String Sex = null;
	@Getter @Setter private String Date = null;
	
	public Patient() {
	}
	
	public Patient(Integer ID) {
		this.ID = ID;
	}
	
	public Patient(Integer ID, String Name) {
		this.ID = ID;
		this.Name = Name;
	}
	
	public Patient(Integer ID, String Name, Integer Age) {
		this.ID = ID;
		this.Name = Name;
		this.Age = Age;
	}
	
	public Patient(Integer ID, String Name, Integer Age, String Sex) {
		this.ID = ID;
		this.Name = Name;
		this.Age = Age;
		this.Sex = Sex;
	}
	
	public Patient(Integer ID, String Name, Integer Age, String Sex, String Date) {
		this.ID = ID;
		this.Name = Name;
		this.Age = Age;
		this.Sex = Sex;
		this.Date = Date;
	}

	public Patient(ContentValues cv){
		this.ID = cv.getAsInteger("_id");
		this.Name = cv.getAsString("name");
		this.Age = cv.getAsInteger("age");
		this.Sex = cv.getAsString("gender");
		this.Date = cv.getAsString("date");
	}


	public Patient(Patient patient) {
		this.ID = patient.getID();
		this.Name = patient.getName();
		this.Age = patient.getAge();
		this.Sex = patient.getSex();
	}

	@Override
	public String toString() {
		return "Patient{" +
				"ID=" + ID +
				", Name='" + Name + '\'' +
				", Age=" + Age +
				", Sex='" + Sex + '\'' +
				", Date='" + Date + '\'' +
				'}';
	}



	public ContentValues getContentValues(){

		ContentValues cv = new ContentValues();

		if (this.ID != null) cv.put("_id",this.ID);

		cv.put("name", this.Name);
		cv.put("age", this.Age);
		cv.put("gender", this.Sex);
		cv.put("date", this.Date);

		return cv;
	}

	public void Save(){
		//Save patient to database
		MyDataBaseHelper.getInstance().insert(this);
	}

	public void Update(){
		//update patient to database
	}

	public void Delete(){
		//delete patient from database
	}
}
