package com.tekleo.eating_out_guide_bot;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String BOT_API_KEY = "1048763656:AAE8-bm3mRRjBFF1jcxvUsExmraelbYwp2s";
    public static final String CHANNEL_USERNAME = "@eating_out_guide_kyiv";

    public static final String BUTTON_SUBMIT_NEW_REVIEW = "Написать отзыв " + Emoji.NOTEPAD.getText();
    public static final String BUTTON_PARTNERSHIP = "Сотрудничество " + Emoji.CHART.getText();
    public static final String BUTTON_RELATED_PROJECTS = "Наши проекты " + Emoji.PC.getText();
    public static final String BUTTON_CONTACTS = "Контакты " + Emoji.EMAIL_INCOMING.getText();
    public static final List<String> BUTTONS = Arrays.asList(BUTTON_SUBMIT_NEW_REVIEW, BUTTON_PARTNERSHIP, BUTTON_RELATED_PROJECTS, BUTTON_CONTACTS);

    public static final String BUTTON_CUISINE_AMERICAN = "Американская " + Emoji.FOOD_BURGER.getText() ;
    public static final String BUTTON_CUISINE_JAPANESE = "Японская " + Emoji.FOOD_SASHIMI.getText() ;
    public static final String BUTTON_CUISINE_CHINESE = "Китайская " + Emoji.FOOD_NOODLES.getText() ;
    public static final String BUTTON_CUISINE_ITALIAN = "Итальянская " + Emoji.FOOD_PASTA.getText() ;
    public static final String BUTTON_CUISINE_FRENCH = "Французская " + Emoji.FOOD_ONION_SOUP.getText() ;
    public static final String BUTTON_CUISINE_MEXICAN = "Мексиканская " + Emoji.FOOD_TACO.getText() ;
    public static final String BUTTON_CUISINE_INDIAN = "Индийская " + Emoji.FOOD_CURRY.getText() ;
    public static final String BUTTON_CUISINE_MEDITERRANEAN = "Средиземноморская " + Emoji.FOOD_GREEN_SALAD.getText() ;
    public static final String BUTTON_CUISINE_UKRAINIAN = "Українська " + Emoji.FOOD_SOUP.getText() ;
    public static final String BUTTON_CUISINE_PATISSERIE = "Кондитерские " + Emoji.FOOD_CAKE_STRAWBERRY.getText() ;
    public static final String BUTTON_CUISINE_FASTFOOD = "Фаст-фуд " + Emoji.FOOD_FRIES.getText() ;
    public static final String BUTTON_CUISINE_OTHER = "Другое " + Emoji.FOOD_FORK.getText();

    public static final String BUTTON_BUDGET_CHEAP = Emoji.DOLLAR.getText() + " (Дешево)";
    public static final String BUTTON_BUDGET_AVERAGE = Emoji.DOLLAR.getText() + Emoji.DOLLAR.getText() + " (Средне)";
    public static final String BUTTON_BUDGET_EXPENSIVE = Emoji.DOLLAR.getText() + Emoji.DOLLAR.getText() + Emoji.DOLLAR.getText() + " (Дорого)";
}
