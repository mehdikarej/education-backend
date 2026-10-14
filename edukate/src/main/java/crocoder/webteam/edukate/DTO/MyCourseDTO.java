package crocoder.webteam.edukate.DTO;

public class MyCourseDTO {
	private Long id;
	private String name;
	private String description;
	private String timming;
	private UserResponseDTO teacher;
	public MyCourseDTO(Long id, String name, String description, String timming, UserResponseDTO teacher) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.timming = timming;
		this.teacher = teacher;
	}
	
	public MyCourseDTO(Long id, String name, String description, String timming) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.timming = timming;
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
	public UserResponseDTO getTeacher() {
		return teacher;
	}
	public void setTeacher(UserResponseDTO teacher) {
		this.teacher = teacher;
	}
	

}
