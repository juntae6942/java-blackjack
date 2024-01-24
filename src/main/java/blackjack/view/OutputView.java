package blackjack.view;

import blackjack.dto.Result;
import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import java.util.List;

public class OutputView {

    public void printState(List<String> players) {
        for (String playerDto : players) {
            System.out.println(playerDto);
        }
    }

    public void printState(String playerState) {
        System.out.println(playerState);
    }

    public void printGameResult(String dealer, List<String> players, Result result) {
        System.out.println(dealer);
        for (String playerDto : players) {
            System.out.println(playerDto);
        }
        System.out.println("## 최종 승패");
        System.out.println(result);
    }
}
