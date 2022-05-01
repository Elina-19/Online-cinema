package ru.itis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.itis.dto.MessageDto;

@Controller
public class MessagesController {
    @MessageMapping("/news")
    public void getMessage(MessageDto message){
        System.out.println(message.getMessage());
    }
}
