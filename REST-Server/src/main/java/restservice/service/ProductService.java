package restservice.service;

import restservice.database.DatabaseConnector;
import restservice.database.DatabaseUtils;
import restservice.model.Product;
import restservice.model.Order;
import restservice.model.Category;
import restservice.model.ZipCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    
	private final static String ALL_PRODUCTS_QUERY = "SELECT * FROM products";
	private final static String ORDER_QUERY = "SELECT * FROM orders WHERE oid = ";

	public static Product getProductByPid(int pid) {
		// Get a new connection object before going forward with the JDBC invocation.
		Connection connection = DatabaseConnector.getConnection();
		ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection,
				ALL_PRODUCTS_QUERY + " WHERE pid = " + pid);

		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					Product product = new Product();
					product.setPid(resultSet.getInt("pid"));
					product.setDescription(resultSet.getString("description"));
					product.setDetails(resultSet.getString("details"));
					product.setImage(resultSet.getString("image"));
					product.setPrice(resultSet.getDouble("price"));
					product.setName(resultSet.getString("name"));
					return product;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// We will always close the connection once we are done interacting with the
					// Database.
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
        

	public static List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();

		Connection connection = DatabaseConnector.getConnection();
		ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_PRODUCTS_QUERY);

		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					Product p = new Product();
					p.setPid(resultSet.getInt("pid"));
					p.setDescription(resultSet.getString("description"));
					p.setDetails(resultSet.getString("details"));
					p.setImage(resultSet.getString("image"));
					p.setPrice(resultSet.getDouble("price"));
					p.setName(resultSet.getString("name"));

					products.add(p);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return products;
	}

        
	public static int AddOrder(Order order) {
		String sql = "INSERT INTO orders (first_name, last_name, phone_number, shipping_address, zip_code, "
				+ "shipping_method, credit_card, expiration_month, expiration_year, security_code, price_total, pids, city, state)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = DatabaseConnector.getConnection();
		return DatabaseUtils.performDBOrderInsert(connection, sql, order);
	}

       
	public static Order getOrderByOid(int oid) {
		// Get a new connection object before going forward with the JDBC invocation.
		Connection connection = DatabaseConnector.getConnection();
		ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ORDER_QUERY + oid);

		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					Order order = new Order();
					order.setOid(resultSet.getInt("oid"));
					order.setFirstName(resultSet.getString("first_name"));
					order.setLastName(resultSet.getString("last_name"));
					order.setPhoneNumber(resultSet.getString("phone_number"));
					order.setZipCode(resultSet.getInt("zip_code"));
					order.setShippingAddress(resultSet.getString("shipping_address"));
					order.setShippingMethod(resultSet.getString("shipping_method"));
					order.setCreditCard(resultSet.getLong("credit_card"));
					order.setExpMonth(resultSet.getInt("expiration_month"));
					order.setExpYear(resultSet.getInt("expiration_year"));
					order.setSecurityCode(resultSet.getInt("security_code"));
					order.setPriceTotal(resultSet.getDouble("price_total"));
					order.setPids(resultSet.getString("pids"));
					order.setCity(resultSet.getString("city"));
					order.setState(resultSet.getString("state"));
					return order;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// We will always close the connection once we are done interacting with the
					// Database.
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

        
	public static List<Category> getCategoriesByPid(int pid) {
		List<Category> categories = new ArrayList<Category>();

		Connection connection = DatabaseConnector.getConnection();
		// ResultSet resultSetProduct = DatabaseUtils.retrieveQueryResults(connection,
		// ALL_PRODUCTS_QUERY + "WHERE products.pid =" + pid);
		ResultSet resultSetCid = DatabaseUtils.retrieveQueryResults(connection,
				"SELECT c.category FROM products as p, categories as c, product_has_category as pc WHERE p.pid = pc.pid AND c.cid = pc.cid AND p.pid = "
						+ pid);

		if (resultSetCid != null) {
			try {
				while (resultSetCid.next()) {
					Category cat = new Category();
					cat.setCategory(resultSetCid.getString("category"));
					categories.add(cat);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return categories;
	}

        
	public static ZipCode getZipById(int zip) {
		// Get a new connection object before going forward with the JDBC invocation.
		Connection connection = DatabaseConnector.getConnection();
		String zipQuery = "SELECT z.city, z.state, t.combined_rate FROM zip_codes as z, tax_rates as t WHERE z.zip = "
				+ zip + " AND z.zip = t.zip;";
		ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, zipQuery);

		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					ZipCode zipCode = new ZipCode();

					zipCode.setZipCode(zip);
					zipCode.setCity(resultSet.getString("city"));
					zipCode.setState(resultSet.getString("state"));
					zipCode.setTaxRate(resultSet.getDouble("combined_rate"));
					return zipCode;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// We will always close the connection once we are done interacting with the
					// Database.
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
