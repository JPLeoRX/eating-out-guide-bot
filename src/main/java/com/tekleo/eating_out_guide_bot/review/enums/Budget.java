package com.tekleo.eating_out_guide_bot.review.enums;

public enum Budget {
    CHEAP("Cheap", "$"),
    AVERAGE("Average", "$$"),
    EXPENSIVE("Expensive", "$$$");

    private String text;
    private String buttonText;

    Budget(String text, String buttonText) {
        this.text = text;
        this.buttonText = buttonText;
    }

    public String getText() {
        return text;
    }

    public String getButtonText() {
        return buttonText;
    }

    public static Budget fromButtonText(String buttonText) {
        for (Budget budget : values()) {
            if (budget.getButtonText().equals(buttonText)) {
                return budget;
            }
        }

        throw new IllegalArgumentException("Unknown button text: " + buttonText);
    }
}
