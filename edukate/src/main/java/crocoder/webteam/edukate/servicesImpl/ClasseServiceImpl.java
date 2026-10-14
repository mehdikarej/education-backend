package crocoder.webteam.edukate.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crocoder.webteam.edukate.DTO.ClasseRequest;
import crocoder.webteam.edukate.DTO.ClasseResponseDTO;
import crocoder.webteam.edukate.DTO.CourseResponseDTO;
import crocoder.webteam.edukate.DTO.UserResponseDTO;
import crocoder.webteam.edukate.models.Classe;
import crocoder.webteam.edukate.models.Course;
import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.ClasseRepository;
import crocoder.webteam.edukate.repositories.CourseRepository;
import crocoder.webteam.edukate.repositories.UserRepository;
import crocoder.webteam.edukate.services.ClasseService;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Classe addClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    @Override
    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
    }

    @Override
    public Classe findClasseById(Long id) {
        return classeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClasseResponseDTO> getAllClasses() {

    	  return classeRepository.findAll()
    	            .stream()
    	            .map(this::mapToDTO)
    	            .collect(Collectors.toList());
}
    @Override
    public ClasseResponseDTO editClasse(Long id, ClasseRequest request) {

        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe not found"));

        // Update name
        if (request.getName() != null) {
            classe.setName(request.getName());
        }

        // Update teacher
        if (request.getTeacherId() != null) {
            User teacher = userRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            classe.setTeacher(teacher);
        }

        // Update students
        if (request.getStudentsIds() != null) {
            List<User> students = request.getStudentsIds().stream()
                    .map(idStudent -> userRepository.findById(idStudent)
                            .orElseThrow(() -> new RuntimeException("Student not found")))
                    .collect(Collectors.toList());

            classe.setStudents(students);
        }

        // Update courses
        if (request.getCourseId() != null) {
            List<Course> courses = request.getCourseId().stream()
                    .map(courseId -> courseRepository.findById(courseId)
                            .orElseThrow(() -> new RuntimeException("Course not found")))
                    .collect(Collectors.toList());

            classe.setCourses(courses);
        }

        Classe saved = classeRepository.save(classe);

        return mapToDTO(saved);
    }
    private ClasseResponseDTO mapToDTO(Classe classe) {

        List<UserResponseDTO> users = new ArrayList<>();

        // Teacher
        Set<String> roles = classe.getTeacher().getRoles()
                .stream()
                .map(role -> role.getName()) // or role.getRoleName()
                .collect(Collectors.toSet());
        if (classe.getTeacher() != null) {
            users.add(new UserResponseDTO(
                    classe.getTeacher().getId(),
                    classe.getTeacher().getFirstName(),
                    classe.getTeacher().getLastName(),
                    roles
                    
            ));
        }

        // Students
        if (classe.getStudents() != null) {
            users.addAll(
                    classe.getStudents().stream()
                            .map(s -> new UserResponseDTO(
                                    s.getId(),
                                    s.getFirstName(),
                                    s.getLastName(),
                                    s.getRoles().stream()
                                    .map(role -> role.getName())
                                    .collect(Collectors.toSet())
                                  
                                    
                            ))
                            .collect(Collectors.toList())
            );
        }

        // Courses
        List<CourseResponseDTO> courses = new ArrayList<>();

        if (classe.getCourses() != null) {
            courses = classe.getCourses().stream()
                    .map(c -> new CourseResponseDTO(
                            c.getId(),
                            c.getName(),
                            c.getDescription(),
                            c.getTimming()
                    ))
                    .collect(Collectors.toList());
        }

        return new ClasseResponseDTO(
                classe.getId(),
                classe.getName(),
                users,
                courses
        );
    }

	@Override
	public List<ClasseResponseDTO> getMyClasses(Long teacherId) {
		// TODO Auto-generated method stub
		List<Classe>classes=classeRepository.findByTeacherId(teacherId);
		return classes.stream().map(classe->{
	
			  List<UserResponseDTO> students = classe.getStudents()
		                .stream()
		                .map(student -> new UserResponseDTO(
		                        student.getId(),
		                        student.getFirstName(),
		                        student.getLastName()
		                ))
		                .collect(Collectors.toList());

		        List<CourseResponseDTO> courses = classe.getCourses()
		                .stream()
		                .map(course -> new CourseResponseDTO(
		                        course.getId(),
		                        course.getName(),
		                        course.getDescription(),
		                        course.getTimming()
		                ))
		                .collect(Collectors.toList());

		        return new ClasseResponseDTO(
		                classe.getId(),
		                classe.getName(),
		                students,
		                courses
		        );

		    }).collect(Collectors.toList());
}
}
