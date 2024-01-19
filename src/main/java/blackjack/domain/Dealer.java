package blackjack.domain;

import blackjack.dto.DealerDto;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player{

    private static final int REPEAT_LIMIT = 16;
    private int score = 0;
    private final List<Card> cards = new ArrayList<>();

    public Dealer(String name) {
        super(name);
    }

    public void addCard(Card card) {
        cards.add(card);
        score+=card.returnCardDto().getNum();
    }

    public DealerDto returnDealerDto() {
        return new DealerDto(score, cards);
    }

    public void changeCard() {
        for (Card card : cards) {
            checkIsAceAndChange(card);
        }
    }

    private void checkIsAceAndChange(Card card) {
        if(card.isAce()) {
            card.changeAceNumber();
            score+=10;
        }
    }

    public boolean scoreIsLowerThen16() {
        return score<=REPEAT_LIMIT;
    }
}
