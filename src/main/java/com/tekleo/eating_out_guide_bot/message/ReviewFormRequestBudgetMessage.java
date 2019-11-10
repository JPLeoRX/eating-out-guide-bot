package com.tekleo.eating_out_guide_bot.message;

import com.tekleo.eating_out_guide_bot.BotUi;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ReviewFormRequestBudgetMessage implements Message {
    @Override
    public String getText() {
        return "Ценовая категория: ";
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getBudgetKeyboard();
    }
}
