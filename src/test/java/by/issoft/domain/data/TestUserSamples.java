package by.issoft.domain.data;

import by.issoft.domain.User;

import java.util.concurrent.ThreadLocalRandom;

public class TestUserSamples {
    private static final String[] firstNames = {"Vasya", "Petya", "Grisha", "Antosha", "Andrysha", "Mikola",
            "Innokentiy", "Hanna", "Oksana", "Katya", "Sveta", "Elena", "Roksolyana"};
    private static final String[] lastNames = {"Petrovich", "Ivanko", "Stepanenko", "Vasilevich", "Davidovich", "Landau"};

    public static User generateUser(){
        ThreadLocalRandom random = ThreadLocalRandom.current();

        User user = new User(firstNames[random.nextInt(firstNames.length - 1)],
                lastNames[random.nextInt(lastNames.length - 1)]);
        user.setBalance(random.nextInt(10000));
        return user;
    }
}
