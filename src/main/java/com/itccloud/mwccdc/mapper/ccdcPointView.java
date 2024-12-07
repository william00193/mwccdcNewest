package com.itccloud.mwccdc.mapper;

import java.time.LocalDate;

public class ccdcPointView {
	
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String firstoccupation;
	private String preferredstand;
	private LocalDate reservationtime;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstoccupation() {
		return firstoccupation;
	}
	public void setFirstoccupation(String firstoccupation) {
		this.firstoccupation = firstoccupation;
	}
	public String getPreferredstand() {
		return preferredstand;
	}
	public void setPreferredstand(String preferredstand) {
		this.preferredstand = preferredstand;
	}
	public LocalDate getReservationtime() {
		return reservationtime;
	}
	public void setReservationtime(LocalDate reservationtime) {
		this.reservationtime = reservationtime;
	}
	

}
