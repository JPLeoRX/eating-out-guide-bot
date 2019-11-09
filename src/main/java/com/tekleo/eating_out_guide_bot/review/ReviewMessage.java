package com.tekleo.eating_out_guide_bot.review;

import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Cuisine;
import com.tekleo.eating_out_guide_bot.review.enums.Score;

import java.util.Arrays;
import java.util.List;

public class ReviewMessage {
    private String author;
    private String name;
    private Cuisine cuisine;
    private Budget budget;
    private String address;
    private Score score;
    private String text;
    private long createdAt;

    public ReviewMessage(String author, String name, Cuisine cuisine, Budget budget, String address, Score score, String text, long createdAt) {
        this.author = author;
        this.name = name;
        this.cuisine = cuisine;
        this.budget = budget;
        this.address = address;
        this.score = score;
        this.text = text;
        this.createdAt = createdAt;
    }

    public ReviewMessage(ReviewForm reviewForm) {
        this(reviewForm.getAuthor(), reviewForm.getName(), reviewForm.getCuisine(), reviewForm.getBudget(), reviewForm.getAddress(), reviewForm.getScore(), reviewForm.getText(), System.currentTimeMillis());
    }

    public String getMessageText() {
        String line1 = getHashtagFromName(name) + " " + getHashtagFromCuisine(cuisine) + " " + getHashtagFromBudget(budget);
        String line2 = "--------------------------";
        String line3 = "<b>Name:</b> " + name;
        String line4 = "<b>Cuisine:</b> " + cuisine.getButtonText();
        String line5 = "<b>Budget:</b> " + budget.getButtonText() + " (" + budget.getText() + ")";
        String line6 = "<b>Address:</b> " + address;
        String line7 = "<b>Score:</b> " + score.getButtonText();
        String line8 = "--------------------------";
        String line9 = text;

        List<String> lines = Arrays.asList(line1, line2, line3, line4, line5, line6, line7, line8, line9);
        return String.join("\n", lines);
    }

    private static String getHashtagFromName(String name) {
        return "#" + name.toLowerCase().replaceAll(" ", "_");
    }

    private static String getHashtagFromCuisine(Cuisine cuisine) {
        return "#" + cuisine.getButtonText().toLowerCase();
    }

    private static String getHashtagFromBudget(Budget budget) {
        return "#" + budget.getText().toLowerCase();
    }
}