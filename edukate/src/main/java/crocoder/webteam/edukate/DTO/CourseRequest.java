package crocoder.webteam.edukate.DTO;

public class CourseRequest {
	private String name;
	private String description;
	private String timming;
	private Long userId;
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
	
}
