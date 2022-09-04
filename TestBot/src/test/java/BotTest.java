
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class BotTest {

    @Mock
    Update update = new Update();
    @Spy
    Message message = new Message();

    @Test
    public void onUpdateReceived() {
        boolean valid = true;
        Mockito.doReturn(argsProviderFactory()).when(update).getMessage();
        if (update.getMessage() != null && update.getMessage().hasText()) {
            switch (update.getMessage().getText()) {
                case "/start":
                    Assert.assertEquals("/start", update.getMessage().getText());
                    //sendMessage(update, new Setting().TEXT_ON_START);break;
                default: valid = new ValidationTest().formValidation(update.getMessage().getText());
                    Assert.assertTrue(valid);
            }
            if (valid) {
                //main(update, update.getMessage().getText());
            }else {
                //sendMessage(update, new Setting().TEXT_WRONG);
            }
        }

    }
    public Message argsProviderFactory() {
        message.setText("2+2");
        return message;
    }
    @Test
    public void main () {
        String message1 = message.getText();
        Assert.assertNotNull(message);
        if (message1 == null) {
        } else if (message1.contains("(") || message1.contains(")")) {
            ArrayList<String> list = new Counter().split(update.getMessage().getText());
            list = new Counter().methodWithBracket(list);
            //sendMessage(update, list.toString().replaceAll("^\\[|]$", ""));
        } else {
            ArrayList<String> list1 = new Counter().split(message1);
            if (list1.contains("*") || list1.contains("/")) {
                new Counter().methodMultiplicationAndDivision(list1);
            } else {
                new Counter().methodAdditionAndSubtraction(list1);
            }
            new Counter().setResult(new Counter().list1);
            //sendMessage(update, list1.toString().replaceAll("^\\[|]$", ""));
        }
    }

}
