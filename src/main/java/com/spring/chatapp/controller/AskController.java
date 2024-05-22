package com.spring.chatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AskController {

    @GetMapping("/ask")
    public String ask() {
        return "ask.html";
    }

    @GetMapping("/pathways")
    public String pathways() {
        return "pathways.html";
    }
}
