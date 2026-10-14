package crocoder.webteam.edukate.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
@Autowired
private UserRepository userRepository;
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
User user = userRepository.findUserByEmail(email);
if (user == null) {
throw new UsernameNotFoundException("User not found with email: "+ email);
}
return org.springframework.security.core.userdetails.User.withUsername
(email).password(user.getPassword()).build();
}

	

}
