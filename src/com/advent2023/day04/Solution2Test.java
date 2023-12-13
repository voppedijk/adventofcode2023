package com.advent2023.day04;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Solution2Test {

	private Solution2 actual = new Solution2();
	private String input1 = "Card   1: 98 16 95 90 53 33 43  7 46 45 | 85 15 78 57 34 10 46 90 33 13  8 54  4 37 25 63 55 41  7 82 69 16 30 76  2";
	private String input2 = "Card   2: 55 34 32 64 52 76 54  8 36 94 | 94 95 76 66 38 26 80 54 32 91 19 64 21 55 36 96 52 82 15 56 70 89 46 71 74";
	private List<Integer> cardsLst = List.of(98, 16, 95, 90, 53, 33, 43, 7, 46, 45);
	private List<Integer> winningNumbersLst = List.of(85, 15, 78, 57, 34, 10, 46, 90, 33, 13, 8, 54, 4, 37, 25, 63, 55, 41, 7, 82, 69, 16, 30, 76, 2);

	@Test
	public void canProcessInputToMap() {
		Cards actualCards = new Cards(input1);
		assertEquals(cardsLst, actualCards.getMyCards());
		assertEquals(winningNumbersLst, actualCards.getWinningNumbers());
	}

	@Test
	public void canProcessFullInput() {
		Map<Integer, Cards> expected = new HashMap<>();
		Cards c1 = new Cards(input1);
		Cards c2 = new Cards(input2);
		expected.put(1, c1);
		expected.put(2, c2);

		List<String> inputLst = new ArrayList<>();
		inputLst.add(input1);
		inputLst.add(input2);

		assertEquals(expected, actual.processInput(inputLst));
	}

	@Test
	public void canCalculateMatches() {
		assertEquals(5, actual.calculateMatches(cardsLst, winningNumbersLst));
	}

	@Test
	public void canCalculateTotalAmountOfCards() {
		Map<Integer, Cards> inputMap = new HashMap<>();
		Cards c1 = new Cards(input1);
		Cards c2 = new Cards(input2);
		Cards c3 = new Cards("Card   3: 35 26 78 89 82 92 37 10  3 43 | 41 81 62 52 92 63 26 57 28 55 93 30 72 71 99 84 96 60 82 78 73 65 43 50 25");
		Cards c4 = new Cards("Card   4: 71 27 75 73 79 83 44 55 31 49 | 74 79 17 38 28 41 88 25 61 55 12 40 43 44  6 73 71 83 49 75 67 80 27 10 31");
		Cards c5 = new Cards("Card   5: 26 10 58 57 85 65 42 23 97 30 | 65 26 85 97 31 10 23 88 58 16 80 22 67 44  7 77 30 69 34 42 24 57 66 27 99");
		Cards c6 = new Cards("Card   6: 56 24 39 96 36 46 48 94 30 58 | 16 24 99 48 73 30 91  7 87 57 23 49 69 39 94 63 17 58 56  9 34 36 31 46 96");
		inputMap.put(1, c1);
		inputMap.put(2, c2);
		inputMap.put(3, c3);
		inputMap.put(4, c4);
		inputMap.put(5, c5);
		inputMap.put(6, c6);

		Map<Integer, Integer> winsMap = new HashMap<>();
		winsMap.put(1,5);
		winsMap.put(2, 8);
		winsMap.put(3,5);
		winsMap.put(4, 10);
		winsMap.put(5,10);
		winsMap.put(6, 10);

		actual.input = List.of("", "", "", "", "", "");

		assertEquals(63, actual.calculateTotalAmountOfCards(winsMap, inputMap));
	}
}