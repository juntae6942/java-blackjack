package blackjack.domain;

public class CardValue {

    private int cardValue;

    public CardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return String.valueOf(cardValue);
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public int getCardValue() {
        return cardValue;
    }
}
