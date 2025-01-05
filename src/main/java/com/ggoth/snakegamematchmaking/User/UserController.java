package com.ggoth.snakegamematchmaking.User;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@Valid @PathVariable Long id) {
    User user = userService.getUser(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
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

    return ResponseEntity.created(location).headers(headers).build();
  }


}
