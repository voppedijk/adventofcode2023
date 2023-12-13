package com.advent2023.day03;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
	Map<Integer, List<Integer>> positionOfSymbolYxMap = new HashMap<>();

	public static void main(String[] args) {
		Solution1 aoc3 = new Solution1();
		aoc3.calculateResult();
	}

	public void calculateResult() {
		List<String> input = textFileToList("d03.txt");
		List<String> symbolsConverted = new ArrayList<>();
		int total = 0;

		for (final String s : input) {
			String convertAnythingButDigitOrDot = s.replaceAll("[^.0-9]+", "s");
			symbolsConverted.add(convertAnythingButDigitOrDot);
		}

		for (int y = 0; y < symbolsConverted.size(); y++) {
			List<Integer> lst = new ArrayList<>();
			CharacterIterator it = new StringCharacterIterator(symbolsConverted.get(y));
			int x = 0;
			while (it.current() != CharacterIterator.DONE) {
				if (it.current() == 's') {
					lst.add(x);
				}
				x++;
				it.next();
			}
			positionOfSymbolYxMap.put(y, lst);
		}

		for (int i = 0; i < symbolsConverted.size(); i++) {
			CharacterIterator it = new StringCharacterIterator(symbolsConverted.get(i));
			int x = 0;
			while (it.current() != CharacterIterator.DONE) {
				String temp = "0";
				boolean hasSymbolTouching = false;
				while (String.valueOf(it.current()).matches("[0-9]")) {
					temp += String.valueOf(it.current());
					if (!hasSymbolTouching) {
						hasSymbolTouching = checkForSymbol(i, x);
					}
					it.next();
					x++;
				}
				if (hasSymbolTouching) {
					total += Integer.valueOf(temp);
					System.out.println(Integer.valueOf(temp));
				}
				it.next();
				x++;
			}
		}
		System.out.println("Solution day3, part1: " + total);

	}

	private boolean checkForSymbol(final int i, final int x) {
		if (i == 0) {
			List<Integer> lst = positionOfSymbolYxMap.get(i + 1);
			for (final Integer o : lst) {
				if (x == o || x == o + 1 || x == o -1){
					return true;
				}
			}

			List<Integer> lst2 = positionOfSymbolYxMap.get(i);
			for (final Integer o : lst2) {
				if (x == o + 1 || x == o -1){
					return true;
				}
			}
		} else if (i == 139) {
			List<Integer> lst = positionOfSymbolYxMap.get(i - 1);
			for (final Integer o : lst) {
				if (x == o || x == o + 1 || x == o -1){
					return true;
				}
			}
			List<Integer> lst2 = positionOfSymbolYxMap.get(i);
			for (final Integer o : lst2) {
				if (x == o + 1 || x == o -1){
					return true;
				}
			}
		} else {
			List<Integer> lst = positionOfSymbolYxMap.get(i - 1);
			for (final Integer o : lst) {
				if (x == o || x == o + 1 || x == o -1){
					return true;
				}
			}
			List<Integer> lst2 = positionOfSymbolYxMap.get(i + 1);
			for (final Integer o : lst2) {
				if (x == o || x == o + 1 || x == o - 1){
					return true;
				}
			}
			List<Integer> lst3 = positionOfSymbolYxMap.get(i);
			for (final Integer o : lst3) {
				if (x == o + 1 || x == o -1){
					return true;
				}
			}
		}
		return false;
	}
}