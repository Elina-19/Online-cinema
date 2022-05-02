package ru.itis.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dto.MessageDto;

@Controller
public class MessagesController {

    @GetMapping("/test")
    public String getTestPage(){
        return "index";
    }

    @MessageMapping("/news")
    public void getMessage(MessageDto message){
        System.out.println(message.getMessage());
    }
}
