package com.tekleo.eating_out_guide_bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Arrays;
import java.util.List;

public class ContactsMessage implements Message {
    @Override
    public String getText() {
        String line1 = "Канал: @eating_out_guide_kyiv";
        String line2 = "Разработка бота: @jpleorx";
        String line3 = "Поддержка: @baba_mara";
        String line4 = "";
        String line5 = "При возникновении дополнительных вопросов, пожалуйста обращайтесь к нам.";
        List<String> lines = Arrays.asList(line1, line2, line3, line4, line5);

        return String.join("\n", lines);
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getReplyKeyboard();
    }
}
