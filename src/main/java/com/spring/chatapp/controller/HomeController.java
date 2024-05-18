package com.spring.chatapp.controller;

import org.springframework.ui.Model;
import com.spring.chatapp.models.User;
import com.spring.chatapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.findUserByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("username", username);
            return "redirect:/chat";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.findUserByUsername(username) != null) {
            model.addAttribute("error", "Username already taken");
            return "signup.html";
        } else {
            User newUser = new User(username, password);
            userService.saveUser(newUser);
            return "redirect:/login";
        }
    }
}
