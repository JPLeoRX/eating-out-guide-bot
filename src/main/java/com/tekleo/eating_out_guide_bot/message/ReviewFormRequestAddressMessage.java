package com.tekleo.eating_out_guide_bot.message;

public class ReviewFormRequestAddressMessage implements Message {
    @Override
    public String getText() {
        return "Адрес или примерное местоположение (район / станция метро): ";
    }
}
