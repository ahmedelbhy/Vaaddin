package com.vaadin.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.customer.models.Customer;

public enum CustomerService {
	INSTANCE;

	private static List<Customer> customerDB = new ArrayList<Customer>();

	public void add(Customer custData) {
		if (custData != null)
			customerDB.add(custData);
	}

	public void update(Customer custData) {
		Optional<Customer> data = customerDB.stream().filter(cust -> cust.getId() == custData.getId()).findFirst();
		if (data.isPresent()) {
			Customer dbCustomer = data.get();
			dbCustomer.setFirstName(custData.getFirstName());
			dbCustomer.setLastName(custData.getLastName());
			dbCustomer.setAge(custData.getAge());
			dbCustomer.setAddress(custData.getAddress());
		}
	}

	public void delete(Customer custData) {
		customerDB.remove(custData);
	}

	public Customer findById(int id) {
		return customerDB.get(id);
	}

	public Customer findByName(String fname) {
		Optional<Customer> data = customerDB.stream()
				.filter(cust -> cust != null && fname.equalsIgnoreCase(cust.getFirstName())).findFirst();
		return data.isPresent() ? data.get() : null;
	}

	public List<Customer> findAll() {
		if (customerDB.isEmpty())
			createDumyData();

		return new ArrayList<>(customerDB);
	}

	private void createDumyData() {
		customerDB.add(new Customer(1 + "", "fname 1", "lname 1", 25 + "", " address 1"));
		customerDB.add(new Customer(2 + "", "fname 2", "lname 2", 25 + "", " address 2"));
		customerDB.add(new Customer(3 + "", "fname 3", "lname 3", 25 + "", " address 3"));
		customerDB.add(new Customer(4 + "", "fname 4", "lname 4", 25 + "", " address 4"));
		customerDB.add(new Customer(5 + "", "fname 5", "lname 5", 25 + "", " address 5"));
		customerDB.add(new Customer(6 + "", "fname 6", "lname 6", 25 + "", " address 6"));
		customerDB.add(new Customer(7 + "", "fname 7", "lname 7", 25 + "", " address 7"));
		customerDB.add(new Customer(8 + "", "fname 8", "lname 8", 25 + "", " address 8"));
		customerDB.add(new Customer(9 + "", "fname 9", "lname 9", 25 + "", " address 9"));
		customerDB.add(new Customer(10 + "", "fname 10", "lname 10", 25 + "", " address 10"));
		customerDB.add(new Customer(11 + "", "fname 11", "lname 11", 25 + "", " address 12"));
		customerDB.add(new Customer(12 + "", "fname 12", "lname 12", 25 + "", " address 13"));

	}

}
