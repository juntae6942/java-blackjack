package blackjack.service;

import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayGameServiceTest {

    private PlayGameService playGameService;

    @Test
    void gameStart() {
        //given
        playGameService = new PlayGameService(Arrays.asList("pobi","crong","lusy"));
        //when
        playGameService.gameStart();
        List<PlayerDto> playerDtos = playGameService.returnPlayerState();

        PlayerDto playerDto1 = playerDtos.get(0);
        PlayerDto playerDto2 = playerDtos.get(1);
        PlayerDto playerDto3 = playerDtos.get(2);
        //then
        assertThat(playerDto1.getCards().size()).isEqualTo(2);
        assertThat(playerDto2.getCards().size()).isEqualTo(2);
        assertThat(playerDto3.getCards().size()).isEqualTo(2);
    }

    @Test
    void repeatGame() {
        // given
        playGameService = new PlayGameService(Arrays.asList("pobi","mac","ahpoo"));
        playGameService.gameStart();
        List<PlayerDto> playerDtos = playGameService.returnPlayerState();
        PlayerDto playerDto = playerDtos.get(0);
        // when
        playGameService.repeatGame(playerDto);
        playerDtos = playGameService.returnPlayerState();
        PlayerDto pobi = playerDtos.get(0);
        // then
        assertThat(pobi.getCards().size()).isEqualTo(3);
    }

    @Test
    void gameResult() {
        // given
        playGameService = new PlayGameService(List.of("pobi"));
        playGameService.gameStart();
        // when
        String result = playGameService.gameResult();
        DealerDto dealer = playGameService.returnDealerState();
        PlayerDto pobi = playGameService.returnPlayerState().get(0);
        // then
        if(dealer.getScore() > pobi.getScore()) {
            assertThat(result).isEqualToIgnoringWhitespace("딜러: 1승 0패\npobi: 패");
        }
        if(dealer.getScore() < pobi.getScore()) {
            assertThat(result).isEqualToIgnoringWhitespace("딜러: 0승 1패\npobi: 승");
        }
    }
}