package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Welcome!");

        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model){
        model.addAttribute("title", "Create User");
        model.addAttribute(new User());

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(Model model, String verify, @ModelAttribute @Valid User user, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create User");
            model.addAttribute(user);

            return "user/add";
        } else if (!user.getPassword().equals(verify)) {
            String passwordMessage = "Passwords do not match!";
            user.setPassword("");
            model.addAttribute("passwordMessage", passwordMessage);
            model.addAttribute(user);
            model.addAttribute("title", "Create User");

            return "user/add";
        } else {
            model.addAttribute(user);
            model.addAttribute("title", "Welcome " + user.getUsername() + "!");
            return "user/index";
        }

    }
}
