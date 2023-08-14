package user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserData {
    public static User getUserData(){
        final String email = "diplom_qa_java_21@yandex.ru";
        final String password = "12345";
        final String name = "Диплом";
        return new User(email, password, name);
    }

//    public static User getUserDataNew(){
//        final String email = "diplom_qa_java_21_sun@yandex.ru";
//        final String password = "1234567";
//        final String name = "Диплом2";
//        return new User(email, password, name);
//    }

    public static User getUserDataWithoutEmail(){
        final String email = null;
        final String password = "12345";
        final String name = "Диплом";
        return new User(email, password, name);
    }
    public static User getUserDataWithoutPassword(){
        final String email = "diplom_qa_java_21@yandex.ru";
        final String password = null;
        final String name = "Диплом";
        return new User(email, password, name);
    }
    public static User getUserDataWithoutName(){
        final String email = "diplom_qa_java_21@yandex.ru";
        final String password = "12345";
        final String name = null;
        return new User(email, password, name);
    }

    public static User getRandom(){
        final String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new User(email, password, name);
    }

    public static User getUserDataLogin(){
        final String email = "diplom_qa_java_21@yandex.ru";
        final String password = "12345";
        return new User(email, password);
    }

    public static User getUserDataLoginWrongEmail(){
        final String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        final String password = "12345";
        return new User(email, password);
    }

    public static User getUserDataLoginWrongPassword(){
        final String email = "diplom_qa_java_21@yandex.ru";
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new User(email, password);
    }
}
