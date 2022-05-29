package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.api.MessageApi;
import ru.itis.dto.request.MessageRequest;
import ru.itis.dto.response.MessageResponse;
import ru.itis.service.RoomService;

import java.security.Principal;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class MessageController implements MessageApi<Principal> {

    private final RoomService roomService;


    @GetMapping("/test")
    public String getTestPage() {
        return "index";
    }

    @Override
    public MessageResponse sendMessage(@DestinationVariable("room-id") UUID roomId,
                                       MessageRequest message, Principal principal) {
        return roomService.sendMessageToRoom(roomId, message, principal);
    }
}
