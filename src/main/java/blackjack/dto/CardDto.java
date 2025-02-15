package blackjack.dto;

import blackjack.domain.CardSymbol;

public class CardDto {

    private final CardSymbol symbol;
    private final int cardNumber;

    public CardDto(int cardNumber, CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.symbol = cardSymbol;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return cardNumber + symbol.getName();
    }

    public CardSymbol getSymbol() {
        return symbol;
    }
}
