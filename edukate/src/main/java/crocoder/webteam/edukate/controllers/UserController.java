package crocoder.webteam.edukate.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import crocoder.webteam.edukate.DTO.LoginRequest;
import crocoder.webteam.edukate.models.Role;
import crocoder.webteam.edukate.models.User;
import crocoder.webteam.edukate.repositories.RoleRepository;
import crocoder.webteam.edukate.security.JwtUtil;
import crocoder.webteam.edukate.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	   // --- Tous les utilisateurs ---
  
    //Ajouter un utilisateur
    @PostMapping( value="/signup",consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> signup( @RequestParam String firstName,
    	    @RequestParam String lastName,
    	    @RequestParam String email,
    	    @RequestParam String password,
    	    @RequestParam(required = false) String phoneNumber,
    	    @RequestParam(required = false) String address,
    	    @RequestParam(required = false) String specialty,
    	    @RequestParam(required = false) String experience,
    	    @RequestParam(required = false) String phoneNumberChild,
    	    @RequestParam(required=  false)String status,
    	    @RequestParam(required = false) List<String> roles,
    	    @RequestParam(required = false) MultipartFile photo,
    	    @RequestParam(required = false) MultipartFile cv) {
        try {
        	  System.out.println("PHOTO = " + (photo != null ? photo.getOriginalFilename() : "null"));
        	    System.out.println("CV = " + (cv != null ? cv.getOriginalFilename() : "null"));
            // Création de l'utilisateur
           

        	    // Création de l'utilisateur
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhoneNumber(phoneNumber);
                user.setAddress(address);
                user.setSpecialty(specialty);
                user.setExperience(experience);
                user.setPhoneNumberChild(phoneNumberChild);
                user.setStatus("NotActivate");

                if (photo != null) {
                    // Si tu stockes juste le nom du fichier, sinon tu peux gérer le stockage
                    user.setPhoto(photo.getOriginalFilename());
                }
                if (cv != null) {
                    user.setCv(cv.getOriginalFilename());
                }

                // Gestion des rôles
                List<Role> roleEntities = new ArrayList<>();
                if (roles == null || roles.isEmpty()) {
                    Role defaultRole = roleRepository.findByName("USER")
                            .orElseThrow(() -> new RuntimeException("Default role not found"));
                    roleEntities.add(defaultRole);
                } else {
                    for (String roleName : roles) {
                        Role role = roleRepository.findByName(roleName)
                                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                        roleEntities.add(role);
                    }
                }
                user.setRoles(roleEntities);

                // Sauvegarde de l'utilisateur
                User createdUser = userService.addUser(user);

            // Réponse
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "User created successfully",
                            "user", createdUser
                    ));
        } catch (IllegalArgumentException ex) {
            // Email déjà utilisé ou mot de passe manquant
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace(); // Pour debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Server error: " + ex.getMessage()));
        }
    }
    // --- Connexion ---
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> map = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
                User user = userService.getUserByEmail(loginRequest.getEmail());
                String token = jwtUtil.createToken(userDetails, user);

                map.put("status", HttpStatus.OK.value());
                map.put("message", "Authentication successful");
                map.put("token", token);
                return ResponseEntity.ok(map);
            } else {
                map.put("status", HttpStatus.UNAUTHORIZED.value());
                map.put("message", "Authentication failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
            }

        } catch (BadCredentialsException ex) {
            map.put("status", HttpStatus.UNAUTHORIZED.value());
            map.put("message", "Bad credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        } catch (LockedException ex) {
            map.put("status", HttpStatus.UNAUTHORIZED.value());
            map.put("message", "Your account is locked");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        } catch (DisabledException ex) {
            map.put("status", HttpStatus.UNAUTHORIZED.value());
            map.put("message", "Your account is disabled");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        } catch (AuthenticationException ex) {
            map.put("status", HttpStatus.UNAUTHORIZED.value());
            map.put("message", "Authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }
    //Get AllUsers
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    // --- Récupérer par ID ---
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
    // --- Récupérer par email ---
    @GetMapping("/by-email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
    // --- Modifier un utilisateur ---
    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.editUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("message", ex.getMessage()));
        }
    }
    //---Modifier un status d'un utilisateur
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateStatus(@PathVariable Long id){
    	return ResponseEntity.ok(userService.activateStatus(id,"Active"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
