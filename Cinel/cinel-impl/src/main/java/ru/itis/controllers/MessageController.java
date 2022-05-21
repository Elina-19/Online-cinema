package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dto.request.MessageRequest;
import ru.itis.dto.response.MessageResponse;
import ru.itis.service.RoomService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class MessageController {

    private final RoomService roomService;


    @GetMapping("/test")
    public String getTestPage(){
        return "index";
    }

    @MessageMapping("/room/{room-id}")
    @SendTo("/chat/room/{room-id}")
    public MessageResponse sendMessage(@DestinationVariable("room-id") UUID roomId,
                                       MessageRequest message) {
        return roomService.sendMessageToRoom(roomId, message);
    }
}
