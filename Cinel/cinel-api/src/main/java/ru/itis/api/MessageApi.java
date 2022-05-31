package ru.itis.api;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import ru.itis.dto.request.MessageRequest;
import ru.itis.dto.response.MessageResponse;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

public interface MessageApi<PRINCIPAL> {

    @MessageMapping("/room/{room-id}")
    @SendTo("/chat/room/{room-id}")
     MessageResponse sendMessage(
            @DestinationVariable("room-id") UUID roomId, MessageRequest message,
            @ApiIgnore PRINCIPAL principal);
}
