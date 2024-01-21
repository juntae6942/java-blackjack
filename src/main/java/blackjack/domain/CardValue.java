package blackjack.domain;

import java.util.Random;

public class CardValue {

    static Random random = new Random();

    public static int randomValue() {
        return random.nextInt(10) + 1;
    }
}
