package application;

public class Contact {
	
	private int id;
	private String fName;
	private String lName;
	private ContactGroup group;
	private String phoneNumber;
	private Adress address;
	private String emailAddress;

	public Contact(String fName, String lName, ContactGroup group, String phoneNumber, Adress address,
			String emailAddress) {
		this.fName = fName;
		this.lName = lName;
		this.group = group;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.emailAddress = emailAddress;
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

	public ContactGroup getGroup() {
		return group;
	}

	public void setGroup(ContactGroup group) {
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAdress) {
		this.emailAddress = emailAdress;
	}
}
