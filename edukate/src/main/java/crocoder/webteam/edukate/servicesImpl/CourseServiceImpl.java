package crocoder.webteam.edukate.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crocoder.webteam.edukate.DTO.CourseResponseDTO;
import crocoder.webteam.edukate.DTO.CourseTeacherDTO;
import crocoder.webteam.edukate.DTO.MyCourseDTO;
import crocoder.webteam.edukate.DTO.UserResponseDTO;
import crocoder.webteam.edukate.models.Course;
import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.CourseRepository;
import crocoder.webteam.edukate.services.AuthService;
import crocoder.webteam.edukate.services.CourseService;
@Service
public class CourseServiceImpl implements CourseService{
@Autowired
private CourseRepository courseRepository;
@Autowired
private AuthService authService;
@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}

	@Override
	public CourseTeacherDTO getCourseById(Long id) {
		// TODO Auto-generated method stub
		
		Course course= courseRepository.findById(id).orElseThrow(()->
		new RuntimeException("Course not found with id"+id)) ;
	    User user = course.getUser();

	    UserResponseDTO teacherDTO = new UserResponseDTO(
	            user.getId(),
	            user.getFirstName(),
	            user.getLastName()
	            
	    );
		return new CourseTeacherDTO(course.getId(),course.getName(),course.getDescription(),course.getTimming(),teacherDTO);
	}

	@Override
	public void deleteCourse(Long id) {
		// TODO Auto-generated method stub
		courseRepository.deleteById(id);
		
	}

	@Override
	public Course editCourse(Long id, Course course) {
		// TODO Auto-generated method stub
		Course existingCourse=courseRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Course not found with"+id));
		existingCourse.setName(course.getName());
		existingCourse.setDescription(course.getDescription());
		existingCourse.setTimming(course.getTimming());
		return courseRepository.save(existingCourse);
	}

	@Override
	public List<CourseResponseDTO> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.getAllCourses();
	}

	@Override
	public List<CourseTeacherDTO> getCourse(Long userId) {
		// TODO Auto-generated method stub
		List<Course> courses = courseRepository.findByUserId(userId);

	    return courses.stream().map(course -> {
	        User user = course.getUser();

	        UserResponseDTO teacher = new UserResponseDTO(
	            user.getId(),
	            user.getFirstName(),
	            user.getLastName()
	        );

	        return new CourseTeacherDTO(
	            course.getId(),
	            course.getName(),
	            course.getDescription(),
	            course.getTimming(),
	            teacher
	        );
	    }).collect(Collectors.toList());
	    		
	}

	@Override
	public List<MyCourseDTO> getMyCourse(Long studentId) {
		// TODO Auto-generated method stub
		
		return courseRepository.getMyCourse(studentId);
	}
	

}
