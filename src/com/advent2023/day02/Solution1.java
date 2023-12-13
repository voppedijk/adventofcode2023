package com.advent2023.day02;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.util.List;

class Solution1 {

	public static void main(String[] args) {
		Solution1 day2 = new Solution1();
		day2.calculateResult();
	}

	private void calculateResult() {
		List<String> input = textFileToList("d02.txt");

		int totalGameNumbers = 0;

		for (final String s : input) {
			String[] gameSplit = s.split(";");
			String gameNumber = gameSplit[0];
			boolean isValidGame = true;

			for (int i = 1; i < gameSplit.length && isValidGame; i++) {
				String set = gameSplit[i];
				isValidGame = setIsValid(set);
			}

			if (isValidGame) {
				totalGameNumbers += Integer.parseInt(gameNumber);
			}
		}
		System.out.println("Solution day2, part1: " + totalGameNumbers);
	}

	private boolean setIsValid(final String set) {
		String[] setSplit = set.split(",");
		for (final String s : setSplit) {
			String[] dice = s.trim().split(" ");

			if (dice[1].contains("red")) {
				if (Integer.parseInt(dice[0]) > 12) {
					return false;
				}
			} else if (dice[1].contains("green")) {
				if (Integer.parseInt(dice[0]) > 13) {
					return false;
				}
			} else if (dice[1].contains("blue")) {
				if (Integer.parseInt(dice[0]) > 14) {
					return false;
				}
			} else {
				System.out.println("color of die not found");
				return false;
			}
		}
		return true;
	}
}