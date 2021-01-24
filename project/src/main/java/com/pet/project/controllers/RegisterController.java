package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid User user, BindingResult bindingResult) {
        userService.create(user);
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isAtcivated = userService.activateUser(code);

        if (isAtcivated){
            model.addAttribute("message","User successfully activated");
        }else {
            model.addAttribute("message","Activation code is not found");
        }

        return "login";
    }

}
