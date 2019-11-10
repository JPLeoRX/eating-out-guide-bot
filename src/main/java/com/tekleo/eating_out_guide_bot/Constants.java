package com.tekleo.eating_out_guide_bot;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String BOT_API_KEY = "931476299:AAFQB5W87R9NLvDqOaHLx6cBfOF82WOHRiI";
    public static final String CHANNEL_USERNAME = "@eating_out_guide";

    public static final String BUTTON_SUBMIT_NEW_REVIEW = "Submit new review " + Emoji.NOTEPAD.getText();
    public static final String BUTTON_PARTNERSHIP = "Partnership " + Emoji.CHART.getText();
    public static final String BUTTON_RELATED_PROJECTS = "Related projects " + Emoji.PC.getText();
    public static final String BUTTON_CONTACTS = "Contacts " + Emoji.EMAIL_INCOMING.getText();
    public static final List<String> BUTTONS = Arrays.asList(BUTTON_SUBMIT_NEW_REVIEW, BUTTON_PARTNERSHIP, BUTTON_RELATED_PROJECTS, BUTTON_CONTACTS);
}
