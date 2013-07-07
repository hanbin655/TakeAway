package stone.takeaway.model;

public class People {
	public String forename;
	public String surname;
	public String fullname;
 
	
	public People(String forname, String surname){
		this.setForename(forname);
		this.setSurname(surname);		
	}
	
	public String getForename() {
		return this.forename;
	}

	public String getSurname() {
		return this.surname;
	}
	
	public String getFullname() {
		return this.fullname;
	}
	
	public void setFullname() {
		this.fullname = this.forename + " " + this.surname;		
	}
	
	public void setForename(String value) {
		this.forename = value;
		setFullname();
	}

	public void setSurname(String value) {
		this.surname = value;
		setFullname();
	}

}
