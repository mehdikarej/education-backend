package crocoder.webteam.edukate.DTO;

import java.util.ArrayList;
import java.util.List;

public class SignupRequest {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String address;
	private String specialty;
	private String experience;
	private String phoneNumberChild;
	private String status;
	private String cv;
	private String photo;
	private List<String> roles = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getPhoneNumberChild() {
		return phoneNumberChild;
	}
	public void setPhoneNumberChild(String phoneNumberChild) {
		this.phoneNumberChild = phoneNumberChild;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
