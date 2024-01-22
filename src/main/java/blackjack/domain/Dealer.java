package blackjack.domain;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player{

    private static final int HIT_LIMIT = 16;

    public Dealer(String name) {
        super(name);
    }

    public DealerDto returnDealerDto() {
        return new DealerDto(super.score, super.cards);
    }

    public boolean scoreIsLowerThenHitLimit() {
        return super.score <= HIT_LIMIT;
    }
}
