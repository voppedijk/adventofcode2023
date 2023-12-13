package com.advent2023.day06;

import java.util.ArrayList;
import java.util.List;

class Solution {

	public static void main(String[] args) {
		Solution day6 = new Solution();
		List<Long> game1 = day6.calculateWinningOptions(57, 291);
		List<Long> game2 = day6.calculateWinningOptions(72, 1172);
		List<Long> game3 = day6.calculateWinningOptions(69, 1176);
		List<Long> game4 = day6.calculateWinningOptions(92, 2026);

		int result = game1.size() * game2.size() * game3.size()	* game4.size();
		System.out.println("Part 1: " + result);

		List<Long> game = day6.calculateWinningOptions(57726992, 291117211762026L);
		System.out.println("Part 2: " + game.size());
	}

	List<Long> calculateWinningOptions(final long time, final long distance) {
		List<Long> result = new ArrayList<>();

		for (long pressedButtonTime = 1; pressedButtonTime < time; pressedButtonTime++) {
			long timeRemaining = time - pressedButtonTime;
			long coveredDistance = timeRemaining * pressedButtonTime;

			if (coveredDistance > distance) {
				result.add(pressedButtonTime);
			}
		}

		return result;
	}

	long multiplyWinningOptions(long... input) {
		long total = 1;

		for (final long i : input) {
			total *= i;
		}

		return total;
	}
}