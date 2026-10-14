package crocoder.webteam.edukate.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
@Id	
@GeneratedValue(strategy =GenerationType.IDENTITY )
@Column(name="COURSE_ID")
private Long id;
private String name;
private String description;
private String timming;
@ManyToOne
@JoinColumn(name="user_id")
@JsonIgnoreProperties({"classes"})
private User user;
@OneToMany(mappedBy="course")
@JsonIgnoreProperties({"course"})
private List<Evaluation>evaluations;
public Course() {

	// TODO Auto-generated constructor stub
}
public Course(Long id, String name, String description, String timming, User user, List<Evaluation> evaluations) {
	
	this.id = id;
	this.name = name;
	this.description = description;
	this.timming = timming;
	this.user = user;
	this.evaluations = evaluations;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getTimming() {
	return timming;
}
public void setTimming(String timming) {
	this.timming = timming;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public List<Evaluation> getEvaluations() {
	return evaluations;
}
public void setEvaluations(List<Evaluation> evaluations) {
	this.evaluations = evaluations;
}
@Override
public String toString() {
	return "Course [id=" + id + ", name=" + name + ", description=" + description + ", timming=" + timming + ", user="
			+ user + ", evaluations=" + evaluations + "]";
}




}
