package demo.telegram.mytelegram_bot.botapi.handlers.askdestiny;

import demo.telegram.mytelegram_bot.botapi.BotState;
import demo.telegram.mytelegram_bot.botapi.InputMessageHandler;
import demo.telegram.mytelegram_bot.cache.UserDataCache;
import demo.telegram.mytelegram_bot.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class AskDestinyHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public AskDestinyHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message){
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName(){ return BotState.ASK_DESTINY; }

    private SendMessage processUsersInput(Message inputMsg){
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replayToUser = messagesService.getReplyMessage(chatId, "reply.askDestiny");
        userDataCache.setUsersCurrentBotState(userId,BotState.FILLING_PROFILE);

        return replayToUser;
    }
}
