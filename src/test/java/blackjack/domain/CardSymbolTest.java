package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CardSymbolTest {

    @Test
    void randomSymbol() {
        //given
        CardSymbol[] values = CardSymbol.values();
        //when
        CardSymbol cardSymbol = CardSymbol.randomSymbol();
        //then
        Arrays.asList(values).contains(cardSymbol);
    }

    @Test
    void randomValue() {
        //given
        int num = CardValue.randomValue();
        boolean check = false;
        //when
        if(num <= 10 && num > 0) {
            check = true;
        }
        //then
        assertThat(check).isTrue();
    }
}