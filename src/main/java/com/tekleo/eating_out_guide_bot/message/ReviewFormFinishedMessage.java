package com.tekleo.eating_out_guide_bot.message;

import com.tekleo.eating_out_guide_bot.BotUi;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ReviewFormFinishedMessage implements Message {
    @Override
    public String getText() {
        return "Благодарим за ваш отзыв, он будет опубликован в ближайшее время.";
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getReplyKeyboard();
    }
}
