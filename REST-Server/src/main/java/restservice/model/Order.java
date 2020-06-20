package restservice.model;

public class Order {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String shippingAddress;
	private int zipCode;
	private String shippingMethod;
	private long creditCard;
	private int expirationMonth;
	private int expirationYear;
	private int securityCode;
	private double priceTotal;
	private String pids;
	private String city;
	private String state;
	private int oid;

	public Order() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		lastName = lName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String pNumber) {
		phoneNumber = pNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String sAdress) {
		shippingAddress = sAdress;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zCode) {
		zipCode = zCode;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shipMethod) {
		shippingMethod = shipMethod;
	}

	public long getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(long ccn) {
		creditCard = ccn;
	}

	public int getExpMonth() {
		return expirationMonth;
	}

	public void setExpMonth(int expM) {
		expirationMonth = expM;
	}

	public int getExpYear() {
		return expirationYear;
	}

	public void setExpYear(int expY) {
		expirationYear = expY;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int sc) {
		securityCode = sc;
	}

	public double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(double p) {
		priceTotal = p;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String orderPids) {
		pids = orderPids;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String cty) {
		city = cty;
	}

	public String getState() {
		return state;
	}

	public void setState(String st) {
		state = st;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int id) {
		oid = id;
	}
}