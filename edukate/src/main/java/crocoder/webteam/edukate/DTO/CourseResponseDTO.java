package crocoder.webteam.edukate.DTO;

public class CourseResponseDTO {
	private Long id;
	private String name;
	private String description;
	private String timming;
	private Long userId;
	private String firstName;
	private String lastName;
	public CourseResponseDTO(Long id,String name, String description, String timming) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.description=description;
		this.timming=timming;
	}
	public CourseResponseDTO(Long id, String name, String description, String timming, Long userId, String firstName,
			String lastName) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.timming = timming;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public CourseResponseDTO(Long id2, String name) {
		// TODO Auto-generated constructor stub
		this.id=id2;
		this.name=name;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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


	

}
