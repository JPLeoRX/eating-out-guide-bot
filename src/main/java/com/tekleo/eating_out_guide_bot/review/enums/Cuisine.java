package com.tekleo.eating_out_guide_bot.review.enums;

import com.tekleo.eating_out_guide_bot.Emoji;

public enum Cuisine {
    AMERICAN("Американская " + Emoji.FOOD_BURGER.getText()),
    JAPANESE("Японская " + Emoji.FOOD_SASHIMI.getText()),
    CHINESE("Китайская " + Emoji.FOOD_NOODLES.getText()),
    ITALIAN("Итальянская " + Emoji.FOOD_PASTA.getText()),
    FRENCH("Французская " + Emoji.FOOD_ONION_SOUP.getText()),
    MEXICAN("Мексиканская " + Emoji.FOOD_TACO.getText()),
    INDIAN("Индийская " + Emoji.FOOD_CURRY.getText()),
    MEDITERRANEAN("Средиземноморская " + Emoji.FOOD_GREEN_SALAD.getText()),
    UKRAINIAN("Українська " + Emoji.FOOD_SOUP.getText()),
    PATISSERIE("Кондитерские " + Emoji.FOOD_CAKE_STRAWBERRY.getText()),
    FASTFOOD("Фаст-фуд " + Emoji.FOOD_FRIES.getText()),
    OTHER("Other " + Emoji.QUESTION_MARK.getText());

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
