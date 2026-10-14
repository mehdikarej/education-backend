package crocoder.webteam.edukate.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import crocoder.webteam.edukate.DTO.ClasseRequest;
import crocoder.webteam.edukate.DTO.ClasseResponseDTO;
import crocoder.webteam.edukate.models.Classe;
import crocoder.webteam.edukate.models.Course;
import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.ClasseRepository;
import crocoder.webteam.edukate.repositories.CourseRepository;
import crocoder.webteam.edukate.repositories.UserRepository;
import crocoder.webteam.edukate.services.ClasseService;
import crocoder.webteam.edukate.services.UserService;


@RestController
@RequestMapping("/api/classes")
public class ClassesController {
	  @Autowired
	    private ClasseRepository classeRepository;

	    @Autowired
	    private UserRepository userRepository;
        @Autowired
        private CourseRepository courseRepository;
	    @Autowired
	    private ClasseService classeService;
	    @Autowired
	    private UserService userService;
	    //add classe
	    @PostMapping
	    public ResponseEntity<Classe> addClasse(@RequestBody ClasseRequest request) {

	        Classe classe = new Classe();

	        classe.setName(request.getName());

	        User teacher = userRepository.findById(request.getTeacherId())
	                .orElseThrow(() -> new RuntimeException("Teacher Not found"));

	        classe.setTeacher(teacher);

	        List<User> students = userRepository.findAllById(request.getStudentsIds());

	        classe.setStudents(students);

	        List<Course> courses = courseRepository.findAllById(request.getCourseId());

	        classe.setCourses(courses);

	        classeRepository.save(classe);

	        return ResponseEntity.ok(classe);
	    }
	    //get ClasseById
	    @GetMapping("/{id}")
	    public ResponseEntity<Classe>getClasseById(@PathVariable Long id){
	    	Classe classe=classeService.findClasseById(id);
	    	if(classe==null) {
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    	}
	    	return ResponseEntity.ok(classe);
	    }
	    //edit Classe
	    @PutMapping("/{id}")
	    public ClasseResponseDTO editClasse(@PathVariable Long id,@RequestBody ClasseRequest request) {

	       return classeService.editClasse(id, request);
	    }
	    
	    //get AllClasses
@GetMapping
public List<ClasseResponseDTO>getAllClasses(){
	  System.out.println("API called...");
	return classeService.getAllClasses();
}
//delete Classe
@DeleteMapping("{id}")
public ResponseEntity<Void>deleteClasse(@PathVariable Long id){
	classeService.deleteClasse(id);
	return ResponseEntity.noContent().build();
	
}
//get MyClasses
@GetMapping("/MyClasses")
public ResponseEntity<List<ClasseResponseDTO>>getMyClasses(Authentication authentication){
    if (authentication == null) {
        return ResponseEntity.status(401).build();
    }

    String email = authentication.getName();

    User user = userService.getUserByEmail(email);

    if (user == null) {
        return ResponseEntity.status(404).build();
    }
    List<ClasseResponseDTO>classes=classeService.getMyClasses(user.getId());
    return ResponseEntity.ok(classes);
}
			
}
