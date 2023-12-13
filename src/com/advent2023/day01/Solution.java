package com.advent2023.day01;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {

	private final List<String> input = textFileToList("d01.txt");

	public static void main(String[] args) {
		Solution day1 = new Solution();
		System.out.println("Solution day 1, part 1: " + day1.getTotal(day1.input));

		List<String> inputProcessed = day1.replaceWrittenNumbersByDigits(day1.input);
		System.out.println("Solution day 1, part 2: " + day1.getTotal(inputProcessed));
	}

	private Integer getTotal(final List<String> inputProcessed) {
		int result = 0;

		for (final String line : inputProcessed) {
			String s = line.replaceAll("[^0-9]+", "");
			String firstNr = s.substring(0, 1);
			String lastNr = s.substring(s.length() - 1);
			Integer firstPlusLastNr = Integer.valueOf(firstNr + lastNr);
			result += firstPlusLastNr;
		}

		return result;
	}

	private List<String> replaceWrittenNumbersByDigits(final List<String> input) {
		List<String> result = new ArrayList<>();
		TreeMap<Integer, String> map = new TreeMap<>();
		for (final String line : input) {
			String s = line.toLowerCase();

			CharacterIterator it = new StringCharacterIterator(s);
			int j = 0;
			while (it.current() != CharacterIterator.DONE) {
				if (s.substring(j).length() >= 5) {
					if (s.substring(j, j+5).contains("three")) {
						map.put(j, "3");
					}
					if (s.substring(j, j+5).contains("seven")) {
						map.put(j, "7");
					}
					if (s.substring(j, j+5).contains("eight")) {
						map.put(j, "8");
					}
				}
				if (s.substring(j).length() >= 4) {
					if (s.substring(j, j+4).contains("four")) {
						map.put(j, "4");
					}
					if (s.substring(j, j+4).contains("five")) {
						map.put(j, "5");
					}
					if (s.substring(j, j+4).contains("nine")) {
						map.put(j, "9");
					}
				}
				if (s.substring(j).length() >= 3) {
					if (s.substring(j, j+3).contains("one")) {
						map.put(j, "1");
					}
					if (s.substring(j, j+3).contains("two")) {
						map.put(j, "2");
					}
					if (s.substring(j, j+3).contains("six")) {
						map.put(j, "6");
					}
				}

				if (it.current() == '1') {
					map.put(j, "1");
				}
				if (it.current() == '2') {
					map.put(j, "2");
				}
				if (it.current() == '3') {
					map.put(j, "3");
				}
				if (it.current() == '4') {
					map.put(j, "4");
				}
				if (it.current() == '5') {
					map.put(j, "5");
				}
				if (it.current() == '6') {
					map.put(j, "6");
				}
				if (it.current() == '7') {
					map.put(j, "7");
				}
				if (it.current() == '8') {
					map.put(j, "8");
				}
				if (it.current() == '9') {
					map.put(j, "9");
				}
				j++;
				it.next();
			}
			Map.Entry<Integer, String> firstNr = map.firstEntry();
			Map.Entry<Integer, String> lastNr = map.lastEntry();
			result.add(firstNr.getValue() + lastNr.getValue());
			map.clear();
		}

		return result;
	}
}