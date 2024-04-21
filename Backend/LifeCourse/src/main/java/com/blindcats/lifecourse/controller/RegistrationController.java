package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.entity.User;
import com.blindcats.lifecourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        //model.addText("userForm", new User());
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        int registrationResponse = userService.saveUser(userForm);
        if (registrationResponse == 1){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        else if (registrationResponse == 2) {
            model.addAttribute("emailError", "Пользователь с такой электронной почтой уже существует");
            return "registration";
        }
        else if (registrationResponse == 3) {
            model.addAttribute("emailValidError", "Некорректный адрес электронной почты");
            return "registration";
        }
        return "redirect:/index";
    }
}
