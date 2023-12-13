package com.advent2023.day08;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advent2023.utilities.Lcm;

public class Solution8 {

	public String steps =
			"LRRLRRRLLRRLRRLRRRLRLRRLRRLRRRLRRRLRRLRLLRRLRLRRLRRLRLRLRRLRRLRRRLLRLLRRLRLRRRLRRRLLRRRLRRLRLLRRLRRRLRLLRLRLLRRRLRLRRRLLRRRLRRRLRRLLRLRLLRRLRRLLRRRLLRLLRRLRRRLRLRRRLRLRRLRLRLRRLRRLRRLLLRRRLRLRLLLRRRLLRLRRLRRRLRRLRRLRRRLRRRLRRLLRLLRRLRRRLLRRRLRLRLRRRLRRRLRRLRRLRLLRLRRLLRRLLRRRR";

	public static void main(String[] args) {
		Solution8 aoc8 = new Solution8();

		List<String> input = textFileToList("d08.txt");
		Map<String, Day08Map> inputProcessed = aoc8.createMap(input);

		//		long solution = aoc8.calculateStepsNeeded(aoc8.steps, inputProcessed, "AAA", "ZZZ");
		//		System.out.println("Solution Day 8, part1 : " + solution);

		long a1 = aoc8.calculateStepsNeeded(aoc8.steps, inputProcessed, "TSA", "Z");
		long a2 = aoc8.calculateStepsNeeded(aoc8.steps, inputProcessed, "JTA", "Z");
		long a3 = aoc8.calculateStepsNeeded(aoc8.steps, inputProcessed, "BLA", "Z");
		long a4 = aoc8.calculateStepsNeeded(aoc8.steps, inputProcessed, "NBA", "Z");
		long a5 = aoc8.calculateStepsNeeded(aoc8.steps, inputProcessed, "AAA", "Z");
		long a6 = aoc8.calculateStepsNeeded(aoc8.steps, inputProcessed, "QXA", "Z");
		long[] startpointsArray = {a1, a2, a3, a4, a5, a6};

		long solution2 = Lcm.lcmOfArrayElements(startpointsArray);
		System.out.println("Solution Day 8, part2 : " + solution2);
	}

	long calculatePart2(List<Long> stepsPerNode) {
		Long node1 = stepsPerNode.get(5);
		Long node2 = stepsPerNode.get(4);
		Long node3 = stepsPerNode.get(3);
		Long node4 = stepsPerNode.get(2);
		Long node5 = stepsPerNode.get(1);
		Long node6 = stepsPerNode.get(0);

		Long node1result = stepsPerNode.get(5);
		Long node2result = stepsPerNode.get(4);
		Long node3result = stepsPerNode.get(3);
		Long node4result = stepsPerNode.get(2);
		Long node5result = stepsPerNode.get(1);
		Long node6result = stepsPerNode.get(0);

		while (!node1result.equals(node2result) && !node1result.equals(node3result) && !node1result.equals(node4result)
				&& !node1result.equals(node5result) && !node1result.equals(node6result)) {
			if (node2 < node1) {
				node2result += node2;
			} else if (node2 > node1) {
				node1result += node1;
			}

			if (node3 < node1) {
				node3result += node3;
			} else if (node3 > node1) {
				node1result += node1;
			}

			if (node4 < node1) {
				node4result += node4;
			} else if (node4 > node1) {
				node1result += node1;
			}

			if (node5 < node1) {
				node5result += node5;
			} else if (node5 > node1) {
				node1result += node1;
			}

			if (node6 < node1) {
				node6result += node6;
			} else if (node6 > node1) {
				node1result += node1;
			}
		}

		System.out.println("node1: " + node1result);
		System.out.println("node2: " + node2result);
		System.out.println("node3: " + node3result);
		System.out.println("node4: " + node4result);
		System.out.println("node5: " + node5result);
		System.out.println("node6: " + node6result);

		return node1result;
	}

	long calculateStepsNeeded(final String steps, final Map<String, Day08Map> input, String start, String end) {
		long amountOfStepsTaken = 0;
		String currentPosition = start;

		int i = 0;
		char step = steps.charAt(i);
		while (!currentPosition.endsWith(end)) {
			// find my node
			Day08Map myNode = input.get(currentPosition);
			// go left or right
			currentPosition = goLeftOrRight(step, myNode);
			amountOfStepsTaken++;

			i = (i + 1) % steps.length();
			step = steps.charAt(i);
		}
		return amountOfStepsTaken;
	}

	String goLeftOrRight(char symbol, Day08Map node) {
		if (symbol == 'L') {
			return node.getLeftLocation();
		}
		return node.getRightLocation();
	}

	public List<Day08Map> createList(final List<String> input) {
		List<Day08Map> result = new ArrayList<>();

		for (final String s : input) {
			Day08Map entry = processLine(s);
			result.add(entry);
		}

		return result;
	}

	public Map<String, Day08Map> createMap(final List<String> input) {
		Map<String, Day08Map> result = new HashMap<>();
		for (final String line : input) {
			Day08Map entry = processLine(line);
			result.put(entry.getCurrentLocation(), entry);
		}
		return result;
	}

	public Day08Map processLine(final String s) {
		return new Day08Map(s.replaceAll("[^a-zA-Z]+", ""));
	}
}