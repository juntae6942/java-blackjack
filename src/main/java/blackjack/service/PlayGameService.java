package blackjack.service;

import blackjack.domain.*;
import blackjack.dto.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class PlayGameService {

    private final Set<Card> duplicatedCheckCard;
    private final List<Player> players;
    private final Dealer dealer;

    public PlayGameService(List<Player> players) {
        this.players = players;
        dealer = new Dealer("딜러", 0);
        duplicatedCheckCard = new ConcurrentSkipListSet<>();
    }

    public void gameStart() {
        firstReceiveCard(dealer);
        for (Player player : players) {
            firstReceiveCard(player);
        }
    }

    private void firstReceiveCard(Player player) {
        player.addCard(generateCard());
        player.addCard(generateCard());
    }

    private Card generateCard() {
        Card card;
        do {
            card = CardGenerator.generateCard();
        } while (duplicatedCard(card));
        duplicatedCheckCard.add(card);
        return card;
    }

    private boolean duplicatedCard(Card card) {
        return duplicatedCheckCard.contains(card);
    }

    public void checkBlackJack(List<String> players) {
        for (String playerName : players) {
            Player player = findPlayer(playerName);
            calculateBetting(player);
        }
    }

    private void calculateBetting(Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            player.bonusMoney(player);
            dealer.moneyLose(player);
        }
    }

    public Player findPlayer(String name) {
        Optional<Player> player = players.stream()
                .filter(cmp -> cmp.nameEqual(name))
                .findAny();
        return player.orElse(null);
    }

    public void repeatGame(String name) {
        for (Player player : players) {
            cardGiveToPlayer(player, name);
        }
    }

    private void cardGiveToPlayer(Player player, String name) {
        if(player.nameEqual(name)) {
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
        if(dealer.contains(new Card(CardSymbol.SPADE, new CardValue(1))) && dealer.aceIsCanChange()) {
            dealer.changeSpadeAceCard();
        }
        return dealer.scoreIsLowerThenHitLimit();
    }

    public String getDealerState() {
        return dealer.firstCardInfo();
    }

    public String getDealerResult() {
        return dealer + dealer.gameScore();
    }

    public List<String> playerState() {
        List<String> playerStates = new ArrayList<>();
        for (Player player : players) {
            playerStates.add(player.toString());
        }
        return playerStates;
    }

    public List<String> playerResults() {
        List<String> playerResults = new ArrayList<>();
        for (Player player : players) {
            playerResults.add(player.toString() + player.gameScore());
        }
        return playerResults;
    }

    public Result gameResult() {
        Result result = new Result();
        for (Player player : players) {
            checkPlayerCards(player);
            decideTheGame(player, result);
            result.addPlayer(player);
        }
        result.setResult(dealer.gameResult());
        return result;
    }

    private void checkPlayerCards(Player player) {
        if(player.contains(new Card(CardSymbol.SPADE, new CardValue(1)))
                && player.aceIsCanChange()) {
            player.changeSpadeAceCard();
        }
    }

    private void decideTheGame(Player player, Result result) {
        if(dealerWin(player)) {
             dealer.moneyGet(player);
             player.moneyLose(player);
             return;
         }
         if(dealerLose(player)) {
             dealer.moneyLose(player);
             player.moneyGet(player);
         }
    }

    public boolean dealerDraw(Player player) {
        return Player.isScoreEqual(player, dealer);
    }

    private boolean dealerWin(Player player) {
        if((Player.isScoreBiggerThenBlackJack(dealer) && Player.isScoreLowerThenBlackJack(player))) {
            return false;
        }
        return dealer.compareScore(player);
    }

    private boolean dealerLose(Player player) {
        if ((Player.isScoreLowerThenBlackJack(player) || Player.isScoreEqualBlackJack(player))
                && Player.isScoreBiggerThenBlackJack(dealer)) {
            return true;
        }
        return player.compareScore(dealer);
    }
}
