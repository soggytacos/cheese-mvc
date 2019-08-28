package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    @NotNull //Validation: cannot be null
    @Size(min=3, max=20) //validation: must be 3-20 characters long
    private String name;
    @NotNull
    @Size(min=1, message = "Description must not be empty.")
    private String description;

    //Here we are initializing our ENUM CheeseType object.
    private CheeseType type;
    private int cheeseId;
    private static int nextId = 1;

    public Cheese(String name, String description) {
        this(); //call the default constructor for the given class, in this case to increment the ID
        this.name = name;
        this.description = description;
    }

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }
}
