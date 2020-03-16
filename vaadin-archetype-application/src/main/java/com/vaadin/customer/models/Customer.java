package com.vaadin.customer.models;

import java.time.LocalDate;

public class Customer {

	private String id;
	private String firstName;
	private String lastName;
	private String age;
	private String address;
	private CustomerStatus status;
	private LocalDate birthDate;

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Customer(String id, String fName, String lname, String age, String address) {
		this.id = id;
		this.firstName = fName;
		this.lastName = lname;
		this.age = age;
		this.address = address;
	}

	public Customer() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
