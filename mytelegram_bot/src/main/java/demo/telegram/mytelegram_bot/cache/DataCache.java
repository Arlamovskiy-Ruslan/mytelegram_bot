package demo.telegram.mytelegram_bot.cache;

import demo.telegram.mytelegram_bot.botapi.BotState;
import demo.telegram.mytelegram_bot.botapi.handlers.UserProfileData;

public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfileData getUsersProfileData(int userId);

    void saveUserProileData(int userId, UserProfileData userProfileData);
}
