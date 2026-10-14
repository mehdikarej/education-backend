package crocoder.webteam.edukate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crocoder.webteam.edukate.DTO.CourseResponseDTO;
import crocoder.webteam.edukate.DTO.CourseTeacherDTO;
import crocoder.webteam.edukate.DTO.MyCourseDTO;
import crocoder.webteam.edukate.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query("SELECT c FROM Course c  WHERE c.user.id=:userId")
	List<Course>findByUserId(@Param("userId")Long userId);
	@Query("SELECT new crocoder.webteam.edukate.DTO.CourseTeacherDTO(" +
		       "c.id, c.name, c.description, c.timming) " +
		       "FROM Course c WHERE c.user.id = :userId")
	List<CourseTeacherDTO>getCourse(@Param("userId")Long userId);	
	@Query("SELECT new crocoder.webteam.edukate.DTO.CourseResponseDTO(" +
		       "c.id, c.name, c.description, c.timming, " +
		       "c.user.id,c.user.firstName, c.user.lastName) " +
		       "FROM Course c")
	List<CourseResponseDTO>getAllCourses();
	@Query("""
			SELECT DISTINCT new crocoder.webteam.edukate.DTO.MyCourseDTO(
			    c.id,
			    c.name,
			    c.description,
			    c.timming,
			    new crocoder.webteam.edukate.DTO.UserResponseDTO(
			        c.user.id,
			        c.user.firstName,
			        c.user.lastName
			    )
			)
			FROM Classe cl
			JOIN cl.students s
			JOIN cl.courses c
			WHERE s.id = :studentId
			""")
			List<MyCourseDTO> getMyCourse(@Param("studentId") Long studentId);
	
				
}
