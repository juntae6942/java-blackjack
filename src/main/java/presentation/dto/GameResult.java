package presentation.dto;

import domain.vo.ProfitResult;
import java.util.LinkedHashMap;
import java.util.Map;

public record GameResult(
        Map<String, Integer> memberAmount
) {
    public static GameResult from(ProfitResult profitResult) {
        Map<String, Integer> results = new LinkedHashMap<>();
        results.put("딜러", profitResult.getDealerAmount());
        profitResult.getPlayersAmount().forEach((member, amount) ->
                results.put(member.getName(), amount));
        return new GameResult(results);
    }
}
