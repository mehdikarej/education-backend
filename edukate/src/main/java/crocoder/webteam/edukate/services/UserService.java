package crocoder.webteam.edukate.services;

import java.util.List;

import crocoder.webteam.edukate.models.User;

public interface UserService {
	User addUser(User user);
	User findUserById(Long id);
	void deleteUser(Long id);
	User getUserByEmail(String email);
	User editUser(Long id,User user);
	List<User>getAllUsers();
	User activateStatus( Long id,String status);

}
