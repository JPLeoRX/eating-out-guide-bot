package com.tekleo.eating_out_guide_bot;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String BOT_API_KEY = "931476299:AAFQB5W87R9NLvDqOaHLx6cBfOF82WOHRiI";
    public static final String CHANNEL_USERNAME = "@testdates";

    public static final String BUTTON_SUBMIT_NEW_REVIEW = "Submit new review";
    public static final List<String> BUTTONS = Arrays.asList(BUTTON_SUBMIT_NEW_REVIEW);

    public static final String CUISINE_BUTTON_JAPANESE = "Japanese";
    public static final String CUISINE_BUTTON_CHINESE = "Chinese";
    public static final String CUISINE_BUTTON_ITALIAN = "Italian";
    public static final String CUISINE_BUTTON_FRENCH = "French";
    public static final String CUISINE_BUTTON_MEXICAN = "Mexican";
    public static final String CUISINE_BUTTON_INDIAN = "Indian";
    public static final String CUISINE_BUTTON_MEDITERRANEAN = "Mediterranean";
    public static final List<String> CUISINE_BUTTONS = Arrays.asList(CUISINE_BUTTON_JAPANESE, CUISINE_BUTTON_CHINESE, CUISINE_BUTTON_ITALIAN, CUISINE_BUTTON_FRENCH, CUISINE_BUTTON_MEXICAN, CUISINE_BUTTON_INDIAN, CUISINE_BUTTON_MEDITERRANEAN);

    public static final String BUDGET_BUTTON_LOW = "Low";
    public static final String BUDGET_BUTTON_AVERAGE = "Average";
    public static final String BUDGET_BUTTON_HIGH = "High";
    public static final List<String> BUDGET_BUTTONS = Arrays.asList(BUDGET_BUTTON_LOW, BUDGET_BUTTON_AVERAGE, BUDGET_BUTTON_HIGH);

    public static final String SCORE_BUTTON_1 = "1";
    public static final String SCORE_BUTTON_2 = "2";
    public static final String SCORE_BUTTON_3 = "3";
    public static final String SCORE_BUTTON_4 = "4";
    public static final String SCORE_BUTTON_5 = "5";
    public static final List<String> SCORE_BUTTONS = Arrays.asList(SCORE_BUTTON_1, SCORE_BUTTON_2, SCORE_BUTTON_3, SCORE_BUTTON_4, SCORE_BUTTON_5);
}
