package crocoder.webteam.edukate.DTO;

public class EvalutionRequest {
	private Integer note;
	private String remark;
	private Long classeId;
	private Long courseId;
	private Long studentId;
	private Long teacherId;
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
	public Long getClasseId() {
		return classeId;
	}
	public void setClasseId(Long classeId) {
		this.classeId = classeId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	

}
