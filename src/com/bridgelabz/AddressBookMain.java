package com.bridgelabz;
import java.util.List;
import java.util.Scanner;
/***
 * 
 * @author Admin
 *
 */
public class AddressBookMain {
	 static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {

	        boolean exit = false;
	        while (!exit) {
	            System.out.println(" Press\n 1 ->  Retrieve data\n 2 -> Exit");
	            switch (scanner.nextInt()) {
	                case 1:
	                    retrieveData();
	                    break;
	                case 2:
	                    exit = true;
	            }
	        }
	    }

	    private static void retrieveData() {
	        AddressBook addressBookRepo = new AddressBook();
	        List<ContactPerson> employeeInfoList = addressBookRepo.retrieveData();
	        for (ContactPerson employee : employeeInfoList)
	         {
	            System.out.println(employee + "\n");
	        }
	    }

	}

