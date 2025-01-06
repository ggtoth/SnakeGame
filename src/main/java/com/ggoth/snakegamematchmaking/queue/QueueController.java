package com.ggoth.snakegamematchmaking.queue;

import com.ggoth.snakegamematchmaking.user.User;
import com.ggoth.snakegamematchmaking.websocket.MessageType;
import com.ggoth.snakegamematchmaking.websocket.WSMessage;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

@Controller
public class QueueController {

  private final QueueService queueService;
  private final SimpMessagingTemplate messagingTemplate;

  public QueueController(QueueService queueService, SimpMessagingTemplate messagingTemplate) {
    this.queueService = queueService;
    this.messagingTemplate = messagingTemplate;
  }

  @MessageMapping("/join")
  public void joinQueue(@Payload User user, @Header("simpSessionId") String sessionId) {
    User queuedUser = queueService.queueUp(user);
    ResponseEntity<User> response;
    if (queuedUser != null) {
      response = ResponseEntity.ok(queuedUser);
    }
    else {
      response = ResponseEntity.notFound().build();
    }
    messagingTemplate.convertAndSendToUser(
        sessionId,
        "/join/reply",
        response);
  }

  @MessageMapping("/leave")
  public void leaveQueue(@Payload User user, @Header("simpSessionId") String sessionId) {
    User queuedUser = queueService.queueDown(user);
    ResponseEntity<User> response;
    if (queuedUser != null) {
      response = ResponseEntity.ok(queuedUser);
    }
    else {
      response = ResponseEntity.notFound().build();
    }
    messagingTemplate.convertAndSendToUser(
        sessionId,
        "/leave/reply",
        response);
  }

  @EventListener
  public void handleDisconnect(SessionDisconnectEvent event){
    String sessionId = event.getSessionId();

  }
}
