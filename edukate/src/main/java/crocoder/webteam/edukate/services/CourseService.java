package crocoder.webteam.edukate.services;

import java.util.List;

import crocoder.webteam.edukate.DTO.CourseResponseDTO;
import crocoder.webteam.edukate.DTO.CourseTeacherDTO;
import crocoder.webteam.edukate.DTO.MyCourseDTO;
import crocoder.webteam.edukate.models.Course;

public interface CourseService {
	Course addCourse(Course course);
	CourseTeacherDTO getCourseById(Long id);
	void deleteCourse(Long id);
	Course editCourse(Long id,Course course);
	List<CourseResponseDTO> getAllCourses();
	List<CourseTeacherDTO>getCourse(Long userId);
	List<MyCourseDTO>getMyCourse(Long studentId);
	

}
