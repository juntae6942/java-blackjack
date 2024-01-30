package blackjack.controller;

import blackjack.domain.Player;
import blackjack.dto.Result;
import blackjack.service.PlayGameService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    private PlayGameService playGameService;

    public BlackJackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void playGame() throws IOException {
        List<String> players = playerInput();
        playGameService.gameStart();

        String dealerState = playGameService.getDealerState();
        List<String> playerStates = playGameService.playerState();
        printPlayerState(dealerState, playerStates);

        playGameService.checkBlackJack(players);

        for (String player : players) {
            repeatGame(player);
        }
        checkDealerHit();
        printResult();
    }

    public void repeatGame(String playerName) throws IOException {
        while (inputView.askReceiveCard(playerName)) {
            playGameService.repeatGame(playerName);
            Player player = playGameService.findPlayer(playerName);
            outputView.printState(player.toString());
        }
    }

    public void checkDealerHit() {
        if(playGameService.checkDealerCards()) {
            playGameService.dealerReceiveCard();
        }
    }

    public void printResult() {
        Result result = playGameService.gameResult();
        String dealerResult = playGameService.getDealerResult();
        List<String> playerResults = playGameService.playerResults();
        outputView.printGameResult(dealerResult, playerResults, result);
    }

    public void printPlayerState(String dealer, List<String> players) {
        outputView.printState(dealer);
        outputView.printState(players);
    }

    public List<String> playerInput() throws IOException {
        List<String> inputPlayer = inputView.getInputPlayer();
        List<Integer> inputMoney = inputView.getInputMoney(inputPlayer);
        List<Player> players = new ArrayList<>();
        for (int i=0; i<inputPlayer.size(); i++) {
            String name = inputPlayer.get(i);
            Integer money = inputMoney.get(i);
            players.add(new Player(name, money));
        }
        playGameService = new PlayGameService(players);
        inputView.printStart(inputPlayer);
        return inputPlayer;
    }
}
