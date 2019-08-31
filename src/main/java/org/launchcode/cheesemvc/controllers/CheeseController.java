package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        //by passing the skeleton of this object into the view, we'll be able to use the properties of the object to render the form.
        model.addAttribute(new Cheese());
//        values will return an array of cheeseTypes.
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";

    }
    //cheeseName needs to match the name value of the form attribute. Then the black box of Spring will look for it.
    //in this case the method is expecting a Cheese object, and will therefor look for the associated fields and display
    // the ones we define. This is a function of Springboot.
    @RequestMapping(value ="add", method = RequestMethod.POST)
    //@Valid is processing the validation found in the Cheese class. It's going to put any errors in the Errors object we've
    // created. The Errors will be called & displayed in the add template.
    public String processAddCheeseForm(Model model, @ModelAttribute @Valid Cheese newCheese,
                                       Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }
        /*
        Implicit code to Springboot is something like this:
        Cheese newCheese = Cheese();
        newCheese.setName(Request.getParameter("name"));
        newCheese.setDescription(Request.getParameter("description"));
         */
        CheeseData.add(newCheese);

        // Redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheeses");

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
         // redirect back to the index
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese cheeseToEdit = CheeseData.getById(cheeseId);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("title", "Edit Cheese " + cheeseToEdit.getName() + " (id=" + cheeseToEdit.getCheeseId() + ")");
        model.addAttribute("cheeseToEdit", CheeseData.getById(cheeseId));

        return "cheese/edit";
    }
//test
    @RequestMapping(value = "edit/{cheeseId}" , method = RequestMethod.POST)
    public String processEditForm(int cheeseId, String name, String description, CheeseType type, int rating) {
        Cheese cheeseToEdit = CheeseData.getById(cheeseId);
        cheeseToEdit.setName(name);
        cheeseToEdit.setDescription(description);
        cheeseToEdit.setType(type);
        cheeseToEdit.setRating(rating);

        return "redirect:/cheese";
    }



}
