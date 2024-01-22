package blackjack.domain;

import blackjack.dto.CardDto;

import java.util.Objects;

public class Card {

    private static final int ACE = 11;

    private final CardSymbol symbol;

    private int cardNumber;

    public Card(CardSymbol symbol, int cardNumber) {
        this.symbol = symbol;
        this.cardNumber = cardNumber;
    }

    public boolean isSpadeAce() {
        return this.symbol.equals(CardSymbol.SPADE) && this.cardNumber==1;
    }

    public void changeAceNumber() {
        this.cardNumber = ACE;
    }

    public CardDto returnCardDto() {
        return new CardDto(cardNumber, symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber && symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, cardNumber);
    }

    @Override
    public String toString() {
        return cardNumber + symbol.getName();
    }
}
