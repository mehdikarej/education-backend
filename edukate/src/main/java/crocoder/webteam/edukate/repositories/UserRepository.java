package crocoder.webteam.edukate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crocoder.webteam.edukate.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = :email")
	User findUserByEmail(@Param("email") String email);
	boolean existsByEmail(String email);
	boolean existsById(Long Id);

}
