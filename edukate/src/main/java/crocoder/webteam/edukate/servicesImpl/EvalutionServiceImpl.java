package crocoder.webteam.edukate.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crocoder.webteam.edukate.DTO.CourseResponseDTO;
import crocoder.webteam.edukate.DTO.EvaluationResponseDTO;
import crocoder.webteam.edukate.DTO.EvalutionRequest;
import crocoder.webteam.edukate.DTO.UserResponseDTO;
import crocoder.webteam.edukate.models.Evaluation;
import crocoder.webteam.edukate.repositories.EvaluationRepository;
import crocoder.webteam.edukate.services.EvaluationService;
@Service

public class EvalutionServiceImpl implements EvaluationService {
	@Autowired
	private EvaluationRepository evaluationRepository;

	@Override
	public Evaluation addEvalution(Evaluation evaluation) {
		// TODO Auto-generated method stub
		return evaluationRepository.save(evaluation);
	}

	@Override
	public EvaluationResponseDTO editEvalution(Long id, EvalutionRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EvaluationResponseDTO> getEvalution(String phoneNumberChild) {
		// TODO Auto-generated method stub
		List<Evaluation> evaluations = evaluationRepository.getEvaluation(phoneNumberChild);
		
		if(evaluations.isEmpty()) {
			throw new RuntimeException("Student not found");
		}
		return evaluations.stream()
		
			    .map(e -> {

			        UserResponseDTO userDto = new UserResponseDTO(
			            e.getStudent().getId(),
			            e.getStudent().getFirstName(),
			            e.getStudent().getLastName()
			        );

			        CourseResponseDTO courseDto = (e.getCourse() != null)
			                ? new CourseResponseDTO(
			                        e.getCourse().getId(),
			                        e.getCourse().getName()
			                  )
			                : null;
			       

			        return new EvaluationResponseDTO(
			        		e.getId(),
			            e.getNote(),
			            e.getRemark(),
			            userDto,
			            courseDto
			           
			        );
			    })
			    .collect(Collectors.toList());

}
	
}
