package com.ggoth.snakegamematchmaking.User;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final UsernameGenerator usernameGenerator;

  public UserService(UserRepository userRepository, UsernameGenerator usernameGenerator) {
    this.userRepository = userRepository;
    this.usernameGenerator = usernameGenerator;
  }

  @Transactional
  public User createUser(String username) {
    User user = new User();
    if (username != null && username.length() > 0) {
      user.setUsername(username);
    }
    else{
      user.setUsername(usernameGenerator.generateRandomUsername());
    }

    User found = userRepository.findUserByUsername(user.getUsername());
    while (found != null){
      user.setUsername(usernameGenerator.generateRandomUsername());
      found = userRepository.findUserByUsername(user.getUsername());
    }

    user = userRepository.save(user);
    return user;
  }

  public User getUser(Long id) {
    User user = userRepository.getUserById(id);
    return user;
  }
}
