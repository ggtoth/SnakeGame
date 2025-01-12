package com.ggoth.snakegamematchmaking;

import com.ggoth.snakegamematchmaking.lobby.Lobby;
import com.ggoth.snakegamematchmaking.lobby.LobbyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SnakeGameMatchMakingApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SnakeGameMatchMakingApplication.class, args);
        LobbyRepository lobbyRepository = context.getBean(LobbyRepository.class);
        Lobby lobby = new Lobby();
        lobby.setCapacity(10);
        lobby.setWebsocketUrl("ws://localhost:8080/lobby/1");
        lobbyRepository.save(lobby);
    }

}
