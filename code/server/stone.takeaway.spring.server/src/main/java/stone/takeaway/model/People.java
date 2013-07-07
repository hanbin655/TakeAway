package stone.takeaway.model;

public class People {
	public String forename;
	public String surname;
 
	
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

	public void setForename(String value) {
		this.forename = value;
	}

	public void setSurname(String value) {
		this.surname = value;
	}

}
