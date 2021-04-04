package demo.telegram.mytelegram_bot.botapi;

import demo.telegram.mytelegram_bot.botapi.BotState;
import demo.telegram.mytelegram_bot.botapi.BotStateContext;
import demo.telegram.mytelegram_bot.cache.UserDataCache;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
    }

    public SendMessage handleUpdate(Update update){
        SendMessage replyMessage = null;

        Message message = update.getMessage();
        if (message != null && message.hasText()){
           log.printf("New message from User:{}, chatId: {}, with text:{}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message){
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg){
            case "/start" :
                botState = BotState.ASK_DESTINY;
                break;
            case "Получить предсказание":
                botState = BotState.FILLING_PROFILE;
                break;
            case "Помощь":
                botState = BotState.SHOW_HELP_MENU;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }
}
