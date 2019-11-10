package com.tekleo.eating_out_guide_bot.message;

public class ReviewFormRequestNameMessage implements Message {
    @Override
    public String getText() {
        return "Название заведения: ";
    }
}
