package blackjack.domain;

import java.util.Random;

public enum CardSymbol {
    CLOVER("클로버"), HEART("하트"), SPADE("스페이드"), DIAMOND("다이아몬드");
    private static final Random random = new Random();
    final private String name;

    CardSymbol(String name) {
        this.name = name;
    }

    public static CardSymbol randomSymbol() {
        CardSymbol[] symbols = values();
        return symbols[random.nextInt(symbols.length)];
    }

    public String getName() {
        return name;
    }
}
