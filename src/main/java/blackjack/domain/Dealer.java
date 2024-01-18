package blackjack.domain;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int REPEAT_LIMIT = 16;
    private static final int CHANGE_LIMIT = 11;
    private int score = 0;
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
        score+=card.returnCardDto().getNum();
    }
    public List<CardDto> returnCardsDto() {
        List<CardDto> cardsDto = new ArrayList<>();
        for (Card card : cards) {
            cardsDto.add(card.returnCardDto());
        }
        return cardsDto;
    }

    public DealerDto returnDealerDto() {
        return new DealerDto(score, cards);
    }
    public void changeCard() {
        for (Card card : cards) {
            if(card.isAce()) {
                card.changeNum();
                score+=10;
            }
        }
    }

    public boolean scoreIsLowerThen16() {
        return score<=REPEAT_LIMIT;
    }
    public boolean containsAce() {
        return cards.contains(new Card(CardSymbol.SPADE,1));
    }
    public boolean aceIsCanChange() {
        return score<=CHANGE_LIMIT;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("딜러: ");
        for (Card card : cards) {
            result.append(card.toString()).append(",");
        }
        return result.substring(0, result.length() - 1);
    }
}
