import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public final String nameBot = "My_first_test2000_bot";
    public final String token = "5524369870:AAFoe_5x9h97OrPvktA2kxhSchh6gNz3vJA";

    @Override
    public String getBotUsername() {
        return nameBot;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        boolean valid = true;
        if (update.getMessage() != null && update.getMessage().hasText()) {
            switch (update.getMessage().getText()) {
                case "/start":
                    sendMessage(update, new Setting().TEXT_ON_START);break;
                default: valid = new Validation().formValidation(update.getMessage().getText());
            }
            if (valid == true) {
                main(update, update.getMessage().getText());
            }else {
                sendMessage(update, new Setting().TEXT_WRONG);
            }
        }

    }
    public void sendMessage (Update update, String message) {
        long text_id = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(text_id));
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void main (Update update, String message) {
            if (message == null) {
            } else if (message.contains("(") || message.contains(")")) {
                ArrayList<String> list = new Counter().split(update.getMessage().getText());
                list = new Counter().methodWithBracket(list);
                sendMessage(update, list.toString().replaceAll("^\\[|]$", ""));
            } else {
                ArrayList<String> list1 = new Counter().split(message);
                if (list1.contains("*") || list1.contains("/")) {
                    new Counter().methodMultiplicationAndDivision(list1);
                } else {
                    new Counter().methodAdditionAndSubtraction(list1);
                }
                new Counter().setResult(new Counter().list1);
                sendMessage(update, list1.toString().replaceAll("^\\[|]$", ""));
            }
    }

}
