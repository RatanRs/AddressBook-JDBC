package com.bridgelabz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/***
 * 
 * @author Admin
 *
 */
public class AddressBook {
	Connection connection;

	private static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers loaded!!");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/addressbookservice?allowPublicKeyRetrieval=true&useSSL=false",
					"root", "Root");
			System.out.println("connection Established!!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public List<ContactPerson> retrieveData() {
		ResultSet resultSet = null;
		List<ContactPerson> addressBookList = new ArrayList<ContactPerson>();
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from addressbook;");
			int count = 0;
			while (resultSet.next()) {
				ContactPerson contactInfo = new ContactPerson();
				contactInfo.setFirstName(resultSet.getString("firstName"));
				contactInfo.setLastName(resultSet.getString("lastname"));
				contactInfo.setAddress(resultSet.getString("address"));
				contactInfo.setCity(resultSet.getString("city"));
				contactInfo.setState(resultSet.getString("state"));
				contactInfo.setZip(resultSet.getInt("zip"));
				contactInfo.setPhoneNumber(resultSet.getString("PhoneNumber"));
				contactInfo.setEmailId(resultSet.getString("email"));
				contactInfo.setDateAdded(resultSet.getDate("dateadded").toLocalDate());
				contactInfo.setBookName(resultSet.getString("bookName"));

				addressBookList.add(contactInfo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return addressBookList;

	}
	
	
	
	public void updateCityByZip(String address, String city, String state, int zip, int id) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "Update Address_book_database set address=" + "'" + address + "'" + ", " + "city=" + "'" + city + "'" + ", " + "state=" + "'" + state + "'" + ", " + "zip=" + zip + " where id=" + id + ";";
            int result = statement.executeUpdate(query);
            System.out.println(result);
            if (result > 0) {
                System.out.println("Address Updated Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	   public List<ContactPerson> findAllForParticularDate(LocalDate date) {
	        ResultSet resultSet = null;
	        List<ContactPerson> addressBookList = new ArrayList<ContactPerson>();
	        try (Connection connection = getConnection()) {
	            Statement statement = connection.createStatement();
	            String sql = "select * from Address_book_database where dateadded between cast(' "+ date + "'" +" as date)  and date(now());";
	            resultSet = statement.executeQuery(sql);
	            int count = 0;
	            while (resultSet.next()) {
	            	ContactPerson contactInfo = new ContactPerson();
	                contactInfo.setFirstName(resultSet.getString("firstName"));
	                contactInfo.setLastName(resultSet.getString("lastname"));
	                contactInfo.setAddress(resultSet.getString("address"));
	                contactInfo.setCity(resultSet.getString("city"));
	                contactInfo.setState(resultSet.getString("state"));
	                contactInfo.setZip(resultSet.getInt("zip"));
	                contactInfo.setPhoneNumber(resultSet.getString("PhoneNumber"));
	                contactInfo.setEmailId(resultSet.getString("email"));
	                contactInfo.setBookName(resultSet.getString("bookname"));
	                contactInfo.setDateAdded(resultSet.getDate("dateadded").toLocalDate());

	                addressBookList.add(contactInfo);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        return addressBookList;
	    }
	  
}