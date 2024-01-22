package blackjack.domain;

import blackjack.dto.PlayerDto;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int CHANGE_LIMIT = 11;
    // 카드의 합이 11보다 작거나 같으면 Spade Ace를 1 -> 11

    private final String name;
    protected final List<Card> cards;

    private int score;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        score = 0;
    }

    public void addCard(Card card) {
        cards.add(card);
        score += card.returnCardDto().getCardNumber();
    }

    public boolean nameEqual(PlayerDto playerDto) {
        String playerName = playerDto.getName();
        return name.equals(playerName);
    }

    public boolean spadeAceContains() {
        return cards.contains(new Card(CardSymbol.SPADE,1));
    }

    public boolean aceIsCanChange() {
        return score<=CHANGE_LIMIT;
    }

    public void changeSpadeAceCard() {
        for (Card card : cards) {
            checkAndChangeCard(card);
        }
    }

    private void checkAndChangeCard(Card card) {
        if(card.isSpadeAce()) {
            card.changeAceNumber();
            score+=10;
        }
    }   // 스페이드 에이스인지 확인하고, 숫자를 1 -> 11로 변경하면서, 점수차 10점을 더한다.

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name+": ");
        for (Card card : cards) {
            result.append(card.toString()).append(",");
        }
        return result.substring(0, result.length() - 1);
    }

    public PlayerDto playerDto() {
        return new PlayerDto(score, name, cards);
    }
}
