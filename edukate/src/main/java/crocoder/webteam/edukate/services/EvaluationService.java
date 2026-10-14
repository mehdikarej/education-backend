package crocoder.webteam.edukate.services;

import java.util.List;

import crocoder.webteam.edukate.DTO.EvaluationResponseDTO;
import crocoder.webteam.edukate.DTO.EvalutionRequest;
import crocoder.webteam.edukate.models.Evaluation;

public interface EvaluationService {
	Evaluation addEvalution(Evaluation evalution);
	EvaluationResponseDTO editEvalution(Long id,EvalutionRequest request);
	List<EvaluationResponseDTO>getEvalution(String phoneNumberChild);

}
