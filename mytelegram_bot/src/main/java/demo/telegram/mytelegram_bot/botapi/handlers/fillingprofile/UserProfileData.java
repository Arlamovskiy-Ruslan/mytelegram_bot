package demo.telegram.mytelegram_bot.botapi.handlers.fillingprofile;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileData {
    String name;
    String gender;
    String color;
    String movie;
    String song;
    int age;
    int number;

}
