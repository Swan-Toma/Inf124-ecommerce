package restservice.database;

import java.sql.*;
import restservice.model.Order;

public class DatabaseUtils {

	public static ResultSet retrieveQueryResults(Connection connection, final String sql) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

        
	public static ResultSet retrieveQueryResultsWithParam(Connection connection, final String sql, Object param) {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, (Integer) param);
			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;

	}

        
	public static boolean performDBUpdate(Connection connection, String sql, Order o) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, o.getFirstName());
			preparedStatement.setString(2, o.getLastName());
			preparedStatement.setString(3, o.getPhoneNumber());
			preparedStatement.setString(4, o.getShippingAddress());
			preparedStatement.setLong(5, o.getZipCode());
			preparedStatement.setString(6, o.getShippingMethod());
			preparedStatement.setLong(7, o.getCreditCard());
			preparedStatement.setInt(8, o.getExpMonth());
			preparedStatement.setInt(9, o.getExpYear());
			preparedStatement.setInt(10, o.getSecurityCode());
			preparedStatement.setDouble(11, o.getPriceTotal());
			preparedStatement.setString(12, o.getPids());
			preparedStatement.setString(13, o.getCity());
			preparedStatement.setString(14, o.getState());

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			return false;
		}
	}

        
	public static int performDBOrderInsert(Connection connection, String sql, Order o) {
		PreparedStatement preparedStatement = null;
		int numAffectedRows;
		int oid;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, o.getFirstName());
			preparedStatement.setString(2, o.getLastName());
			preparedStatement.setString(3, o.getPhoneNumber());
			preparedStatement.setString(4, o.getShippingAddress());
			preparedStatement.setLong(5, o.getZipCode());
			preparedStatement.setString(6, o.getShippingMethod());
			preparedStatement.setLong(7, o.getCreditCard());
			preparedStatement.setInt(8, o.getExpMonth());
			preparedStatement.setInt(9, o.getExpYear());
			preparedStatement.setInt(10, o.getSecurityCode());
			preparedStatement.setDouble(11, o.getPriceTotal());
			preparedStatement.setString(12, o.getPids());
			preparedStatement.setString(13, o.getCity());
			preparedStatement.setString(14, o.getState());

			numAffectedRows = preparedStatement.executeUpdate();

			if (numAffectedRows == 0) {
				throw new SQLException("Inserting into products_in_orders failed");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					oid = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Inserting into products_in_orders failed, no ID obtained.");
				}
			}
			return oid;
		} catch (SQLException e) {
			return -1;
		}
	}
}
