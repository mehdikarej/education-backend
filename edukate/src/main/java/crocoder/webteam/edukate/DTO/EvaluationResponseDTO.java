package crocoder.webteam.edukate.DTO;

public class EvaluationResponseDTO {
	private Long evaluationId;
	private Integer note;
	private String remark;
	private UserResponseDTO user;
	private CourseResponseDTO course;
	public EvaluationResponseDTO() {
		
	}
	public EvaluationResponseDTO(Long evaluationId, Integer note, String remark, UserResponseDTO user,
			CourseResponseDTO course) {
		this.evaluationId = evaluationId;
		this.note = note;
		this.remark = remark;
		this.user = user;
		this.course = course;
		
	}
	public Long getEvaluationId() {
		return evaluationId;
	}
	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
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
	public UserResponseDTO getUser() {
		return user;
	}
	public void setUser(UserResponseDTO user) {
		this.user = user;
	}
	public CourseResponseDTO getCourse() {
		return course;
	}
	public void setCourse(CourseResponseDTO course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return "EvaluationResponseDTO [evaluationId=" + evaluationId + ", note=" + note + ", remark=" + remark
				+ ", user=" + user + ", course=" + course + "]";
	}
	
	
	

}
