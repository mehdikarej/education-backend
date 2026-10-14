package crocoder.webteam.edukate.services;

import java.util.List;

import crocoder.webteam.edukate.DTO.ClasseRequest;
import crocoder.webteam.edukate.DTO.ClasseResponseDTO;
import crocoder.webteam.edukate.models.Classe;

public interface ClasseService {
	Classe addClasse(Classe classe);
	ClasseResponseDTO editClasse(Long id,ClasseRequest classe);
	void deleteClasse(Long id);
	Classe findClasseById(Long id);
	List<ClasseResponseDTO> getAllClasses();
	List<ClasseResponseDTO>getMyClasses(Long teacherId);

}
