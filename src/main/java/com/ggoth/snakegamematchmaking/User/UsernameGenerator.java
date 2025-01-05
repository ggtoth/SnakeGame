package com.ggoth.snakegamematchmaking.User;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Component
public class UsernameGenerator {
  private List<String> adjectives;
  private List<String> nouns;

  public UsernameGenerator() throws IOException {
    loadWords();
  }

  private void loadWords() throws IOException {
    adjectives = Files.readAllLines(Paths.get("src/main/resources/adjectives.txt"));
    nouns = Files.readAllLines(Paths.get("src/main/resources/nouns.txt"));
  }

  public String generateRandomUsername() {
    Random random = new Random();
    String adjective = adjectives.get(random.nextInt(adjectives.size()));
    String noun = nouns.get(random.nextInt(nouns.size()));
    return capitalize(adjective) + capitalize(noun) + "#" + random.nextInt(9999);
  }
}
