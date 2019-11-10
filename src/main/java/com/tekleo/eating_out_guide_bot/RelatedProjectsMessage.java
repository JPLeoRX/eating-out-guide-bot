package com.tekleo.eating_out_guide_bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Arrays;
import java.util.List;

public class RelatedProjectsMessage implements Message {
    @Override
    public String getText() {
        String line1 = "<b>Україна</b>";
        String line2 = "Київ: @eating_out_guide_kyiv";
        String line3 = "Одеса: @eating_out_guide_odessa";
        String line4 = "Львів: @eating_out_guide_lviv";
        String line5 = "Дніпро: @eating_out_guide_dnipro";
        String line6 = "Харків: @eating_out_guide_kharkiv";
//        String line7 = "";
//        String line8 = "<b>Россия</b>";
//        String line9 = "Москва: @eating_out_guide_moscow";
//        String line10 = "Санкт-Петербург: @eating_out_guide_stpetersburg";

        List<String> lines = Arrays.asList(line1, line2, line3, line4, line5, line6);

        return String.join("\n", lines);
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getReplyKeyboard();
    }
}
