

public class Visit {
	private Integer ID = null;
	private Integer Patient_ID = null;
	private String Date = null;
	private String History = null;
	private String Examination = null;
	private String Treatment = null;
	

	
	
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
	
	public Integer getID(){
		return this.ID;
	}

	public Integer getPatient_ID(){
		return this.Patient_ID;
	}

	public String getDate(){
		return this.Date;
	}

	public String getHistory(){
		return this.History;
	}

	public String getExamination(){
		return this.Examination;
	}

	public String getTreatment(){
		return this.Treatment;
	}
	
	public void setID(Integer ID){
		this.ID = ID;
	}

	public void setPatient_ID(Integer Patient_ID){
		this.Patient_ID = Patient_ID;
	}

	public void setDate(String Date){
		this.Date = Date;
	}

	public void setHistory(String History){
		this.History = History;
	}

	public void setExamination(String Examination){
		this.Examination = Examination;
	}

	public void setTreatment(String Treatment){
		this.Treatment = Treatment;
	}
}