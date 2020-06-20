package restservice.model;

public class ZipCode {

	int zipCode;
	String city;
	String state;
	Double taxRate;

	public ZipCode() {
		zipCode = 0;
		city = "";
		state = "";
		taxRate = 0.0;
	}

	public void setZipCode(int z) {
		zipCode = z;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setCity(String c) {
		city = c;
	}

	public String getCity() {
		return city;
	}

	public void setState(String st) {
		state = st;
	}

	public String getState() {
		return state;
	}

	public void setTaxRate(double rate) {
		taxRate = rate;
	}

	public Double getTaxRate() {
		return taxRate;
	}
}