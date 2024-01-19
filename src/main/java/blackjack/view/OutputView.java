package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;

import java.util.List;

public class OutputView {

    public void printState(List<PlayerDto> playerDtos) {
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(playerDto);
        }
    }

    public void printState(PlayerDto playerDto) {
        System.out.println(playerDto);
    }

    public void printState(DealerDto dealerDto) {
        System.out.println(dealerDto.firstCardInfo());
    }

    public void printGameResult(DealerDto dealerDto, List<PlayerDto> playerDtos, String result) {
        dealerResult(dealerDto);
        for (PlayerDto playerDto : playerDtos) {
            playerResult(playerDto);
        }
        System.out.println("## 최종 승패");
        System.out.println(result);
    }

    public void dealerResult(DealerDto dealerDto) {
        StringBuilder result = new StringBuilder();
        result.append("딜러")
                .append("카드: ");
        result = returnResult(dealerDto.getCards(), result);
        result.append(dealerDto.getScore());
        System.out.println(result);
    }

    public void playerResult(PlayerDto playerDto) {
        StringBuilder result = new StringBuilder();
        result.append(playerDto.getName())
                .append("카드: ");
        result = returnResult(playerDto.getCards(), result);
        result.append(playerDto.getScore());
        System.out.println(result);
    }

    public StringBuilder returnResult(List<CardDto> cards, StringBuilder result) {
        for (CardDto card : cards) {
            result.append(card.toString())
                    .append(", ");
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));
        return result.append(" - 결과: ");
    }
}
