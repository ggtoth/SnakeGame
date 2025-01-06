package com.ggoth.snakegamematchmaking.User;

import com.ggoth.snakegamematchmaking.names.Name;
import com.ggoth.snakegamematchmaking.names.NamesRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final NamesRepository namesRepository;

  public UserService(UserRepository userRepository, NamesRepository namesRepository) {
    this.userRepository = userRepository;
    this.namesRepository = namesRepository;
  }

  @Transactional
  public User createUser(String username) {
    User user = new User();
    if (username != null && username.length() > 0) {
      user.setUsername(username);
    }
    else{
      Name name = namesRepository.findRandomName();
      String stringName = name.getName();
      Set<String> matchingNames = userRepository.findUsersByUsernameStartingWith(stringName).stream().map(User::getUsername).collect(Collectors.toSet());

      for(Long i = 1L; ; i++) {
        String possibleName = stringName + "#" + String.format("%06d", i);
        if (matchingNames.contains(possibleName)) {
          continue;
        }
        user.setUsername(possibleName);
        break;
      }
    }

    user = userRepository.save(user);
    return user;
  }
}
