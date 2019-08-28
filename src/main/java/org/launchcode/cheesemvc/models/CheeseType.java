package org.launchcode.cheesemvc.models;

public enum CheeseType {

    HARD ("Hard"),
    SOFT ("Soft"),
    AGED ("Aged"),
    FAKE ("Fake"),
    VEGAN ("Vegan");

    private final String name;

    CheeseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
