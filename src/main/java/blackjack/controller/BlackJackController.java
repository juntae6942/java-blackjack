package blackjack.controller;

import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.service.PlayGameService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.io.IOException;
import java.util.List;

public class BlackJackController {
    private InputView inputView;
    private OutputView outputView;
    private PlayGameService playGameService;
    public void playGame() throws IOException {
         inputView = new InputView();
         outputView = new OutputView();

         List<String> inputPlayer = inputView.getInputPlayer();
         playGameService = new PlayGameService(inputPlayer);
         playGameService.gameStart();

         DealerDto dealerDto = playGameService.dealerState();
         List<PlayerDto> playerDtos = playGameService.playerState();
         outputView.printState(dealerDto);
         outputView.printState(playerDtos);

         for (PlayerDto playerDto : playerDtos) {
            repeatGame(playerDto);
         }
         if(playGameService.checkDealerCards()) {
            playGameService.dealerReceiveCard();
         }
         String result = playGameService.gameResult();
         dealerDto = playGameService.dealerState();
         playerDtos = playGameService.playerState();

         outputView.printGameResult(dealerDto, playerDtos, result);
    }

    public void repeatGame(PlayerDto playerDto) throws IOException {
        while (inputView.askReceiveCard(playerDto)) {
            playGameService.repeatGame(playerDto);
            outputView.printState(playerDto);
        };
    }
}
