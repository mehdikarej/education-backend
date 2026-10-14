package crocoder.webteam.edukate.DTO;

import java.util.List;

public class ClasseResponseDTO {
	private Long classeId;
    private String name;
    private List<UserResponseDTO>users;
    private List<CourseResponseDTO>courses;
	public ClasseResponseDTO() {
		
		// TODO Auto-generated constructor stub
	}
	public ClasseResponseDTO(Long classeId, String name, List<UserResponseDTO> users, List<CourseResponseDTO> courses) {
		this.classeId = classeId;
		this.name = name;
		this.users = users;
		this.courses = courses;
	}
	public ClasseResponseDTO(Long id, String name2) {
		// TODO Auto-generated constructor stub
		this.classeId=id;
		this.name=name2;
		
	}
	public Long getClasseId() {
		return classeId;
	}
	public void setClasseId(Long classeId) {
		this.classeId = classeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UserResponseDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserResponseDTO> users) {
		this.users = users;
	}
	public List<CourseResponseDTO> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseResponseDTO> courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "ClasseResponseDTO [classeId=" + classeId + ", name=" + name + ", users=" + users + ", courses="
				+ courses + "]";
	}
	
    
	
}
