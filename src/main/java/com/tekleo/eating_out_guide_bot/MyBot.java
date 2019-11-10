package com.tekleo.eating_out_guide_bot;

import com.tekleo.eating_out_guide_bot.review.*;
import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Confirmation;
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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MyBot extends TelegramLongPollingBot {
    private static final ConcurrentHashMap<Long, State> chatStates = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, ReviewFormBuilder> formBuilders = new ConcurrentHashMap<>();

    @Override
    public String getBotToken() {
        return Constants.BOT_API_KEY;
    }


    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // If this was a command
            if (update.getMessage().getText().startsWith("/")) {
                System.out.println("Command received: " + update.getMessage().getText());
                handleCommand(update);
            }

            // If this was a button press
            else if (Constants.BUTTONS.contains(update.getMessage().getText())) {
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
                    message.setReplyMarkup(BotUi.getReplyKeyboard());

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

    private void handleCommand(Update update) {
        if (update.getMessage().getText().equals("/start")) {
            handleCommandStart(update);
        }
    }

    private void handleCommandStart(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Welcome!");
        message.setReplyMarkup(BotUi.getReplyKeyboard());

        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleButton(Update update) {
        if (update.getMessage().getText().equals(Constants.BUTTON_SUBMIT_NEW_REVIEW))
            handleStartReviewButton(update);
        else if (update.getMessage().getText().equals(Constants.BUTTON_PARTNERSHIP))
            handlePartnershipButton(update);
        else if (update.getMessage().getText().equals(Constants.BUTTON_RELATED_PROJECTS))
            handleRelatedProjectsButton(update);
        else if (update.getMessage().getText().equalsIgnoreCase(Constants.BUTTON_CONTACTS))
            handleContactsButton(update);
    }

    private void handleStartReviewButton(Update update) {
        requestName(update);
    }

    private void handlePartnershipButton(Update update) {

    }

    private void handleRelatedProjectsButton(Update update) {
        String line1 = "<b>Ukraine</b>";
        String line2 = "Kyiv: @eating_out_guide_kyiv";
        String line3 = "Odessa: @eating_out_guide_odessa";
        String line4 = "Lviv: @eating_out_guide_lviv";
        String line5 = "Dnipro: @eating_out_guide_dnipro";
        String line6 = "Kharkiv: @eating_out_guide_kharkiv";
        String line7 = "";
        String line8 = "<b>Russia</b>";
        String line9 = "Moscow: @eating_out_guide_moscow";
        String line10 = "St. Petersburg: @eating_out_guide_stpetersburg";

        List<String> lines = Arrays.asList(line1, line2, line3, line4, line5, line6, line7, line8, line9, line10);

        String text = String.join("\n", lines);
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setParseMode(ParseMode.HTML);
        message.setReplyMarkup(BotUi.getReplyKeyboard());
        sendMessage(message);
    }

    private void handleContactsButton(Update update) {
        String line1 = "Main channel: @eating_out_guide_kyiv";
        String line2 = "Bot developer: @jpleorx";
        String line3 = "Support: @medium_mara";
        String line4 = "";
        String line5 = "If you encounter any issues with this bot don't hesitate to contact us";
        List<String> lines = Arrays.asList(line1, line2, line3, line4, line5);

        String text = String.join("\n", lines);
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(BotUi.getReplyKeyboard());
        sendMessage(message);
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
            requestConfirmation(update);
        }

        else if (chatStates.get(update.getMessage().getChatId()) == State.REQUESTED_CONFIRMATION) {
            acceptConfirmation(update);
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

    private void acceptConfirmation(Update update) {
        System.out.println("Confirmation: " + update.getMessage().getText());

        // Get confirmation
        Confirmation confirmation = Confirmation.fromButtonText(update.getMessage().getText());

        // If submitted
        if (Confirmation.YES == confirmation)
            finishForm(update);
        // If cancelled
        else if (Confirmation.NO_CANCEL == confirmation)
            cancelForm(update);
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
        message.setReplyMarkup(BotUi.getCuisineKeyboard());
        sendMessage(message);
    }

    private void requestBudget(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_BUDGET);
        String text = "Enter budget: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(BotUi.getBudgetKeyboard());
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
        message.setReplyMarkup(BotUi.getScoreKeyboard());
        sendMessage(message);
    }

    private void requestText(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_TEXT);
        String text = "Enter review text: ";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        sendMessage(message);
    }

    private void requestConfirmation(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_CONFIRMATION);
        ReviewForm reviewForm = formBuilders.get(update.getMessage().getChatId()).build();
        ReviewMessage reviewMessage = new ReviewMessage(reviewForm);
        String text = "Confirm your review?\n\n\n" + reviewMessage.getMessageText();
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setParseMode(ParseMode.HTML);
        message.setReplyMarkup(BotUi.getConfirmationKeyboard());
        sendMessage(message);
    }

    private void finishForm(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.IDLE);
        String text = "Thanks for your review";

        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(BotUi.getReplyKeyboard());
        sendMessage(message);

        ReviewForm reviewForm = formBuilders.get(update.getMessage().getChatId()).build();
        ReviewMessage reviewMessage = new ReviewMessage(reviewForm);

        SendMessage message2 = new SendMessage(Constants.CHANNEL_USERNAME, reviewMessage.getMessageText());
        message2.setParseMode(ParseMode.HTML);
        sendMessage(message2);
    }

    private void cancelForm(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.IDLE);
        String text = "Review cancelled";
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setReplyMarkup(BotUi.getReplyKeyboard());
        sendMessage(message);
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
