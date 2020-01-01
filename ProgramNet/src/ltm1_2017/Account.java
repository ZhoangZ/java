package ltm1_2017;

public class Account {
	public String acountNumber;
	public String pinCode;

	public String getAcountNumber() {
		return acountNumber;
	}

	public void setAcountNumber(String acountNumber) {
		this.acountNumber = acountNumber;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Account(String acountNumber, String pinCode) {
		super();
		this.acountNumber = acountNumber;
		this.pinCode = pinCode;
	}

	public Account() {
	}

	public Account(String acountNumber) {
		super();
		this.acountNumber = acountNumber;
	}
	
}
