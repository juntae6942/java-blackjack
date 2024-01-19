package blackjack.domain;

import blackjack.dto.PlayerDto;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int CHANGE_LIMIT = 11;
    private final String name;
    private final List<Card> cards;
    private int score;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        score = 0;
    }

    public PlayerDto returnPlayerDto() {
        return new PlayerDto(score, name, cards);
    }

    public void addCard(Card card) {
        cards.add(card);
        score += card.returnCardDto().getNum();
    }

    public boolean nameEqual(PlayerDto playerDto) {
        String playerName = playerDto.getName();
        return name.equals(playerName);
    }

    public boolean containsAce() {
        return cards.contains(new Card(CardSymbol.SPADE,1));
    }

    public boolean aceIsCanChange() {
        return score<=CHANGE_LIMIT;
    }

    public void changeAceCard() {
        for (Card card : cards) {
            checkAndChangeCard(card);
        }
    }

    private void checkAndChangeCard(Card card) {
        if(card.isAce()) {
            card.changeAceNumber();
            score+=10;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name+": ");
        for (Card card : cards) {
            result.append(card.toString()).append(",");
        }
        return result.substring(0, result.length() - 1);
    }
}
