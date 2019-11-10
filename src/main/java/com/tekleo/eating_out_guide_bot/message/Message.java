package com.tekleo.eating_out_guide_bot.message;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface Message {
    String getText();

    default boolean hasReplyKeyboard() {
        return getReplyKeyboard() != null;
    }

    default ReplyKeyboard getReplyKeyboard() {
        return null;
    }

    default String getParseMode() {
        return ParseMode.HTML;
    }

    default SendMessage toSendMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(getText());
        message.setParseMode(getParseMode());
        if (hasReplyKeyboard())
            message.setReplyMarkup(getReplyKeyboard());
        return message;
    }

    default SendMessage toSendMessage(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(getText());
        message.setParseMode(getParseMode());
        if (hasReplyKeyboard())
            message.setReplyMarkup(getReplyKeyboard());
        return message;
    }

    default SendMessage toSendMessage(Update update) {
        return toSendMessage(update.getMessage().getChatId());
    }
}