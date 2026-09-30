package com.petpulse.petpulse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user account with hashed password
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // Encrypt plain password using BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }

        User savedUser = userRepository.save(user);
        savedUser.setPassword(null); // Clear password hash before returning response
        return ResponseEntity.ok(savedUser);
    }

    // Login validation endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Compare submitted plain password with BCrypt hash in MySQL
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                user.setPassword(null); // Clear hash in response
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(401).body("Invalid username or password!");
    }
}