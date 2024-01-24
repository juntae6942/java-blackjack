package blackjack.domain;

public class Dealer extends Player{

    private static final int HIT_LIMIT = 16;

    public Dealer(String name) {
        super(name);
    }

    public String firstCardInfo() {
        return "딜러: "+cards.get(0).toString();
    }

    public boolean scoreIsLowerThenHitLimit() {
        return super.score <= HIT_LIMIT;
    }
}
