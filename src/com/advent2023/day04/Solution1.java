package com.advent2023.day04;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.util.List;

class Solution1 {

	private int totalPoints;

	public static void main(String[] args) {
		Solution1 s1 = new Solution1();
		s1.start();
	}

	private void start() {
		List<String> input = textFileToList("d04.txt");
		for (final String s : input) {
			String[] splittedString = s.split(":");
			String[] cards = splittedString[1].split("\\|");
			String[] myNumbers = cards[0].trim().split("\\s+");
			String[] winningNumbers = cards[1].trim().split("\\s+");

			int amountOfWinningNumbers = 0;
			for (final String myNumber : myNumbers) {
				for (final String winningNumber : winningNumbers) {
					int my = Integer.parseInt(myNumber);
					int check = Integer.parseInt(winningNumber);
					if (my == check) {
						if (amountOfWinningNumbers == 0) {
							amountOfWinningNumbers = 1;
						} else {
							amountOfWinningNumbers = amountOfWinningNumbers * 2;
						}
					}
				}

			}
			totalPoints += amountOfWinningNumbers;
		}
		System.out.println(totalPoints);
	}

}