package blackjack.view;

import blackjack.domain.Player;
import blackjack.dto.PlayerDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InputView {

    private static final int NAME_LEN = 5;
    private static final int MAX_PLAYER_NUMBER = 7;
    private static final int MIN_PLAYER_NUMBER = 1;

    private static BufferedReader bufferedReader;
    private static StringTokenizer stringTokenizer;

    public List<String> getInputPlayer() throws IOException {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> playerNames;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), ",");

        while ((playerNames = makeNames(stringTokenizer)) == null) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), ",");
        }
        return playerNames;
    }

    public List<String> makeNames(StringTokenizer stringTokenizer) {
        List<String> names = new ArrayList<>();
        int count = stringTokenizer.countTokens();

        if (count < MIN_PLAYER_NUMBER || count > MAX_PLAYER_NUMBER) {
            System.out.println("인원이 초과했거나 부족합니다.");
            return null;
        }   // player 의 수를 제한

        while (stringTokenizer.hasMoreTokens()) {
            String name = stringTokenizer.nextToken().strip();
            if (name.length() > NAME_LEN) {
                System.out.println("Player의 이름은 5자 이하입니다.");
                return null;
            }
            names.add(name);
        }
        return names;
    }

    public void printStart(List<String> playerNames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ");
        for (String playerName : playerNames) {
            stringBuilder.append(playerName)
                    .append(", ");
        }
        String result = stringBuilder.substring(0, stringBuilder.length() - 2);
        result += "에게 2장을 나누었습니다.";

        System.out.println(result);
    }

    public boolean askReceiveCard(String playerName) throws IOException {
        System.out.println(playerName+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return bufferedReader.readLine().equals("y");
    }
}
