package crocoder.webteam.edukate.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Classe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name; 
    @ManyToOne
    @JsonIgnoreProperties({"classes", "students"})
    private User teacher;
    @ManyToMany
    @JoinTable(
        name = "classe_students",
        joinColumns = @JoinColumn(name = "classe_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> students;
    // Courses 👇
    @ManyToMany
    @JoinTable(
        name = "classe_course",
        joinColumns =@JoinColumn(name = "classe_id"),
        inverseJoinColumns =@JoinColumn(name = "course_id")
    )
    @JsonIgnoreProperties({"classes"})
    private List<Course> courses;
    @OneToMany(mappedBy="classe")
    @JsonIgnoreProperties({"classe"})
    private List<Evaluation>evaluations;
    
	public Classe() {
		// TODO Auto-generated constructor stub
	}

	public Classe(Long id, String name, User teacher, List<User> students, List<Course> courses,
			List<Evaluation> evaluations) {
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.students = students;
		this.courses = courses;
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

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	@Override
	public String toString() {
		return "Classe [id=" + id + ", name=" + name + ", teacher=" + teacher + ", students=" + students + ", courses="
				+ courses + ", evaluations=" + evaluations + "]";
	}
	

	
	
    
}
