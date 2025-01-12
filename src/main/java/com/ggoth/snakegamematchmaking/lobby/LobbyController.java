package com.ggoth.snakegamematchmaking.lobby;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
