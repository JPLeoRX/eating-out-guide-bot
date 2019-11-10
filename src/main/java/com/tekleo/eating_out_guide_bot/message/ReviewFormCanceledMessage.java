package com.tekleo.eating_out_guide_bot.message;

import com.tekleo.eating_out_guide_bot.BotUi;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ReviewFormCanceledMessage implements Message {
    @Override
    public String getText() {
        return "Отзыв отменен.";
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getReplyKeyboard();
    }
}
