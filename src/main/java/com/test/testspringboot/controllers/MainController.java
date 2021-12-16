package com.test.testspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    private String home(Model model) {
        model.addAttribute("title", "Мой блог");
        return "home";
    }

    @GetMapping("/about")
    private String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }
}
