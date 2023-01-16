package com.bridgelabz;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
/***
 * 
 * @author Admin
 *
 */
public class AddressBookMain {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		 boolean exit = false;
	        while (!exit) {
	            System.out.println(" Press\n 1 ->  Retrieve data\n 2 -> Update Address,city,state,zip  by srNo\n " +
	                    "3 -> Retrieve data for particular date\n" +
	                    " 4 -> Retrieve Count of Contacts for City or State\n" +
	                    " 5 -> Add new Contacts to AddressBook\n" +
	                    " 6 -> exit");
	            int choice = scanner.nextInt();
	            switch (choice) {
	                case 1:
	                    retrieveData();
	                    break;
	                case 2:
	                    update();
	                    break;
	                case 3:
	                	reteriveDataForParticularDate();
	                    break;
	                case 4:
	                    retrieveCountByCityOrState();
	                case 5:
	                	addNewContact();
	                    break;
	                case 6:
	                    exit = true;
	            }
	        }
}
	private static void retrieveCountByCityOrState() {
        AddressBook addressBookRepo = new AddressBook();
        System.out.println("Enter 1 -> Contacts count by City\n" +
                "2 -> Contacts count by State");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Enter city Name");
                int cityContactsCount = addressBookRepo.countByCity(scanner.next());
                System.out.println("Number of Contacts is Given city= " + cityContactsCount);
                break;
            case 2:
                System.out.println("Enter state name");
                int stateContactsCount=  addressBookRepo.countByState(scanner.next());
                System.out.println("Number of Contacts is Given state= " + stateContactsCount);
                break;
        }


    }

	private static void retrieveData() {
		AddressBook addressBookRepo = new AddressBook();
		List<ContactPerson> employeeInfoList = addressBookRepo.retrieveData();
		for (ContactPerson employee : employeeInfoList) {
			System.out.println(employee + "\n");
		}
	}

	private static void update() {
		AddressBook addressBookRepo = new AddressBook();
		System.out.println("Enter the address,city,state, zip and id  to Update");
		addressBookRepo.updateCityByZip(scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),
				scanner.nextInt());
	}
	private static void reteriveDataForParticularDate() {
        AddressBook addressBook = new AddressBook();
        System.out.println("Enter the Date of Joining (YYYY-MM-DD");
        System.out.println("Enter year , month and Day ex: 2020 02 03");
        List<ContactPerson> employeeInfoList = addressBook.findAllForParticularDate(LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        for (ContactPerson employee : employeeInfoList)
         {
            System.out.println(employee + "\n");
        }
    }
	  private static void addNewContact() throws SQLException {
		  ContactPerson Object = new ContactPerson();
	        System.out.println("Enter First Name:");
	        Object.setFirstName(scanner.next());
	        System.out.println("Enter Last name:");
	        Object.setLastName(scanner.next());
	        System.out.println("Enter address");
	        Object.setAddress(scanner.next());
	        System.out.println("Enter city");
	        Object.setCity(scanner.next());
	        System.out.println("Enter state");
	        Object.setState(scanner.next());
	        System.out.println("Enter Zip");
	        Object.setZip(scanner.nextInt());
	        System.out.println("Enter PhoneNumber");
	        Object.setPhoneNumber(scanner.next());
	        System.out.println("Enter Email");
	        Object.setEmailId(scanner.next());
	        System.out.println("Enter Addressbook name");
	        Object.setBookName(scanner.next());
	        System.out.println("Enter date");
	        Object.setDateAdded(LocalDate.now());
	        AddressBook.insertData(Object);

	    }

}