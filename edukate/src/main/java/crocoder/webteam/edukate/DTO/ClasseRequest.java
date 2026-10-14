package crocoder.webteam.edukate.DTO;

import java.util.List;



public class ClasseRequest {
	private String name; 
    private Long teacherId;
    private List<Long> studentsIds;
    private List<Long>  courseId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public List<Long> getStudentsIds() {
		return studentsIds;
	}
	public void setStudentsIds(List<Long> studentsIds) {
		this.studentsIds = studentsIds;
	}
	public List<Long> getCourseId() {
		return courseId;
	}
	public void setCourseId(List<Long> courseId) {
		this.courseId = courseId;
	}
	

}
