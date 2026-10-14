package crocoder.webteam.edukate.repositories;

import crocoder.webteam.edukate.DTO.ClasseResponseDTO;
import crocoder.webteam.edukate.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
	
    @Query("SELECT c FROM Classe c WHERE c.teacher.id=:teacherId")
    List<Classe>findByTeacherId(@Param("teacherId")Long teacherId);
    @Query("""
    		SELECT DISTINCT c FROM Classe c
    		LEFT JOIN  c.teacher
    		LEFT JOIN  c.students
    		LEFT JOIN c.courses
    		""")
    		List<ClasseResponseDTO>getAllClasses();
    @Query("""
    		SELECT DISTINCT cl
    		FROM Classe cl
			LEFT JOIN FETCH cl.students s
			LEFT JOIN FETCH cl.courses c
			WHERE cl.teacher.id = :teacherId
    		""")
    List<Classe>findByteacherId(@Param("teacherId")Long teacherId);

}

