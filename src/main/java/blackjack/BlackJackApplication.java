package blackjack;

import blackjack.controller.BlackJackController;

import java.io.IOException;

public class BlackJackApplication {
    public static void main(String[] args) throws IOException {
        BlackJackController blackJackController = new BlackJackController();
        blackJackController.playGame();
    }
}
