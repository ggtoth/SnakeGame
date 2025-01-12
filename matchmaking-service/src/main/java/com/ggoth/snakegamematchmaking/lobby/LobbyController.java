package com.ggoth.snakegamematchmaking.lobby;

import com.ggoth.snakegamematchmaking.player.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lobby")
public class LobbyController {

  private final LobbyService lobbyService;

  public LobbyController(LobbyService lobbyService) {
    this.lobbyService = lobbyService;
  }

  @GetMapping()
  public ResponseEntity<PagedModel<Lobby>> getLobbies(Pageable pageable) {
    Page<Lobby> lobbyPage = lobbyService.getLobbies(pageable);
    PagedModel<Lobby> pagedModel = PagedModel.of(
        lobbyPage.getContent(),
        new PagedModel.PageMetadata(
            lobbyPage.getSize(),
            lobbyPage.getNumber(),
            lobbyPage.getTotalElements(),
            lobbyPage.getTotalPages()
        )
    );
    return ResponseEntity.ok(pagedModel);
  }

  @PostMapping("/join/{lobbyId}")
  public ResponseEntity<Player> joinLobby(@PathVariable("lobbyId") Long lobbyId, @RequestBody LobbyJoinRequest joinRequest) {
    try{
      Player joinedPlayer = lobbyService.joinlobby(lobbyId, joinRequest);
      return ResponseEntity.ok(joinedPlayer);
    }
    catch(Exception e){
      return ResponseEntity.notFound().build();
    }
  }
}
