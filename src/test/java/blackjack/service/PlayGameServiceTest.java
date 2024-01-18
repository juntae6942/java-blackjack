package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardSymbol;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.dto.PlayerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayGameServiceTest {

    @Test
    void gameStart() {
    }

    @Test
    void repeatGame() {
    }

    @Test
    void dealerReceiveCard() {
    }

    @Test
    void receiveCard() {
    }

    @Test
    void generateCard() {
        //given
        //when
        //then
    }

    @Test
    void duplicatedCard() {
    }

    @Test
    void returnDealerState() {
    }

    @Test
    void returnPlayerState() {
    }

    @Test
    void gameResult() {
        //given
        //when
        //then
    }

    @Test
    void checkPlayerCards() {
        //given
        PlayGameService playGameService = new PlayGameService(Arrays.asList("pobi","crong","rupy"));
        Player pobi = playGameService.players.get(0);
        Player crong = playGameService.players.get(1);
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardSymbol.CLOVER,3));
        pobi.addCard(new Card(CardSymbol.SPADE,1));
        crong.addCard(new Card(CardSymbol.HEART,2));
        //when
        playGameService.checkPlayerCards(pobi);
        PlayerDto playerDto = pobi.returnPlayer();
        //then
        Assertions.assertThat(playerDto.getScore()).isEqualTo(11);
    }

    @Test
    void checkDealerCards() {
        Player player = new Player("pobi");
        player.addCard(new Card(CardSymbol.SPADE,1));
        player.changeCard();
        PlayerDto playerDto = player.returnPlayer();
        Assertions.assertThat(playerDto.getScore()).isEqualTo(11);
    }
}