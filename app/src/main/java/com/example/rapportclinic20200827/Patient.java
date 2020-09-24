package com.example.rapportclinic20200827;

import android.content.ContentValues;


public class Patient{
	
	private Integer ID = null;
	private String Name = null;
	private Integer Age = null;
	private String Sex = null;
	private String Date = null;
	
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

	public Patient(Patient patient) {
		this.ID = patient.getID();
		this.Name = patient.getName();
		this.Age = patient.getAge();
		this.Sex = patient.getSex();
	}
	
	public Integer getID(){
		return ID;
	}
	
	public String getName(){
		return Name;
	}
	
	public Integer getAge(){
		return Age;
	}
	
	public String getSex(){
		return Sex;
	}

	public String getDate(){
		return Date;
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
	
	public void setID(Integer ID){
		this.ID = ID;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public void setAge(Integer Age) {
		this.Age = Age;
	}
	
	public void setSex(String Sex) {
		this.Sex = Sex;
	}

	public void setDate(String Date){
		this.Date = Date;
	}
}