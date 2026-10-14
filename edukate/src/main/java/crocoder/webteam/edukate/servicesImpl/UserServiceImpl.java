package crocoder.webteam.edukate.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.UserRepository;
import crocoder.webteam.edukate.services.UserService;

@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserRepository userRepository;
@Autowired
private PasswordEncoder passwordEncoder;
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		 if (user.getPassword() == null || user.getPassword().isEmpty()) {
		        throw new IllegalArgumentException("Password is required");
		    }

		    if (userRepository.existsByEmail(user.getEmail())) {
		        throw new IllegalArgumentException("Email already exists");
		    }

		    user.setPassword(passwordEncoder.encode(user.getPassword()));
		    return userRepository.save(user);
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(id);	
		return  user.isPresent()? user.get() :null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User editUser(Long id, User user) {
		// TODO Auto-generated method stub
		 User existingUser = userRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("User not found with id " + id));

		    // Champs modifiables
		    existingUser.setFirstName(user.getFirstName());
		    existingUser.setLastName(user.getLastName());
		    existingUser.setEmail(user.getEmail());
		    existingUser.setPhoneNumber(user.getPhoneNumber());
		    existingUser.setAddress(user.getAddress());
		    existingUser.setSpecialty(user.getSpecialty());
		    existingUser.setExperience(user.getExperience());
		    existingUser.setAddress(user.getCv());
		    existingUser.setPhoto(user.getPhoto());

		    // ⚠️ Password : seulement si fourni, et on encode
		    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
		        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
		    }

		    // ⚠️ Roles : seulement si fournis
		    if (user.getRoles() != null && !user.getRoles().isEmpty()) {
		        existingUser.setRoles(user.getRoles());
		    }

		    return userRepository.save(existingUser);
		}
	

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User activateStatus(Long id,String status) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		user.setStatus(status);
		return userRepository.save(user);
	}

}
