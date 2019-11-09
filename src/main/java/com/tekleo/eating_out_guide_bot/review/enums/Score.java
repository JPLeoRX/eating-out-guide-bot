package com.tekleo.eating_out_guide_bot.review.enums;

import com.tekleo.eating_out_guide_bot.Emoji;

public enum Score {
    ONE(1, Emoji.STAR.getText()),
    TWO(2, ONE.getButtonText() + Emoji.STAR.getText()),
    THREE(3, TWO.getButtonText() + Emoji.STAR.getText()),
    FOUR(4, THREE.getButtonText() + Emoji.STAR.getText()),
    FIVE(5, FOUR.getButtonText() + Emoji.STAR.getText());

    private int intValue;
    private String buttonText;

    Score(int intValue, String buttonText) {
        this.intValue = intValue;
        this.buttonText = buttonText;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getButtonText() {
        return buttonText;
    }

    public static Score fromIntValue(int intValue) {
        for (Score score : values()) {
            if (score.getIntValue() == intValue) {
                return score;
            }
        }

        throw new IllegalArgumentException("Unknown int value: " + intValue);
    }

    public static Score fromButtonText(String buttonText) {
        for (Score score : values()) {
            if (score.getButtonText().equals(buttonText)) {
                return score;
            }
        }

        throw new IllegalArgumentException("Unknown button text: " + buttonText);
    }
}
