package com.tekleo.eating_out_guide_bot;

import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Confirmation;
import com.tekleo.eating_out_guide_bot.review.enums.Cuisine;
import com.tekleo.eating_out_guide_bot.review.enums.Score;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BotUi {
    public static ReplyKeyboardMarkup getReplyKeyboard() {
        KeyboardRow row1 = BotHelpers.buildKeyboardRow(new KeyboardButton(Constants.BUTTON_SUBMIT_NEW_REVIEW));
        KeyboardRow row2 = BotHelpers.buildKeyboardRow(new KeyboardButton(Constants.BUTTON_PARTNERSHIP));
        KeyboardRow row3 = BotHelpers.buildKeyboardRow(new KeyboardButton(Constants.BUTTON_RELATED_PROJECTS), new KeyboardButton(Constants.BUTTON_CONTACTS));
        return BotHelpers.buildKeyboardMarkupOneTime(Arrays.asList(row1, row2, row3));
    }

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

    public static ReplyKeyboardMarkup getScoreKeyboard() {
        List<KeyboardButton> buttons = Stream.of(Score.values()).map(s -> new KeyboardButton(s.getButtonText())).collect(Collectors.toList());
        List<KeyboardRow> rows = BotHelpers.buildKeyboardRowsWith2ButtonsInRow(buttons);
        return BotHelpers.buildKeyboardMarkupOneTime(rows);
    }

    public static ReplyKeyboardMarkup getConfirmationKeyboard() {
        List<KeyboardButton> buttons = Stream.of(Confirmation.values()).map(v -> new KeyboardButton(v.getButtonText())).collect(Collectors.toList());
        List<KeyboardRow> rows = BotHelpers.buildKeyboardRowsWith1ButtonInRow(buttons);
        return BotHelpers.buildKeyboardMarkupOneTime(rows);
    }
}
