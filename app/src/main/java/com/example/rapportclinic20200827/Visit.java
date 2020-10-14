package com.example.rapportclinic20200827;


import android.content.ContentValues;
import lombok.Getter;
import lombok.Setter;

public class Visit implements java.io.Serializable {
	@Getter @Setter private Integer ID = null;
	@Getter @Setter private Integer Patient_ID = null;
	@Getter @Setter private Integer Doctor_ID = null;
	@Getter @Setter private Integer Clinic_ID = null;
	@Getter @Setter private Integer Previous_Visit_ID = null;
	@Getter @Setter private String Date = null;
	@Getter @Setter private String History = null;
	@Getter @Setter private String Examination = null;
	@Getter @Setter private String Treatment = null;
	


	public Visit(ContentValues cv){
		this.ID = cv.getAsInteger("_id");
		this.Patient_ID = cv.getAsInteger("patient_id");
		this.Date = cv.getAsString("date");
		this.History = cv.getAsString("history");
		this.Examination = cv.getAsString("examination");
		this.Treatment = cv.getAsString("treatment");
	}
	
	
	public Visit(){
		
	}
	
	public Visit(Integer ID){
		this.ID = ID;
	}
	
	public Visit(Integer ID, Integer Patient_ID) {
		this.ID = ID;
		this.Patient_ID = Patient_ID;
	}
	
	public Visit(Integer ID, Integer Patient_ID, String Date) {
		this.ID = ID;
		this.Patient_ID = Patient_ID;
		this.Date = Date;
	}
	
	public Visit(Integer ID, Integer Patient_ID, String Date, String History) {
		this.ID = ID;
		this.Patient_ID = Patient_ID;
		this.Date = Date;
		this.History = History;
	}	
	
	public Visit(Integer ID, Integer Patient_ID, String Date, String History, String Examination) {
		this.ID = ID;
		this.Patient_ID = Patient_ID;
		this.Date = Date;
		this.History = History;
		this.Examination = Examination;
	}	
	
	
	public Visit(Integer ID, Integer Patient_ID, String Date, String History, String Examination, String Treatment) {
		this.ID = ID;
		this.Patient_ID = Patient_ID;
		this.Date = Date;
		this.History = History;
		this.Examination = Examination;
		this.Treatment = Treatment;
	}

	public ContentValues getContentValues(){
		ContentValues cv = new ContentValues();

		if (this.ID != null) cv.put("_id",this.ID);
		if (this.Patient_ID != null) cv.put("patient_id", this.Patient_ID);

		cv.put("date",this.Date);
		cv.put("history", this.History);
		cv.put("examination", this.Examination);
		cv.put("treatment",this.Treatment);

		return cv;


	}

	@Override
	public String toString() {
		return "Visit{" +
				"ID=" + ID +
				", Patient_ID=" + Patient_ID +
				", Date='" + Date + '\'' +
				", History='" + History + '\'' +
				", Examination='" + Examination + '\'' +
				", Treatment='" + Treatment + '\'' +
				'}';
	}


}
