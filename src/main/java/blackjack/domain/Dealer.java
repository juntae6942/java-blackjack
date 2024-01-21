package blackjack.domain;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player{

    private static final int HIT_LIMIT = 16;
    private int score;

    public Dealer(String name) {
        super(name);
        score = 0;
    }

    public void addCard(Card card) {
        super.addCard(card);
        score+=card.returnCardDto().getCardNumber();
    }

    public DealerDto returnDealerDto() {
        return new DealerDto(score, super.cards);
    }

    public boolean scoreIsLowerThenHitLimit() {
        return score<=HIT_LIMIT;
    }
}
