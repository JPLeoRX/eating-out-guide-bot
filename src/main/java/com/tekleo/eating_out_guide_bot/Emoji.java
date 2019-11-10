package com.tekleo.eating_out_guide_bot;

public enum Emoji {
    FOOD_GREEN_SALAD("\uD83E\uDD57"),
    FOOD_TACO("\uD83C\uDF2E"),
    FOOD_ONION_SOUP("\uD83C\uDF75"),
    FOOD_CURRY("\uD83C\uDF5B"),
    FOOD_NOODLES("\uD83C\uDF5C"),
    FOOD_PASTA("\uD83C\uDF5D"),
    FOOD_BURGER("\uD83C\uDF54"),
    FOOD_PIZZA("\uD83C\uDF55"),
    FOOD_SOUP("\uD83C\uDF72"),
    FOOD_CAKE_STRAWBERRY("\uD83C\uDF70"),
    FOOD_SASHIMI("\uD83C\uDF63"),
    FOOD_FRIES("\uD83C\uDF5F"),
    FOOD_FORK("\uD83C\uDF74"),

    NOTEPAD("\uD83D\uDCDD"),
    CHART("\uD83D\uDCCA"),
    PC("\uD83D\uDCBB"),
    EMAIL_INCOMING("\uD83D\uDCE9"),
    
    STAR("\u2B50"),
    DOLLAR("\uD83D\uDCB5");

    private String text;

    Emoji(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
