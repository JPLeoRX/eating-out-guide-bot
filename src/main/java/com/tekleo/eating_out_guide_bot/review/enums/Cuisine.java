package com.tekleo.eating_out_guide_bot.review.enums;

import com.tekleo.eating_out_guide_bot.Constants;

public enum Cuisine {
    AMERICAN(Constants.BUTTON_CUISINE_AMERICAN),
    JAPANESE(Constants.BUTTON_CUISINE_JAPANESE),
    CHINESE(Constants.BUTTON_CUISINE_CHINESE),
    ITALIAN(Constants.BUTTON_CUISINE_ITALIAN),
    FRENCH(Constants.BUTTON_CUISINE_FRENCH),
    MEXICAN(Constants.BUTTON_CUISINE_MEXICAN),
    INDIAN(Constants.BUTTON_CUISINE_INDIAN),
    TURKISH(Constants.BUTTON_CUISINE_TURKISH),
    MEDITERRANEAN(Constants.BUTTON_CUISINE_MEDITERRANEAN),
    UKRAINIAN(Constants.BUTTON_CUISINE_UKRAINIAN),
    GEORGIAN(Constants.BUTTON_CUISINE_GEORGIAN),
    PATISSERIE(Constants.BUTTON_CUISINE_PATISSERIE),
    COFFEE_SHOP(Constants.BUTTON_CUISINE_COFFEE_SHOP),
    FASTFOOD(Constants.BUTTON_CUISINE_FASTFOOD),
    OTHER(Constants.BUTTON_CUISINE_OTHER);

    private String buttonText;

    Cuisine(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public static Cuisine fromButtonText(String buttonText) {
        for (Cuisine cuisine : values()) {
            if (cuisine.getButtonText().equals(buttonText)) {
                return cuisine;
            }
        }

        throw new IllegalArgumentException("Unknown button text: " + buttonText);
    }
}
