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
        dealer = new Dealer("딜러");
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

    public Player findPlayer(Player playerTarget) {
        Optional<Player> player = players.stream()
                .filter(cmp -> cmp.equals(playerTarget))
                .findAny();
        return player.orElse(null);
    }

    public void repeatGame(Player playerTarget) {
        for (Player player : players) {
            cardGiveToPlayer(player, playerTarget);
        }
    }

    private void cardGiveToPlayer(Player player, Player playerTarget) {
        if(player.nameEqual(playerTarget)) {
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
        Result result = new Result(0, 0);
        for (Player player : players) {
            checkPlayerCards(player);
            decideTheGame(player, result);
        }
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
             result.increaseWin();
             result.addPlayer(player,"패");
             return;
         }
         if(dealerLose(player)) {
             result.increaseLose();
             result.addPlayer(player, "승");
         }
         if(dealerDraw(player)) {
             result.addPlayer(player, "무");
         }
    }

    public boolean dealerDraw(Player player) {
        return Player.isScoreEqual(player, dealer);
    }

    private boolean dealerWin(Player player) {
        if(Player.isScoreBiggerThenBlackJack(player)
                && (Player.isScoreLowerThenBlackJack(dealer) || Player.isScoreEqualBlackJack(dealer))) {
            return true;
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
