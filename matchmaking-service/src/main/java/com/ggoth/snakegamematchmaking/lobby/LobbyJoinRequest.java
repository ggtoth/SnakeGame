package com.ggoth.snakegamematchmaking.lobby;

import com.ggoth.snakegamematchmaking.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LobbyJoinRequest {
  Long userId;
  String userSecret;
}
