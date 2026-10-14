package crocoder.webteam.edukate.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")

public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="USER_ID")
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
@ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
private List<Role> roles = new ArrayList<>();
@OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
@JsonIgnore
private List<Course> courses=new ArrayList<>();
@OneToMany(mappedBy = "student")
@JsonIgnore
private List<Evaluation> studentEvaluations;

@OneToMany(mappedBy = "teacher")
@JsonIgnore
private List<Evaluation> teacherEvaluations;
public User() {

	// TODO Auto-generated constructor stub
}
public User(Long id, String firstName, String lastName, String email, String phoneNumber, String password,
		String address, String specialty, String experience, String phoneNumberChild, String status, String cv,
		String photo, List<Role> roles, List<Course> courses, List<Evaluation> studentEvaluations,
		List<Evaluation> teacherEvaluations) {
	this.id=id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.password = password;
	this.address = address;
	this.specialty = specialty;
	this.experience = experience;
	this.phoneNumberChild = phoneNumberChild;
	this.status = status;
	this.cv = cv;
	this.photo = photo;
	this.roles = roles;
	this.courses = courses;
	this.studentEvaluations = studentEvaluations;
	this.teacherEvaluations = teacherEvaluations;
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
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
}
public List<Course> getCourses() {
	return courses;
}
public void setCourses(List<Course> courses) {
	this.courses = courses;
}
public List<Evaluation> getStudentEvaluations() {
	return studentEvaluations;
}
public void setStudentEvaluations(List<Evaluation> studentEvaluations) {
	this.studentEvaluations = studentEvaluations;
}
public List<Evaluation> getTeacherEvaluations() {
	return teacherEvaluations;
}
public void setTeacherEvaluations(List<Evaluation> teacherEvaluations) {
	this.teacherEvaluations = teacherEvaluations;
}
@Override
public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
			+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", address=" + address + ", specialty="
			+ specialty + ", experience=" + experience + ", phoneNumberChild=" + phoneNumberChild + ", status=" + status
			+ ", cv=" + cv + ", photo=" + photo + ", roles=" + roles + ", courses=" + courses + ", studentEvaluations="
			+ studentEvaluations + ", teacherEvaluations=" + teacherEvaluations + "]";
}




}
