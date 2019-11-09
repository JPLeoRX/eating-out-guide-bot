package com.tekleo.eating_out_guide_bot.review;

import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Cuisine;
import com.tekleo.eating_out_guide_bot.review.enums.Privacy;
import com.tekleo.eating_out_guide_bot.review.enums.Score;

public class ReviewForm {
    private String author;
    private String name;
    private Cuisine cuisine;
    private Budget budget;
    private String address;
    private Score score;
    private String text;
    private Privacy privacy;

    public ReviewForm(String author, String name, Cuisine cuisine, Budget budget, String address, Score score, String text, Privacy privacy) {
        this.author = author;
        this.name = name;
        this.cuisine = cuisine;
        this.budget = budget;
        this.address = address;
        this.score = score;
        this.text = text;
        this.privacy = privacy;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public Budget getBudget() {
        return budget;
    }

    public String getAddress() {
        return address;
    }

    public Score getScore() {
        return score;
    }

    public String getText() {
        return text;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    @Override
    public String toString() {
        return "ReviewForm{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", cuisine=" + cuisine +
                ", budget=" + budget +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", text='" + text + '\'' +
                ", privacy=" + privacy +
                '}';
    }
}