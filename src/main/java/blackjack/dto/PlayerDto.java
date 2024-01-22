package blackjack.dto;

import blackjack.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public PlayerDto(int score, String name, List<Card> cards) {
        this.score = score;
        this.cards = cards;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append("카드: ");
        for (Card card : cards) {
            result.append(card.toString()).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }

    public String getName() {
        return name;
    }

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
}
