package com.tekleo.eating_out_guide_bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class WelcomeMessage implements Message {
    @Override
    public String getText() {
        return "Вас приветствует бот канала " + Constants.CHANNEL_USERNAME + ".\n\nВыберите действие ниже.";
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getReplyKeyboard();
    }
}
