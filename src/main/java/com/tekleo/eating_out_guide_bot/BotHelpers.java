package com.tekleo.eating_out_guide_bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BotHelpers {
    public static KeyboardRow buildKeyboardRow(KeyboardButton ... buttons) {
        KeyboardRow row = new KeyboardRow();
        for (KeyboardButton button : buttons)
            row.add(button);
        return row;
    }

    public static List<KeyboardRow> buildKeyboardRowsWith2ButtonsInRow(List<KeyboardButton> buttons) {
        List<KeyboardRow> rows = new ArrayList<>();

        for (int i = 0; i < buttons.size() / 2; i++) {
            int a = i * 2;
            int b = a + 1;
            rows.add(buildKeyboardRow(buttons.get(a), buttons.get(b)));
        }

        if (buttons.size() % 2 != 0) {
            rows.add(buildKeyboardRow(buttons.get(buttons.size() - 1)));
        }

        return rows;
    }

    public static List<KeyboardRow> buildKeyboardRowsWith2ButtonsInRow(KeyboardButton ... buttons) {
        return buildKeyboardRowsWith2ButtonsInRow(Arrays.asList(buttons));
    }

    public static ReplyKeyboardMarkup buildKeyboardMarkupOneTime(List<KeyboardRow> rows) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);
        markup.setOneTimeKeyboard(true);
        return markup;
    }
}
