package crocoder.webteam.edukate.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crocoder.webteam.edukate.DTO.CourseRequest;
import crocoder.webteam.edukate.DTO.CourseResponseDTO;
import crocoder.webteam.edukate.DTO.CourseTeacherDTO;
import crocoder.webteam.edukate.DTO.MyCourseDTO;
import crocoder.webteam.edukate.models.Course;
import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.services.CourseService;
import crocoder.webteam.edukate.services.UserService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	//add Course
	@PostMapping(consumes =MediaType.APPLICATION_JSON_VALUE)
	public CourseResponseDTO addCourse(@RequestBody CourseRequest request,Authentication  authentication) {
		String email=authentication.getName();
		User user=userService.getUserByEmail(email);
		Course  course=new Course();
		course.setName(request.getName());
		course.setDescription(request.getDescription());
		course.setTimming(request.getTimming());
		course.setUser(user);
		Course savedCourse=courseService.addCourse(course);
         return mapToresponse(savedCourse);
	}
	private CourseResponseDTO mapToresponse(Course course) {
		CourseResponseDTO response=new CourseResponseDTO(null, null, null, null, null, null, null);
		response.setId(course.getId());
		response.setName(course.getName());
		response.setDescription(course.getDescription());
		response.setTimming(course.getTimming());
		if(course.getUser()!=null) {
			response.setUserId(course.getUser().getId());
			response.setFirstName(course.getUser().getFirstName());
			response.setLastName(course.getUser().getLastName());
			
			
		}
		return response;
}
	//Get CourseTeacher
	@GetMapping("/TeacherCourse")
	public ResponseEntity<List<CourseTeacherDTO>>getCourse(Authentication authentication){
		 if (authentication == null) {
	            return ResponseEntity.status(401).build();
	        }

	        String email = authentication.getName();

	        User user = userService.getUserByEmail(email);

	        if (user == null) {
	            return ResponseEntity.status(404).build();
	        }

	        List<CourseTeacherDTO> courses = courseService.getCourse(user.getId());

	        return ResponseEntity.ok(courses);
	    }
	//Edit Course
	@PutMapping("/{id}")
	public ResponseEntity<?>editCourse(@PathVariable Long id,@RequestBody Course course){
		try {
			Course updatedCourse=courseService.editCourse(id, course);
			return ResponseEntity.ok(updatedCourse);
			
		}
		catch(RuntimeException ex)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("message",ex.getMessage()));
			
		}
	}
	
	//Get CourseById
	@GetMapping("/{id}")
	public CourseTeacherDTO getCourseById(@PathVariable Long id) {
		return courseService.getCourseById(id);
	}
	//Delete Course
	@DeleteMapping("{id}")
	public ResponseEntity<Void>  deleteCourse(@PathVariable Long id){
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
	//Get All Courses
	@GetMapping
	public List<CourseResponseDTO>getAllCourses(){
		return courseService.getAllCourses();
	}
	// get MyCourse
	@GetMapping("/MyCourse")
	public ResponseEntity<List<MyCourseDTO>> getMyCourseDTO(Authentication authentication) {

	    if (authentication == null) {
	        return ResponseEntity.status(401).build();
	    }

	    String email = authentication.getName();

	    User user = userService.getUserByEmail(email);

	    if (user == null) {
	        return ResponseEntity.status(404).build();
	    }

	    List<MyCourseDTO> courses = courseService.getMyCourse(user.getId());

	    return ResponseEntity.ok(courses);
	}
}
