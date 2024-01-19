package blackjack.dto;

import blackjack.domain.CardSymbol;

public class CardDto {

    int num;
    CardSymbol symbol;

    public CardDto(int num, CardSymbol cardSymbol) {
        this.num = num;
        this.symbol = cardSymbol;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return num + symbol.getName();
    }

    public CardSymbol getSymbol() {
        return symbol;
    }
}
