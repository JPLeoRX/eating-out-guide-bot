package com.tekleo.eating_out_guide_bot;

import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Cuisine;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BotUi {
    public static ReplyKeyboardMarkup getCuisineKeyboard() {
        List<KeyboardButton> buttons = Stream.of(Cuisine.values()).map(c -> new KeyboardButton(c.getButtonText())).collect(Collectors.toList());
        List<KeyboardRow> rows = BotHelpers.buildKeyboardRowsWith2ButtonsInRow(buttons);
        return BotHelpers.buildKeyboardMarkupOneTime(rows);
    }

    public static ReplyKeyboardMarkup getBudgetKeyboard() {
        List<KeyboardButton> buttons = Stream.of(Budget.values()).map(b -> new KeyboardButton(b.getButtonText())).collect(Collectors.toList());
        List<KeyboardRow> rows = BotHelpers.buildKeyboardRowsWith1ButtonInRow(buttons);
        return BotHelpers.buildKeyboardMarkupOneTime(rows);
    }
}
