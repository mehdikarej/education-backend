package crocoder.webteam.edukate.DTO;

import java.util.Set;

public class UserResponseDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String specialty;
	private String experience;
	private String phoneNumberChild;
	private String status;
	private String cv;
	private String photo;
	public UserResponseDTO(Long id, String firstName, String lastName, String email, String phoneNumber,
			 String address, String specialty, String experience, String phoneNumberChild,
			String status, String cv, String photo, Set<String> roles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.specialty = specialty;
		this.experience = experience;
		this.phoneNumberChild = phoneNumberChild;
		this.status = status;
		this.cv = cv;
		this.photo = photo;
		this.roles = roles;
	}
	public UserResponseDTO(Long id, String firstName, String lastName, Set<String> roles) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.roles=roles;
	}
	public UserResponseDTO(Long id, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
	}
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	private Set<String> roles;
}
