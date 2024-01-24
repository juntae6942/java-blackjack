package blackjack.dto;

import blackjack.domain.Card;

import java.util.List;

public class DealerDto extends PlayerDto {

    public DealerDto(int score, List<Card> cards) {
        super(score, "딜러", cards);
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
