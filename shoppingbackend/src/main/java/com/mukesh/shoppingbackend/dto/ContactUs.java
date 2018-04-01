package com.mukesh.shoppingbackend.dto;

public class ContactUs {

	private String name;
	private String email;
	private String subject;
	private long phone;
	private String message;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long number) {
		this.phone = number;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ContactUs [name=" + name + ", email=" + email + ", subject=" + subject + ", phone=" + phone
						+ ", message=" + message + "]";
	}

}
