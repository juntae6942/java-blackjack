package domain.member;

import domain.vo.ProfitResult;
import domain.vo.RoundResult;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Settler {

    public ProfitResult getProfits(Dealer dealer, Players players) {
        Map<Player, Integer> playerProfits = getPlayerProfits(dealer, players);
        int dealerProfit = getDealerProfit(playerProfits);
        return new ProfitResult(dealerProfit, playerProfits);
    }

    private Map<Player, Integer> getPlayerProfits(Dealer dealer, Players players) {
        return judgeGameResults(dealer, players).entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getKey()
                                .calculateProfit(entry.getValue()),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private int getDealerProfit(Map<Player, Integer> playerProfits) {
        return playerProfits.values().stream()
                .mapToInt(profit -> -profit)
                .sum();
    }

    private Map<Player, RoundResult> judgeGameResults(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                                player -> player,
                                player -> RoundResult.judgeAgainst(
                                        dealer,
                                        player
                                ),
                                (existing, replacement) -> existing,
                                LinkedHashMap::new
                        )
                );
    }
}
