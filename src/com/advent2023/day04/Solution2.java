package com.advent2023.day04;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {

	public List<String> input = textFileToList("d04.txt");

	public static void main(String[] args) {
		Solution2 aoc4 = new Solution2();
		Map<Integer, Cards> inputMap = aoc4.processInput(aoc4.input);

		Map<Integer, Integer> winsPerGame = new HashMap<>();
		int i = 1;
		for (Map.Entry<Integer, Cards> entry : inputMap.entrySet()) {
			int wins = aoc4.calculateMatches(entry.getValue().getMyCards(), entry.getValue().getWinningNumbers());
			winsPerGame.put(i, wins);
			i++;
		}

		System.out.println("Solution aoc4: " + aoc4.calculateTotalAmountOfCards(winsPerGame, inputMap)); //6227972

	}

	long calculateTotalAmountOfCards(final Map<Integer, Integer> winsPerGame, final Map<Integer, Cards> inputMap) {
		for (int i = 1; i <= input.size(); i++) {
			int winsCurrentGame = winsPerGame.get(i);
			int amountOfCardsCurrentGame = inputMap.get(i).getCopiesOfCard();

			for (int j = 1; j <= winsCurrentGame; j++) {
				if (!inputMap.containsKey(i + 1)) {
					break;
				}

				if (inputMap.containsKey(i + j)) {
					Cards d = inputMap.get(j + i);
					d.setCopiesOfCard((d.getCopiesOfCard() + amountOfCardsCurrentGame));
					inputMap.put(i + j, d);
				}
			}
		}

		long result = 0;
		for (Map.Entry<Integer, Cards> entry : inputMap.entrySet()) {
			result += entry.getValue().getCopiesOfCard();
		}

		return result;
	}

	Map<Integer, Cards> processInput(final List<String> inputLst) {
		Map<Integer, Cards> result = new HashMap<>();

		for (int i = 0; i < inputLst.size(); i++) {
			result.put(i + 1, new Cards(inputLst.get(i)));
		}

		return result;
	}

	int calculateMatches(final List<Integer> cardsLst, final List<Integer> winningNumbersLst) {
		int matches = 0;

		for (final Integer myCard : cardsLst) {
			for (final Integer winningNr : winningNumbersLst) {
				if (myCard.equals(winningNr)) {
					matches++;
				}
			}
		}

		return matches;
	}
}