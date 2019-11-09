package com.tekleo.eating_out_guide_bot.review.enums;

import com.tekleo.eating_out_guide_bot.Emoji;

public enum Cuisine {
    AMERICAN("American " + Emoji.FOOD_BURGER.getText()),
    JAPANESE("Japanese " + Emoji.FOOD_SASHIMI.getText()),
    CHINESE("Chinese " + Emoji.FOOD_NOODLES.getText()),
    ITALIAN("Italian " + Emoji.FOOD_PASTA.getText()),
    FRENCH("French " + Emoji.FOOD_ONION_SOUP.getText()),
    MEXICAN("Mexican " + Emoji.FOOD_TACO.getText()),
    INDIAN("Indian " + Emoji.FOOD_CURRY.getText()),
    MEDITERRANEAN("Mediterranean " + Emoji.FOOD_GREEN_SALAD.getText()),
    UKRAINIAN("Ukrainian " + Emoji.FOOD_SOUP.getText()),
    PATISSERIE("Patisserie " + Emoji.FOOD_CAKE_STRAWBERRY.getText()),
    FASTFOOD("Fast food " + Emoji.FOOD_FRIES.getText());

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
