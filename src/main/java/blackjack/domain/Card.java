package blackjack.domain;

import blackjack.dto.CardDto;

import java.util.Objects;

public class Card implements Comparable<Card>{

    private static final int ACE = 11;

    private final CardSymbol symbol;
    private final CardValue cardValue;

    public Card(CardSymbol symbol, CardValue cardValue) {
        this.symbol = symbol;
        this.cardValue = cardValue;
    }

    public boolean isSpadeAce() {
        return this.symbol.equals(CardSymbol.SPADE) && this.cardValue.getCardValue()==1;
    }

    public void changeAceNumber() {
        this.cardValue.setCardValue(ACE);
    }

    public CardValue cardValue() {
        return cardValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue && symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, cardValue);
    }

    @Override
    public String toString() {
        return cardValue + symbol.getName();
    }

    @Override
    public int compareTo(Card o) {
        return this.cardValue.getCardValue() - o.cardValue.getCardValue();
    }
}
