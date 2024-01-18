package blackjack.dto;

import blackjack.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class DealerDto {
    int score;
    List<Card> cards;

    public int getScore() {
        return score;
    }

    public List<CardDto> getCards() {
        List<CardDto> cardsDto = new ArrayList<>();
        for (Card card : cards) {
            cardsDto.add(card.returnCardDto());
        }
        return cardsDto;
    }

    public DealerDto(int score, List<Card> cards) {
        this.cards = cards;
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("딜러 ").append("카드: ");
        for (Card card : cards) {
            result.append(card.toString()).append(", ");
        }
        return result.substring(0,result.length() - 2);
    }
}
