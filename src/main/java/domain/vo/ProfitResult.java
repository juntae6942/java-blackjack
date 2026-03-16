package domain.vo;

import domain.member.Player;
import java.util.Collections;
import java.util.Map;

public class ProfitResult {

    private final Map<Player, Integer> playersAmount;
    private final int dealerAmount;

    public ProfitResult(int dealerAmount, Map<Player, Integer> playersAmount) {
        this.dealerAmount = dealerAmount;
        this.playersAmount = playersAmount;
    }

    public int getDealerAmount() {
        return dealerAmount;
    }

    public Map<Player, Integer> getPlayersAmount() {
        return Collections.unmodifiableMap(playersAmount);
    }
}
