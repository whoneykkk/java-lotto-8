package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = parsePurchaseAmount(Console.readLine());

        System.out.println("당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = parseWinningNumbers(Console.readLine());

        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = parseBonusNumber(Console.readLine(), winningNumbers);
    }

    private static int parsePurchaseAmount(String input) {
        int amount = Integer.parseInt(input);
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
        }
        return amount;
    }

    private static List<Integer> parseWinningNumbers(String input) {
        String[] parts = input.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }

        List<Integer> numbers = Arrays.stream(parts)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }

        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }

        return numbers;
    }

    private static int parseBonusNumber(String input, List<Integer> winningNumbers) {
        int bonusNumber = Integer.parseInt(input);
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        return bonusNumber;
    }
}