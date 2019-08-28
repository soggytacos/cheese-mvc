package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Create User");

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify){
        model.addAttribute("title", "Create User");
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("email", user.getEmail());

        if (!user.getPassword().equals(verify)) {
            String passwordMessage = "Passwords do not match!";
            model.addAttribute("passwordMessage", passwordMessage);
            return "user/add";
        } else {
            return "user/index";
        }

    }
}
