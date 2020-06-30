package com.ponomarenko.achandbook.controller;

import com.ponomarenko.achandbook.model.User;
import com.ponomarenko.achandbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profileUpdateForm(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("username",user.getUsername());
        return "admin/profile";
    }

    @PostMapping
    public String profileUpdate(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String password){
        userService.updateUser(user, username, password);
        return "redirect:/brands";
    }


}
