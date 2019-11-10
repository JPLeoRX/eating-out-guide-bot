package com.tekleo.eating_out_guide_bot.message;

import com.tekleo.eating_out_guide_bot.BotUi;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ReviewFormRequestCuisineMessage implements Message {
    @Override
    public String getText() {
        return "Кухня: ";
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getCuisineKeyboard();
    }
}
