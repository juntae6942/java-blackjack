package blackjack.domain;

import java.util.Random;

public class CardGenerator {

    private static final Random random = new Random();

    public static Card generateCard() {
        CardValue value = randomValue();
        CardSymbol cardSymbol = randomSymbol();
        return new Card(cardSymbol, value);
    }

    private static CardValue randomValue() {
        return new CardValue(random.nextInt(10) + 1);
    }

    private static CardSymbol randomSymbol() {
        CardSymbol[] symbols = CardSymbol.values();
        return symbols[random.nextInt(symbols.length)];
    }
}
