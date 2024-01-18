package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardSymbol;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
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
        dealer = new Dealer();
        duplicatedCheckCard = new ArrayList<>();
    }

    public void gameStart() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ");
        for (Player player : players) {
            stringBuilder.append(player.returnPlayer().getName())
                    .append(",");
        }
        String result = stringBuilder.substring(0, stringBuilder.length() - 1);
        result += "에게 2장을 나누었습니다.";
        firstReceiveCard(dealer);
        for (Player player : players) {
            firstReceiveCard(player);
        }
    }

    public void repeatGame(PlayerDto playerDto) {
        for (Player player : players) {
            if(player.nameEqual(playerDto)) {
                receiveCard(player);
                return;
            }
        }
    }
    private void firstReceiveCard(Dealer dealer) {
        dealer.addCard(generateCard());
        dealer.addCard(generateCard());
    }

    private void firstReceiveCard(Player player) {
        player.addCard(generateCard());
        player.addCard(generateCard());
    }

    public void dealerReceiveCard() {
        dealer.addCard(generateCard());
    }

    public void receiveCard(Player player) {
        player.addCard(generateCard());
    }

    public Card generateCard() {
        CardSymbol cardSymbol = CardSymbol.randomSymbol();
        int value = CardSymbol.randomValue();
        Card card = new Card(cardSymbol, value);
        if (duplicatedCard(card)) {
            duplicatedCheckCard.add(card);
            return card;
        }
        return generateCard();
    }

    public boolean duplicatedCard(Card card) {
        return !duplicatedCheckCard.contains(card);
    }
    public DealerDto returnDealerState() {
        return dealer.returnDealerDto();
    }
    public List<PlayerDto> returnPlayerState() {
        List<PlayerDto> playersDto = new ArrayList<>();
        for (Player player : players) {
            playersDto.add(player.returnPlayer());
        }
        return playersDto;
    }

    public String gameResult() {
        int dealerScore = dealer.returnDealerDto().getScore();
        int win = 0;
        int lose = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            checkPlayerCards(player);
            PlayerDto playerDto = player.returnPlayer();
            stringBuilder.append(playerDto.getName()).append(": ");
            if(playerDto.getScore() > 21 && dealerScore <= 21) {
                win++;
                stringBuilder.append("패\n");
                continue;
            } else if (playerDto.getScore() <= 21 && dealerScore > 21) {
                lose++;
                stringBuilder.append("승\n");
                continue;
            }
            if((playerDto.getScore() > 21 && dealerScore > 21) || playerDto.getScore() == dealerScore) {
                stringBuilder.append("무\n");
                continue;
            }
            if(dealerScore-BLACK_JACK > playerDto.getScore()-BLACK_JACK) {
                win++;
                stringBuilder.append("패\n");
            } else if(dealerScore-BLACK_JACK < playerDto.getScore()-BLACK_JACK){
                lose++;
                stringBuilder.append("승\n");
            }
        }
        return "딜러: "+win+"승 "+lose+"패\n"+stringBuilder;
    }

    public void checkPlayerCards(Player player) {
        if(player.containsAce() && player.aceIsCanChange()) {
            player.changeCard();
        }
    }
    public boolean checkDealerCards() {
        if(dealer.containsAce() && dealer.aceIsCanChange()) {
            dealer.changeCard();
        }
        return dealer.scoreIsLowerThen16();
    }
    // 딜러의 카드를 확인한다. 합이 16이하면, 한 장 더 받는다. 근데 Ace를 들고 있다면?
    // Ace의 유무를 파악 가지고 있으면 일단 11로 바꾼다. 그 다음 16이하인지 파악
}
