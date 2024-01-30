package blackjack.domain;

public class Dealer extends Player{

    private static final int HIT_LIMIT = 16;

    private int win;
    private int lose;
    private int draw;

    public Dealer(String name, int money) {
        super(name, money);
        win = 0;
        lose = 0;
    }

    public void increaseWin() {
        win++;
    }

    public void increaseLose() {
        lose++;
    }


    public void increaseDraw() {
        draw++;
    }

    @Override
    public void moneyGet(Player player) {
        money += player.money;
    }

    @Override
    public void moneyLose(Player player) {
        money -= player.money;
    }

    public String firstCardInfo() {
        return "딜러: "+cards.get(0).toString();
    }

    public boolean scoreIsLowerThenHitLimit() {
        return super.score <= HIT_LIMIT;
    }
}
