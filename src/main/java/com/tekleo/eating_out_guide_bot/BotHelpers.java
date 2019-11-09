package com.tekleo.eating_out_guide_bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class BotHelpers {
    public static KeyboardRow buildKeyboardRow(KeyboardButton ... buttons) {
        KeyboardRow row = new KeyboardRow();
        for (KeyboardButton button : buttons)
            row.add(button);
        return row;
    }
}
