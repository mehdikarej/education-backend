package crocoder.webteam.edukate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Evaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer note;
	private String remark;
	@ManyToOne
	@JoinColumn(name="classe_id")
	@JsonIgnore
	private Classe classe;
	@ManyToOne(optional = false)
	@JoinColumn(name="course_id",nullable = false)
	@JsonIgnore
	private Course course;
	@ManyToOne
	@JoinColumn(name="student_id")
	@JsonIgnore
	private User student;
	@ManyToOne
	@JoinColumn(name="teacher_id")
	@JsonIgnore
	private User teacher;
	
	public Evaluation() {
	
	}
	public Evaluation(Long id, Integer note, String remark, Classe classe, Course course, User student, User teacher) {
		this.id = id;
		this.note = note;
		this.remark = remark;
		this.classe = classe;
		this.course = course;
		this.student = student;
		this.teacher = teacher;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", note=" + note + ", remark=" + remark + ", classe=" + classe + ", course="
				+ course + ", student=" + student + ", teacher=" + teacher + "]";
	}
	
	

}
