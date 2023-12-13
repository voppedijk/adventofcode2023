package com.advent2023.day03;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {

	List<String> input = textFileToList("d03.txt");

	public static void main(String[] args) {
		Solution2 aoc3 = new Solution2();
		Map<Integer, List<Integer>> allStars = aoc3.collectAllStars(aoc3.input);
		System.out.println("Solution day3, part2: " + aoc3.checkForGearsAndCalculate(allStars));
	}

	List<Integer> findPositionOfStar(final String line) {
		List<Integer> result = new ArrayList<>();

		CharacterIterator it = new StringCharacterIterator(line);
		int i = 0;
		while (it.current() != CharacterIterator.DONE) {
			if (String.valueOf(it.current()).equals("*")) {
				result.add(i);
			}

			i++;
			it.next();
		}

		return result;
	}

	Map<Integer, List<Integer>> collectAllStars(final List<String> input) {
		Map<Integer, List<Integer>> result = new HashMap<>();

		for (int i = 0; i < input.size(); i++) {
			result.put(i, findPositionOfStar(input.get(i)));
		}

		return result;
	}

	int checkForGearsAndCalculate(final Map<Integer, List<Integer>> positionOfStars) {
		List<Integer> gearValues = new ArrayList<>();

		for (Map.Entry<Integer, List<Integer>> entry : positionOfStars.entrySet()) {
			int lineNumberOfCurrentStar = entry.getKey();

			for (final Integer positionOfStar : entry.getValue()) {
				List<Integer> numbersFound = new ArrayList<>();
				checkAndAddLeftAndRight(input.get(lineNumberOfCurrentStar), numbersFound, positionOfStar);

				if (lineNumberOfCurrentStar != 0) {
					checkAndAddUp(input.get(lineNumberOfCurrentStar - 1), numbersFound, positionOfStar);
				}

				if (lineNumberOfCurrentStar != input.size() - 1) {
					checkAndAddDown(input.get(lineNumberOfCurrentStar + 1), numbersFound, positionOfStar);
				}

				if (numbersFound.size() == 2) {
					gearValues.add(numbersFound.get(0) * numbersFound.get(1));
				}
			}
		}

		int sum = gearValues.stream()
				.mapToInt(Integer::intValue)
				.sum();

		return sum;
	}

	private void checkAndAddDown(final String rawInput, final List<Integer> numbersFound, final int positionOfStar) {
			boolean leftIsDigit = "0123456789".contains(String.valueOf(rawInput.charAt(positionOfStar - 1)));
			boolean centerIsDigit = "0123456789".contains(String.valueOf(rawInput.charAt(positionOfStar)));
			boolean rightIsDigit = "0123456789".contains(String.valueOf(rawInput.charAt(positionOfStar + 1)));

			if (leftIsDigit	|| centerIsDigit || rightIsDigit) {
				if (!centerIsDigit
						&& !rightIsDigit) {
					left(rawInput, numbersFound, positionOfStar);
				} else if (!leftIsDigit
						&& !centerIsDigit) {
					right(rawInput, numbersFound, positionOfStar);
				} else if (leftIsDigit
						&& centerIsDigit
						&& !rightIsDigit) {
					left(rawInput, numbersFound, positionOfStar + 1);
				} else if (!leftIsDigit
						&& centerIsDigit
						&& rightIsDigit) {
					right(rawInput, numbersFound, positionOfStar - 1);
				} else if (leftIsDigit
						&& centerIsDigit
						&& rightIsDigit) {
					right(rawInput, numbersFound, positionOfStar - 2);
				} else if (leftIsDigit
						&& !centerIsDigit
						&& rightIsDigit) {
					left(rawInput, numbersFound, positionOfStar);
					right(rawInput, numbersFound, positionOfStar);
				}
			}
	}

	private void checkAndAddUp(final String rawInput, final List<Integer> numbersFound, final int positionOfStar) {
			boolean upperLeftIsDigit = "0123456789".contains(String.valueOf(rawInput.charAt(positionOfStar - 1)));
			boolean upperCenterIsDigit = "0123456789".contains(String.valueOf(rawInput.charAt(positionOfStar)));
			boolean upperRightIsDigit = "0123456789".contains(String.valueOf(rawInput.charAt(positionOfStar + 1)));

			if (upperLeftIsDigit
					|| upperCenterIsDigit
					|| upperRightIsDigit) {
				if (!upperCenterIsDigit
						&& !upperRightIsDigit) {
					left(rawInput, numbersFound, positionOfStar);

					//1 number only in upper right corner
				} else if (!upperLeftIsDigit
						&& !upperCenterIsDigit) {
					right(rawInput, numbersFound, positionOfStar);

					//1 number in upper left and center
				} else if (upperLeftIsDigit
						&& upperCenterIsDigit
						&& !upperRightIsDigit) {
					left(rawInput, numbersFound, positionOfStar + 1);

					//1 number in upper right and center
				} else if (!upperLeftIsDigit
						&& upperCenterIsDigit
						&& upperRightIsDigit) {
					right(rawInput, numbersFound, positionOfStar - 1);

					//1 number in upper left, center, and right
				} else if (upperLeftIsDigit
						&& upperCenterIsDigit
						&& upperRightIsDigit) {
					right(rawInput, numbersFound, positionOfStar - 2);
					//2 numbers, 1 in both corners
				} else if (upperLeftIsDigit
						&& !upperCenterIsDigit
						&& upperRightIsDigit) {
					left(rawInput, numbersFound, positionOfStar);
					right(rawInput, numbersFound, positionOfStar);
				}
		}
	}

	private void checkAndAddLeftAndRight(final String rawInput, final List<Integer> numbersFound, final int positionOfStar) {
			left(rawInput, numbersFound, positionOfStar);
			right(rawInput, numbersFound, positionOfStar);
	}

	private void right(final String rawInput, final List<Integer> numbersFound, final Integer positionOfStar) {
		int iRight = positionOfStar + 1;
		StringBuilder sbRight = new StringBuilder();

		while (positionOfStar < rawInput.length() - 1 && iRight < rawInput.length()
				&& "0123456789".contains(String.valueOf(rawInput.charAt(iRight)))) {
			sbRight.append(rawInput.charAt(iRight));
			if (iRight < rawInput.length()) {
				iRight++;
			} else {
				break;
			}
		}

		if (sbRight.length() > 0) {
			numbersFound.add(Integer.valueOf(sbRight.toString()));
		}
	}

	private void left(final String rawInput, final List<Integer> numbersFound, final Integer positionOfStar) {
		int iLeft = positionOfStar - 1;
		StringBuilder sbLeft = new StringBuilder();

		while (positionOfStar != 0 && iLeft > -1
				&& "0123456789".contains(String.valueOf(rawInput.charAt(iLeft)))) {
			sbLeft.insert(0, rawInput.charAt(iLeft));
			if (iLeft != 0) {
				iLeft--;
			} else {
				break;
			}
		}

		if (sbLeft.length() > 0) {
			numbersFound.add(Integer.valueOf(sbLeft.toString()));
		}
	}
}