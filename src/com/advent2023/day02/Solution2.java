package com.advent2023.day02;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.util.List;

class Solution2 {

	private int maxRed = 0;
	private int maxGreen = 0;
	private int maxBlue = 0;

	public static void main(String[] args) {
		Solution2 day2 = new Solution2();
		day2.calculateResult();
	}

	private void calculateResult() {
		List<String> input = textFileToList("d02.txt");
		int total = 0;

		for (final String s : input) {
			String[] gameSplit = s.split(";");

			for (int i = 1; i < gameSplit.length; i++) {
				int[] r = calculatePower(gameSplit[i]);
				maxRed = r[0];
				maxGreen = r[1];
				maxBlue = r[2];
			}
			total += (maxRed * maxGreen * maxBlue);
			maxRed = 0;
			maxGreen = 0;
			maxBlue = 0;
		}
		System.out.println("Solution day2, part2: " + total);
	}

	private int[] calculatePower(final String s) {
		String[] setSplit = s.split(",");
		int[] result = { maxRed, maxGreen, maxBlue };
		for (final String t : setSplit) {
			String[] dice = t.trim().split(" ");

			if (dice[1].contains("red")) {
				if (Integer.parseInt(dice[0]) > maxRed) {
					result[0] = Integer.parseInt(dice[0]);
					maxRed = Integer.parseInt(dice[0]);
				}
				maxRed = Math.max(Integer.parseInt(dice[0]), maxRed);
			} else if (dice[1].contains("green")) {
				if (Integer.parseInt(dice[0]) > maxGreen) {
					result[1] = Integer.parseInt(dice[0]);
					maxGreen = Integer.parseInt(dice[0]);

				}
			} else if (dice[1].contains("blue")) {
				if (Integer.parseInt(dice[0]) > maxBlue) {
					result[2] = Integer.parseInt(dice[0]);
					maxBlue = Integer.parseInt(dice[0]);
				}
			} else {
				System.out.println("color of die not found");
			}

		}
		return result;
	}
}