package com.tekleo.eating_out_guide_bot.message;

public class ReviewFormRequestTextMessage implements Message {
    @Override
    public String getText() {
        return "Напишите ваш отзыв, ваше впечатление от заведения: ";
    }
}
