package blackjack.service;

import blackjack.domain.Player;
import blackjack.dto.Result;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.assertj.core.api.Assertions.*;

class PlayGameServiceTest {

    private PlayGameService playGameService;

    @Test
    void gameStart() {
        //given
        List<Player> players = new ArrayList<>();
        players.add(new Player("pobi"));
        players.add(new Player("crong"));
        players.add(new Player("lusy"));
        playGameService = new PlayGameService(players);
        //when
        playGameService.gameStart();
        List<String> playerDtos = playGameService.playerState();

        String playerDto1 = playerDtos.get(0);
        StringTokenizer stringTokenizer = new StringTokenizer(playerDto1,",");
        //then
        assertThat(stringTokenizer.countTokens()).isEqualTo(2);
    }

    @Test
    void repeatGame() {
        // given
        List<Player> players = new ArrayList<>();
        players.add(new Player("pobi"));
        players.add(new Player("crong"));
        players.add(new Player("lusy"));
        playGameService = new PlayGameService(players);
        playGameService.gameStart();
        // when
        playGameService.repeatGame("pobi");
        List<String> playerState = playGameService.playerState();
        String pobi = playerState.get(0);
        StringTokenizer stringTokenizer = new StringTokenizer(pobi,",");
        // then
        assertThat(stringTokenizer.countTokens()).isEqualTo(3);
    }

    @Test
    void gameResult() {
        // given
        List<Player> players = new ArrayList<>();
        players.add(new Player("pobi"));
        playGameService = new PlayGameService(players);
        playGameService.gameStart();
        // when
        Result result = playGameService.gameResult();
        // then
        if(result.getWin() >= 1) {
            assertThat(result.toString())
                    .isEqualToIgnoringWhitespace("딜러: 1승 0패\npobi: 패");
        } if(result.getWin() < 1) {
            assertThat(result.toString())
                    .isEqualToIgnoringWhitespace("딜러: 0승 1패\npobi: 승");
        }
    }
}