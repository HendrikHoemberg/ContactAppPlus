package application;

public class Contact {
	
	private int id;
	private String fName;
	private String lName;
	private String group;
	private String phoneNumber;
	private Adress address;
	private String street;
	private String houseNr;
	private String zip;
	private String city;
	private String state;
	private String country;
	private String emailAddress;

	public Contact(int id, String fName, String lName, String group, String phoneNumber, Adress address,
			String emailAddress) {
		this.fName = fName;
		this.lName = lName;
		this.group = group;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.emailAddress = emailAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.phoneNumber = telephoneNumber;
	}

	public Adress getAddress() {
		return address;
	}

	public void setAddress(Adress adress) {
		this.address = adress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNr() {
		return houseNr;
	}

	public void setHouseNr(String houseNr) {
		this.houseNr = houseNr;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAdress) {
		this.emailAddress = emailAdress;
	}
}
