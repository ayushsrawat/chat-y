package com.spring.chatapp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @RequestMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        // Code to validate user credentials
        // If valid, redirect to chat room
        // If invalid, show an error message
        if (isValidUser(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/chat";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }

    private boolean isValidUser(String username, String password) {
        // Implement your logic to validate user credentials
        // For example, checking against the database
        return "user".equals(username) && "password".equals(password);
    }


    @GetMapping("/signup")
    public String signupPage() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username, @RequestParam String password) {
        // Code to handle sign-up logic
        // Save the new user to the database
        // Redirect to login page after successful sign-up
        return "redirect:/login";
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat.html";
    }


}
