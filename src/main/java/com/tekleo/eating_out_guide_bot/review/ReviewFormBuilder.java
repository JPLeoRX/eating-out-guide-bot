package com.tekleo.eating_out_guide_bot.review;

import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Cuisine;
import com.tekleo.eating_out_guide_bot.review.enums.Score;

public class ReviewFormBuilder {
    private String author;
    private String name;
    private Cuisine cuisine;
    private Budget budget;
    private String address;
    private Score score;
    private String text;

    public synchronized ReviewFormBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public synchronized ReviewFormBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public synchronized ReviewFormBuilder setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
        return this;
    }

    public synchronized ReviewFormBuilder setBudget(Budget budget) {
        this.budget = budget;
        return this;
    }

    public synchronized ReviewFormBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public synchronized ReviewFormBuilder setScore(Score score) {
        this.score = score;
        return this;
    }

    public synchronized ReviewFormBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public synchronized ReviewForm build() {
        return new ReviewForm(author, name, cuisine, budget, address, score, text);
    }
}
