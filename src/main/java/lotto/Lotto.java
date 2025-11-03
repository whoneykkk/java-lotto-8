package lotto;

import java.util.List;
import java.util.Collections;
import java.util.HashSet;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }

        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        List<Integer> sortedNumbers = numbers.stream().sorted().toList();
        return sortedNumbers.toString();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Rank checkRank(List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = countMatches(winningNumbers);
        boolean bonusMatch = numbers.contains(bonusNumber);
        return Rank.valueOf(matchCount, bonusMatch);
    }

    private int countMatches(List<Integer> winningNumbers) {
        int count = 0;
        for (int number : numbers) {
            if (winningNumbers.contains(number)) {
                count++;
            }
        }
        return count;
    }
}