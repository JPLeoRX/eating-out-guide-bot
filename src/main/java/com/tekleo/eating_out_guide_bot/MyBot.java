package com.tekleo.eating_out_guide_bot;

import com.tekleo.eating_out_guide_bot.review.*;
import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Cuisine;
import com.tekleo.eating_out_guide_bot.review.enums.Score;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class MyBot extends TelegramLongPollingBot {
    private static final ConcurrentHashMap<Long, State> chatStates = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, ReviewFormBuilder> formBuilders = new ConcurrentHashMap<>();

    @Override
    public String getBotToken() {
        return Constants.BOT_API_KEY;
    }

    private ReplyKeyboardMarkup getReplyKeyboard() {
        KeyboardButton button1 = new KeyboardButton(Constants.BUTTON_SUBMIT_NEW_REVIEW);

        KeyboardRow row1 = new KeyboardRow();
        row1.add(button1);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(Arrays.asList(row1));
        markup.setOneTimeKeyboard(true);

        return markup;
    }

    private ReplyKeyboardMarkup getCuisineKeyboard() {
        KeyboardButton button1 = new KeyboardButton(Cuisine.AMERICAN.getButtonText());
        KeyboardButton button2 = new KeyboardButton(Cuisine.JAPANESE.getButtonText());
        KeyboardButton button3 = new KeyboardButton(Cuisine.CHINESE.getButtonText());
        KeyboardButton button4 = new KeyboardButton(Cuisine.ITALIAN.getButtonText());
        KeyboardButton button5 = new KeyboardButton(Cuisine.FRENCH.getButtonText());
        KeyboardButton button6 = new KeyboardButton(Cuisine.MEXICAN.getButtonText());
        KeyboardButton button7 = new KeyboardButton(Cuisine.INDIAN.getButtonText());
        KeyboardButton button8 = new KeyboardButton(Cuisine.MEDITERRANEAN.getButtonText());
        KeyboardButton button9 = new KeyboardButton(Cuisine.UKRAINIAN.getButtonText());
        KeyboardButton button10 = new KeyboardButton(Cuisine.PATISSERIE.getButtonText());

        KeyboardRow row1 = BotHelpers.buildKeyboardRow(button1, button2);
        KeyboardRow row2 = BotHelpers.buildKeyboardRow(button3, button4);
        KeyboardRow row3 = BotHelpers.buildKeyboardRow(button5, button6);
        KeyboardRow row4 = BotHelpers.buildKeyboardRow(button7, button8);
        KeyboardRow row5 = BotHelpers.buildKeyboardRow(button9, button10);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(Arrays.asList(row1, row2, row3, row4, row5));
        markup.setOneTimeKeyboard(true);

        return markup;
    }

    private ReplyKeyboardMarkup getBudgetKeyboard() {
        KeyboardButton button1 = new KeyboardButton(Budget.CHEAP.getButtonText());
        KeyboardButton button2 = new KeyboardButton(Budget.AVERAGE.getButtonText());
        KeyboardButton button3 = new KeyboardButton(Budget.EXPENSIVE.getButtonText());

        KeyboardRow row1 = BotHelpers.buildKeyboardRow(button1);
        KeyboardRow row2 = BotHelpers.buildKeyboardRow(button2);
        KeyboardRow row3 = BotHelpers.buildKeyboardRow(button3);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(Arrays.asList(row1, row2, row3));
        markup.setOneTimeKeyboard(true);

        return markup;
    }

    private ReplyKeyboardMarkup getScoreKeyboard() {
        KeyboardButton button1 = new KeyboardButton(Score.ONE.getButtonText());
        KeyboardButton button2 = new KeyboardButton(Score.TWO.getButtonText());
        KeyboardButton button3 = new KeyboardButton(Score.THREE.getButtonText());
        KeyboardButton button4 = new KeyboardButton(Score.FOUR.getButtonText());
        KeyboardButton button5 = new KeyboardButton(Score.FIVE.getButtonText());

        KeyboardRow row1 = BotHelpers.buildKeyboardRow(button1, button2);
        KeyboardRow row2 = BotHelpers.buildKeyboardRow(button3, button4);
        KeyboardRow row3 = BotHelpers.buildKeyboardRow(button5);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(Arrays.asList(row1, row2, row3));
        markup.setOneTimeKeyboard(true);

        return markup;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // If this was a button press
            if (Constants.BUTTONS.contains(update.getMessage().getText())) {
                System.out.println("Button pressed: " + update.getMessage().getText());
                handleButton(update);
            }

            // If this is a regular text
            else {
                // If current user is not idling (i.e. is inside form submission now)
                if (chatStates.get(update.getMessage().getChatId()) != State.IDLE) {
                    handleForm(update);
                }


                else {
                    System.out.println("Something else: " + update.getMessage().getText());

                    SendMessage message = new SendMessage();
                    message.setChatId(update.getMessage().getChatId());
                    message.setText("Keyboard");
                    message.setReplyMarkup(getReplyKeyboard());

                    try {
                        execute(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //forwardMessage(update);
        }
    }

    @Override
    public String getBotUsername() {
        return "testdates_bot";
    }

    private void handleCommand(String command) {

    }

    private void handleButton(Update update) {
        if (update.getMessage().getText().equals(Constants.BUTTON_SUBMIT_NEW_REVIEW))
            handleStartReviewButton(update);
    }

    private void handleStartReviewButton(Update update) {
        requestName(update);
    }

    // Handle form submission
    //------------------------------------------------------------------------------------------------------------------
    private void handleForm(Update update) {
        if (chatStates.get(update.getMessage().getChatId()) == State.REQUESTED_NAME) {
            acceptName(update);
            requestCuisine(update);
        }

        else if (chatStates.get(update.getMessage().getChatId()) == State.REQUESTED_CUISINE) {
            acceptCuisine(update);
            requestBudget(update);
        }

        else if (chatStates.get(update.getMessage().getChatId()) == State.REQUESTED_BUDGET) {
            acceptBudget(update);
            requestAddress(update);
        }

        else if (chatStates.get(update.getMessage().getChatId()) == State.REQUESTED_ADDRESS) {
            acceptAddress(update);
            requestScore(update);
        }

        else if (chatStates.get(update.getMessage().getChatId()) == State.REQUESTED_SCORE) {
            acceptScore(update);
            requestText(update);
        }

        else if (chatStates.get(update.getMessage().getChatId()) == State.REQUESTED_TEXT) {
            acceptText(update);
            finishForm(update);
        }
    }

    private void acceptName(Update update) {
        System.out.println("Name: " + update.getMessage().getText());

        // Initialize new builder
        formBuilders.put(update.getMessage().getChatId(), new ReviewFormBuilder());

        // Push author into it
        formBuilders.get(update.getMessage().getChatId()).setAuthor(update.getMessage().getFrom().getUserName());

        // Push name into it
        formBuilders.get(update.getMessage().getChatId()).setName(update.getMessage().getText());
    }

    private void acceptCuisine(Update update) {
        System.out.println("Cuisine: " + update.getMessage().getText());

        // Get cuisine
        Cuisine cuisine = Cuisine.fromButtonText(update.getMessage().getText());

        // Push cuisine into builder
        formBuilders.get(update.getMessage().getChatId()).setCuisine(cuisine);
    }

    private void acceptBudget(Update update) {
        System.out.println("Budget: " + update.getMessage().getText());

        // Get budget
        Budget budget = Budget.fromButtonText(update.getMessage().getText());

        // Push budget into builder
        formBuilders.get(update.getMessage().getChatId()).setBudget(budget);
    }

    private void acceptAddress(Update update) {
        System.out.println("Address: " + update.getMessage().getText());

        // Push address into builder
        formBuilders.get(update.getMessage().getChatId()).setAddress(update.getMessage().getText());
    }

    private void acceptScore(Update update) {
        System.out.println("Score: " + update.getMessage().getText());

        // Get score
        Score score = Score.fromButtonText(update.getMessage().getText());

        // Push score into builder
        formBuilders.get(update.getMessage().getChatId()).setScore(score);
    }

    private void acceptText(Update update) {
        System.out.println("Text: " + update.getMessage().getText());

        // Push text into builder
        formBuilders.get(update.getMessage().getChatId()).setText(update.getMessage().getText());
    }

    private void requestName(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_NAME);
        String text = "Enter name: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        sendMessage(message);
    }

    private void requestCuisine(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_CUISINE);
        String text = "Enter cuisine: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(getCuisineKeyboard());
        sendMessage(message);
    }

    private void requestBudget(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_BUDGET);
        String text = "Enter budget: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(getBudgetKeyboard());
        sendMessage(message);
    }

    private void requestAddress(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_ADDRESS);
        String text = "Enter address: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        sendMessage(message);
    }

    private void requestScore(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_SCORE);
        String text = "Enter score: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(getScoreKeyboard());
        sendMessage(message);
    }

    private void requestText(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_TEXT);
        String text = "Enter review text: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        sendMessage(message);
    }

    private void requestPrivacy(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_PRIVACY);
        String text = "Send anonymously: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        sendMessage(message);
    }

    private void finishForm(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.IDLE);
        String text = "Thanks for your review ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(getReplyKeyboard());
        sendMessage(message);

        ReviewForm reviewForm = formBuilders.get(update.getMessage().getChatId()).build();
        ReviewMessage reviewMessage = new ReviewMessage(reviewForm);

        SendMessage message2 = new SendMessage(update.getMessage().getChatId(), reviewMessage.getMessageText());
        message2.setParseMode(ParseMode.HTML);
        sendMessage(message2);
    }
    //------------------------------------------------------------------------------------------------------------------

    private void sendMessage(long chatId, String text) {
        sendMessage(new SendMessage(chatId, text));
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void forwardMessage(Update update) {
        User user = update.getMessage().getFrom();
        long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        String text = "Received message: '" + messageText + "' from user '" + user.getUserName() + "' at char id " + chatId;

        SendMessage message1 = new SendMessage(update.getMessage().getChatId(), text);

        try {
            execute(message1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
