package com.ggoth.snakegamematchmaking.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final UserRepository userRepository;

  public UserController(UserService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }

  // TODO make this use spring security instead
  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@Valid @PathVariable Long id, @Valid @RequestParam String secret) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      if (user.get().getSecret().equals(secret)) {
        return ResponseEntity.ok(user.get());
      }
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@Valid @RequestBody UserRegisterRequest request) {
    String username = request.getUsername();
    User user = userService.createUser(username);

    if(user == null) {
      return ResponseEntity.badRequest().build();
    }

    URI location = URI.create("/user/" + user.getId());
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(location);
    headers.set("secret", user.getSecret());

    return ResponseEntity.created(location).headers(headers).build();
  }
}
