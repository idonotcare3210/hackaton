package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.service.GuestService;
import com.blindcats.lifecourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestController {
    @Autowired
    private GuestService guestService;

    @PostMapping("/user/changeFirstname/{firstname}")
    public String  changeFirstname(@PathVariable("firstname") String firstname,
                              Model model) {
        guestService.changeUserFirstname(firstname);
        return "redirect:/user";
    }

    @PostMapping("/user/changeMiddlename/{middlename}")
    public String  changeMiddlename(@PathVariable("middlename") String middlename,
                                   Model model) {
        guestService.changeUserMiddlename(middlename);
        return "redirect:/user";
    }

    @PostMapping("/user/changeLastname/{lastname}")
    public String  changeLastname(@PathVariable("lastname") String lastname,
                                    Model model) {
        guestService.changeUserLastname(lastname);
        return "redirect:/user";
    }

    @PostMapping("/user/changeLastname/{username}")
    public String  changeUsername(@PathVariable("username") String username,
                                  Model model) {
        guestService.changeUserUsername(username);
        return "redirect:/user";
    }

    @PostMapping("/user/changeEmail/{email}")
    public String  changeEmail(@PathVariable("email") String email,
                                  Model model) {
        guestService.changeUserEmail(email);
        return "redirect:/user";
    }
}