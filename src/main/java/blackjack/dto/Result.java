package blackjack.dto;

import blackjack.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private final List<String> results;

    private String result;

    public Result() {
        results = new ArrayList<>();
        this.result = "";
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void addPlayer(Player player) {
        results.add(player.gameResult());
    }

    @Override
    public String toString() {
        StringBuilder winLoseResult = new StringBuilder(result+"\n");
        for (String result : results) {
            winLoseResult.append(result)
                    .append("\n");
        }
        return winLoseResult.toString();
    }
}
