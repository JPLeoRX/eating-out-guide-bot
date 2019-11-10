package com.tekleo.eating_out_guide_bot.message;

import com.tekleo.eating_out_guide_bot.BotUi;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Arrays;
import java.util.List;

public class PartnershipMessage implements Message {
    @Override
    public String getText() {
        String line1 = "По вопросам возможного сотрудничества:";
        String line2 = "@jpleorx";
        String line3 = "@baba_mara";
        List<String> lines = Arrays.asList(line1, line2, line3);

        return String.join("\n", lines);
    }

    @Override
    public ReplyKeyboard getReplyKeyboard() {
        return BotUi.getReplyKeyboard();
    }
}
