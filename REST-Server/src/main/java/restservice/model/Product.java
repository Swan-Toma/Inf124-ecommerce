//  Jersey uses objects to contruct requests and responses.

package restservice.model;

public class Product {
	private int pid;
	private double price;
	private String name;
	private String image;
	private String description;
	private String details;

	public Product() {
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return pid;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
}