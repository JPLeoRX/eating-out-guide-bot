package com.tekleo.eating_out_guide_bot;

import com.tekleo.eating_out_guide_bot.message.*;
import com.tekleo.eating_out_guide_bot.review.*;
import com.tekleo.eating_out_guide_bot.review.enums.Budget;
import com.tekleo.eating_out_guide_bot.review.enums.Confirmation;
import com.tekleo.eating_out_guide_bot.review.enums.Cuisine;
import com.tekleo.eating_out_guide_bot.review.enums.Score;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
                System.out.println("Command received: " + update.getMessage().getText() + " from " + update.getMessage().getFrom().getUserName());
                handleCommand(update);
            }

            // If this was a button press
            else if (Constants.BUTTONS.contains(update.getMessage().getText())) {
                System.out.println("Button pressed: " + update.getMessage().getText() + " from " + update.getMessage().getFrom().getUserName());
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
        }
    }

    @Override
    public String getBotUsername() {
        return "eating_out_guide_kyiv_bot";
    }

    private void handleCommand(Update update) {
        if (update.getMessage().getText().equals("/start")) {
            handleCommandStart(update);
        }
    }

    private void handleCommandStart(Update update) {
        sendMessage(new WelcomeMessage().toSendMessage(update));
    }

    // Handle buttons
    //------------------------------------------------------------------------------------------------------------------
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
        sendMessage(new PartnershipMessage().toSendMessage(update));
    }

    private void handleRelatedProjectsButton(Update update) {
        sendMessage(new RelatedProjectsMessage().toSendMessage(update));
    }

    private void handleContactsButton(Update update) {
        sendMessage(new ContactsMessage().toSendMessage(update));
    }
    //------------------------------------------------------------------------------------------------------------------



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
        // Initialize new builder
        formBuilders.put(update.getMessage().getChatId(), new ReviewFormBuilder());

        // Push author into it
        formBuilders.get(update.getMessage().getChatId()).setAuthor(update.getMessage().getFrom().getUserName());

        // Push name into it
        formBuilders.get(update.getMessage().getChatId()).setName(update.getMessage().getText());
    }

    private void acceptCuisine(Update update) {
        // Get cuisine
        Cuisine cuisine = Cuisine.fromButtonText(update.getMessage().getText());

        // Push cuisine into builder
        formBuilders.get(update.getMessage().getChatId()).setCuisine(cuisine);
    }

    private void acceptBudget(Update update) {
        // Get budget
        Budget budget = Budget.fromButtonText(update.getMessage().getText());

        // Push budget into builder
        formBuilders.get(update.getMessage().getChatId()).setBudget(budget);
    }

    private void acceptAddress(Update update) {
        // Push address into builder
        formBuilders.get(update.getMessage().getChatId()).setAddress(update.getMessage().getText());
    }

    private void acceptScore(Update update) {
        // Get score
        Score score = Score.fromButtonText(update.getMessage().getText());

        // Push score into builder
        formBuilders.get(update.getMessage().getChatId()).setScore(score);
    }

    private void acceptText(Update update) {
        // Push text into builder
        formBuilders.get(update.getMessage().getChatId()).setText(update.getMessage().getText());
    }

    private void acceptConfirmation(Update update) {
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
        sendMessage(new ReviewFormRequestNameMessage().toSendMessage(update));
    }

    private void requestCuisine(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_CUISINE);
        sendMessage(new ReviewFormRequestCuisineMessage().toSendMessage(update));
    }

    private void requestBudget(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_BUDGET);
        sendMessage(new ReviewFormRequestBudgetMessage().toSendMessage(update));
    }

    private void requestAddress(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_ADDRESS);
        sendMessage(new ReviewFormRequestAddressMessage().toSendMessage(update));
    }

    private void requestScore(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_SCORE);
        sendMessage(new ReviewFormRequestScoreMessage().toSendMessage(update));
    }

    private void requestText(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_TEXT);
        sendMessage(new ReviewFormRequestTextMessage().toSendMessage(update));
    }

    private void requestConfirmation(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.REQUESTED_CONFIRMATION);
        ReviewForm reviewForm = formBuilders.get(update.getMessage().getChatId()).build();
        ReviewMessage reviewMessage = new ReviewMessage(reviewForm);
        String text = "Ваш отзыв будет выглядеть так. Подтверждаете отправку?\n\n\n" + reviewMessage.getMessageText();
        SendMessage message = new SendMessage(update.getMessage().getChatId(), text);
        message.setParseMode(ParseMode.HTML);
        message.setReplyMarkup(BotUi.getConfirmationKeyboard());
        sendMessage(message);
    }

    private void finishForm(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.IDLE);
        sendMessage(new ReviewFormFinishedMessage().toSendMessage(update));

        ReviewForm reviewForm = formBuilders.get(update.getMessage().getChatId()).build();
        ReviewMessage reviewMessage = new ReviewMessage(reviewForm);
        SendMessage message2 = new SendMessage(Constants.CHANNEL_USERNAME, reviewMessage.getMessageText());
        message2.setParseMode(ParseMode.HTML);
        sendMessage(message2);
    }

    private void cancelForm(Update update) {
        chatStates.put(update.getMessage().getChatId(), State.IDLE);
        sendMessage(new ReviewFormCanceledMessage().toSendMessage(update));
    }
    //------------------------------------------------------------------------------------------------------------------

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
