package crocoder.webteam.edukate.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.UserRepository;
import crocoder.webteam.edukate.services.AuthService;
@Service
public class AuthServiceImpl implements AuthService {
@Autowired
private UserRepository userRepository;
	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		 Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

	        String email = authentication.getName();

	        User user = userRepository.findUserByEmail(email);
	        if (user == null) {
	            throw new RuntimeException("User not found");
	        }

	        return user;
	                
	}

	@Override
	public Long getCurrentUserId() {
		// TODO Auto-generated method stub
		return getCurrentUser().getId();
	}

}
