package com.tekleo.eating_out_guide_bot.review.enums;

import com.tekleo.eating_out_guide_bot.Emoji;

public enum Confirmation {
    YES("Да"),
    NO_CANCEL("Нет, отменить отзыв");

    private String buttonText;

    Confirmation(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public static Confirmation fromButtonText(String buttonText) {
        for (Confirmation confirmation : values()) {
            if (confirmation.getButtonText().equals(buttonText)) {
                return confirmation;
            }
        }

        throw new IllegalArgumentException("Unknown button text: " + buttonText);
    }
}
