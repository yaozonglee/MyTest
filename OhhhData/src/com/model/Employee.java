package com.model;

public class Employee {

	private String name;
	private String department;
	private int contact;

	public Employee(String name, String department, int contact) {
		super();
		this.name = name;
		this.department = department;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}


}
