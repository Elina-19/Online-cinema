package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.repository.AccountsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/test")
@Controller
public class TestController {

    @GetMapping
    public void getPage(HttpServletResponse res) throws IOException {
        res.getWriter().write("hey");
    }
}
