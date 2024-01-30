package blackjack.validate;

public class OutOfBoundPlayer {
    private static final int MAX_PLAYER_NUMBER = 7;
    private static final int MIN_PLAYER_NUMBER = 1;
    public static void valid(int count){
        if (count < MIN_PLAYER_NUMBER || count > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("인원이 초과했거나 부족합니다.");
        }
    }
}
