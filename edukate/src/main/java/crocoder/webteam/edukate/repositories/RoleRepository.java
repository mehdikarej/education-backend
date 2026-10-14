package crocoder.webteam.edukate.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import crocoder.webteam.edukate.models.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
