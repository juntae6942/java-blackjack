package blackjack.dto;

import blackjack.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private final List<String> results;

    private int win;
    private int lose;

    public Result(int win, int lose) {
        this.win = win;
        this.lose = lose;
        results = new ArrayList<>();
    }

    public void increaseWin() {
        win++;
    }

    public void increaseLose() {
        lose++;
    }

    public void addPlayer(Player player, String result) {
        results.add(player.gameResult() + result);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("딜러: " + win + "승 " + lose + "패\n");
        for (String s : results) {
            result.append(s)
                    .append("\n");
        }
        return result.toString();
    }

    public int getWin() {
        return win;
    }
}
