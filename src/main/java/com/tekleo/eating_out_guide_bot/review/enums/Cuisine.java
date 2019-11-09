package com.tekleo.eating_out_guide_bot.review.enums;

public enum Cuisine {
    AMERICAN("American"),
    JAPANESE("Japanese"),
    CHINESE("Chinese"),
    ITALIAN("Italian"),
    FRENCH("French"),
    MEXICAN("Mexican"),
    INDIAN("Indian"),
    MEDITERRANEAN("Mediterranean"),
    UKRAINIAN("Ukrainian"),
    PATISSERIE("Patisserie");

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
