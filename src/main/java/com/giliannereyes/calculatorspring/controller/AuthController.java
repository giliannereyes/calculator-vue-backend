package com.giliannereyes.calculatorspring.controller;

import com.giliannereyes.calculatorspring.model.User;
import com.giliannereyes.calculatorspring.model.auth.LoginRequest;
import com.giliannereyes.calculatorspring.model.auth.LoginResponse;
import com.giliannereyes.calculatorspring.repo.UserRepository;
import com.giliannereyes.calculatorspring.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for authentication-related endpoints.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
  private final UserRepository userRepository;
  private final JwtService jwtService;

  /**
   * Creates an authentication controller.
   *
   * @param userRepository user persistence repository
   * @param jwtService service for JWT token generation
   */
  public AuthController(UserRepository userRepository, JwtService jwtService) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  /**
   * Logs in an existing user or creates a new one.
   *
   * @param request login payload
   * @return user metadata and a signed JWT token
   */
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    String username = request.getUsername() == null ? "" : request.getUsername().trim();
    String password = request.getPassword() == null ? "" : request.getPassword();

    if (username.isEmpty() || password.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    Optional<User> existingUser = userRepository.findByUsername(username);
    User user;

    if (existingUser.isPresent()) {
      user = existingUser.get();
      if (!user.getPassword().equals(password)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    } else {
      user = userRepository.save(new User(username, password));
    }

    String token = jwtService.generateToken(user.getUsername());
    LoginResponse response = new LoginResponse(user.getId(), user.getUsername(), token);
    return ResponseEntity.ok(response);
  }
}
