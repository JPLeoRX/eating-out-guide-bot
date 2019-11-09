package com.tekleo.eating_out_guide_bot.review.enums;

public enum Privacy {
    ANONYMOUS("Yes"),
    PUBLIC("No, make my username public");

    private String buttonText;

    Privacy(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }

    public static Privacy fromButtonText(String buttonText) {
        for (Privacy privacy : values()) {
            if (privacy.getButtonText().equals(buttonText)) {
                return privacy;
            }
        }

        throw new IllegalArgumentException("Unknown button text: " + buttonText);
    }
}
