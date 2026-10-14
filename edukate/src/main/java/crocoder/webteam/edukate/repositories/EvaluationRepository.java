package crocoder.webteam.edukate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crocoder.webteam.edukate.DTO.EvaluationResponseDTO;
import crocoder.webteam.edukate.models.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
	@Query("SELECT e FROM Evaluation e WHERE e.student.id=:studentId")
	List<Evaluation>findByStudentId(@Param("studentId")Long studentId);
	@Query("""
		    SELECT 
		      e 
		      FROM Evaluation e
		       WHERE e.student.phoneNumber = :phoneNumberChild
		    	   
		    """)
	List<Evaluation>getEvaluation(@Param("phoneNumberChild")String phoneNumberChild);

}
