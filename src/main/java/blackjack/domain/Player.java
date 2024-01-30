package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private static final int BLACK_JACK = 21;
    private static final int CHANGE_LIMIT = 11;

    protected final List<Card> cards;
    private final String name;

    protected int score;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        score = 0;
    }

    public static boolean isScoreBiggerThenBlackJack(Player player) {
        return player.score > BLACK_JACK;
    }

    public static boolean isScoreLowerThenBlackJack(Player player) {
        return player.score < BLACK_JACK;
    }

    public static boolean isScoreEqualBlackJack(Player player) {
        return player.score == BLACK_JACK;
    }

    public static boolean isScoreEqual(Player player, Player dealer) {
        return player.score == dealer.score;
    }

    public boolean compareScore(Player player) {
        return (this.score - BLACK_JACK) > (player.score - BLACK_JACK);
    }

    public void addCard(Card card) {
        cards.add(card);
        score += card.cardValue().getCardValue();
    }

    public boolean nameEqual(String name) {
        return this.name.equals(name);
    }

    public boolean contains(Card card) {
        return cards.contains(card);
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
            score+=CHANGE_LIMIT - 1;
        }
    }

    public String gameScore() {
        return " - 결과: "+score;
    }

    public String gameResult() {
        return name+": ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name+": ");
        for (Card card : cards) {
            result.append(card.toString()).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }
}
