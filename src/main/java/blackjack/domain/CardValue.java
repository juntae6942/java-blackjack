package blackjack.domain;

import java.util.Random;

public class CardValue {

    private static final Random random = new Random();

    public static int randomValue() {
        return random.nextInt(10) + 1;
    }
}
