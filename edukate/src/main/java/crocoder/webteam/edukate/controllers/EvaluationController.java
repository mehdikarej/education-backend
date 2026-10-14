package crocoder.webteam.edukate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crocoder.webteam.edukate.DTO.EvaluationResponseDTO;
import crocoder.webteam.edukate.DTO.EvalutionRequest;
import crocoder.webteam.edukate.models.Classe;
import crocoder.webteam.edukate.models.Course;
import crocoder.webteam.edukate.models.Evaluation;
import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.ClasseRepository;
import crocoder.webteam.edukate.repositories.CourseRepository;
import crocoder.webteam.edukate.repositories.EvaluationRepository;
import crocoder.webteam.edukate.repositories.UserRepository;
import crocoder.webteam.edukate.services.EvaluationService;

@RestController
@RequestMapping("/api/evaluations")

public class EvaluationController {
	@Autowired
	private EvaluationRepository evaluationRepository;
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private ClasseRepository classeRepository;
	@Autowired 
	private CourseRepository courseRepository;
	//add evaluation
	@PostMapping
	public ResponseEntity<Evaluation>addEvaluation(@RequestBody EvalutionRequest request,Authentication authentification){
		String email=authentification.getName();
		User teacher=userRepository.findUserByEmail(email);
			if(teacher==null) {
				throw new RuntimeException("Teacher not found");
			}
			
		Evaluation evaluation=new Evaluation();
		evaluation.setTeacher(teacher);
		Classe classe=classeRepository.findById(request.getClasseId())
				.orElseThrow(()->new RuntimeException("classe not found"));
		evaluation.setClasse(classe);
		User student=userRepository.findById(request.getStudentId())
				.orElseThrow(()->new RuntimeException("student not found"));
				evaluation.setStudent(student);
				Course course=courseRepository.findById(request.getCourseId())
						.orElseThrow(()->new RuntimeException("course not found"));
				evaluation.setCourse(course);
		evaluation.setNote(request.getNote());
		evaluation.setRemark(request.getRemark());
		evaluationRepository.save(evaluation);
		return ResponseEntity.ok(evaluation);
		
	}
	
	//get evaluation
	@GetMapping("/{phoneNumberChild}")
	public List<EvaluationResponseDTO>getEvaluation(@PathVariable String phoneNumberChild){
		List<Evaluation>evaluations=evaluationRepository.getEvaluation(phoneNumberChild);
		if(evaluations.isEmpty()) {
			throw new RuntimeException("Student not found");
		}
		
		return evaluationService.getEvalution(phoneNumberChild);
	}
	

}
