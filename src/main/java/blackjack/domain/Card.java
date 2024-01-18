package blackjack.domain;

import blackjack.dto.CardDto;

import java.util.Objects;

public class Card {
    private static final int ACE = 11;
    private final CardSymbol symbol;
    private int num;

    public Card(CardSymbol symbol, int num) {
        this.symbol = symbol;
        this.num = num;
    }
    public boolean isAce() {
        return this.symbol.equals(CardSymbol.SPADE) && this.num==1;
    }

    public void changeNum() {
        this.num = ACE;
    }
    public CardDto returnCardDto() {
        return new CardDto(num, symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return num == card.num && symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, num);
    }

    @Override
    public String toString() {
        return num + symbol.getName();
    }
}
