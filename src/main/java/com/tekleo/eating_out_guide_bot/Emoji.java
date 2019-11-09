package com.tekleo.eating_out_guide_bot;

public enum Emoji {
    STAR("\u2B50");

    private String text;

    Emoji(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}