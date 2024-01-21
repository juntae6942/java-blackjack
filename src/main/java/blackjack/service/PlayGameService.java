package blackjack.service;

import blackjack.domain.*;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class PlayGameService {

    private static final int BLACK_JACK = 21;
    List<Card> duplicatedCheckCard;
    List<Player> players;
    Dealer dealer;

    public PlayGameService(List<String> playersName) {
        players = new ArrayList<>();
        for (String name : playersName) {
            players.add(new Player(name));
        }
        dealer = new Dealer("딜러");
        duplicatedCheckCard = new ArrayList<>();
    }

    public void gameStart() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ");
        for (Player player : players) {
            stringBuilder.append(player.returnPlayerDto().getName())
                    .append(", ");
        }

        String result = stringBuilder.substring(0, stringBuilder.length() - 2);
        result += "에게 2장을 나누었습니다.";
        firstReceiveCard(dealer);
        for (Player player : players) {
            firstReceiveCard(player);
        }
        System.out.println(result);
    }

    private void firstReceiveCard(Player player) {
        player.addCard(generateCard());
        player.addCard(generateCard());
    }

    public void repeatGame(PlayerDto playerDto) {
        for (Player player : players) {
            cardGiveToPlayer(player, playerDto);
        }
    }

    private void cardGiveToPlayer(Player player, PlayerDto playerDto) {
        if(player.nameEqual(playerDto)) {
            receiveCard(player);
        }
    }

    private void receiveCard(Player player) {
        player.addCard(generateCard());
    }

    public void dealerReceiveCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        dealer.addCard(generateCard());
    }

    public boolean checkDealerCards() {
        if(dealer.spadeAceContains() && dealer.aceIsCanChange()) {
            dealer.changeSpadeAceCard();
        }
        return dealer.scoreIsLowerThenHitLimit();
    }

    private Card generateCard() {
        CardSymbol cardSymbol = CardSymbol.randomSymbol();
        int value = CardValue.randomValue();
        Card card = new Card(cardSymbol, value);

        if (duplicatedCard(card)) {
            duplicatedCheckCard.add(card);
            return card;
        }
        return generateCard();
    }

    private boolean duplicatedCard(Card card) {
        return !duplicatedCheckCard.contains(card);
    }

    public DealerDto dealerState() {
        return dealer.returnDealerDto();
    }

    public List<PlayerDto> playerState() {
        List<PlayerDto> playersDto = new ArrayList<>();
        for (Player player : players) {
            playersDto.add(player.returnPlayerDto());
        }
        return playersDto;
    }

    public String gameResult() {
        int dealerScore = dealer.returnDealerDto().getScore();
        int win = 0;
        int lose = 0;
        Result result = new Result(win, lose);
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            checkPlayerCards(player);
            PlayerDto playerDto = player.returnPlayerDto();
            stringBuilder.append(playerDto.getName()).append(": ");
            decideTheGame(playerDto, dealerScore, stringBuilder, result);
        }
        return "딜러: "+ result.win +"승 "+ result.lose +"패\n"+stringBuilder;
    }

    private void decideTheGame(PlayerDto playerDto, int dealerScore ,StringBuilder stringBuilder, Result result) {
        if(playerDto.getScore() > 21 && dealerScore <= 21) {
            result.win++;
            stringBuilder.append("패\n");
            return;
        } if (playerDto.getScore() <= 21 && dealerScore > 21) {
            result.lose++;
            stringBuilder.append("승\n");
            return;
        } if(dealerScore-BLACK_JACK > playerDto.getScore()-BLACK_JACK) {
            result.win++;
            stringBuilder.append("패\n");
            return;
        } if(dealerScore-BLACK_JACK < playerDto.getScore()-BLACK_JACK){
            result.lose++;
            stringBuilder.append("승\n");
            return;
        }
        stringBuilder.append("무\n");
    }

    private static class Result {
        public int win;
        public int lose;

        public Result(int win, int lose) {
            this.win = win;
            this.lose = lose;
        }
    }

    private void checkPlayerCards(Player player) {
        if(player.spadeAceContains() && player.aceIsCanChange()) {
            player.changeSpadeAceCard();
        }
    }
}
