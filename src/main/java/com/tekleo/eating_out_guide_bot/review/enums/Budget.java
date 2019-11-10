package com.tekleo.eating_out_guide_bot.review.enums;

import com.tekleo.eating_out_guide_bot.Constants;

public enum Budget {
    CHEAP(Constants.BUTTON_BUDGET_CHEAP),
    AVERAGE(Constants.BUTTON_BUDGET_AVERAGE),
    EXPENSIVE(Constants.BUTTON_BUDGET_EXPENSIVE);

    private String buttonText;

    Budget(String buttonText) {
        this.buttonText = buttonText;
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
