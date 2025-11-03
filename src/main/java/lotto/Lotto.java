package lotto;

import java.util.List;
import java.util.Collections;

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