package com.ggoth.snakegamematchmaking.websocket;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WSMessage {
  private String content;
  private MessageType type;
}
