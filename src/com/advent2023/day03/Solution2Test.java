package com.advent2023.day03;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Solution2Test {
	Solution2 aoc3 = new Solution2();

	@Test
	public void canFindStarPosition() {
		String line = ".............*............................612*......304..*..........*.......@175...#...*...........*890...........*.............*..*........";

		List<Integer> expected = List.of(13, 45, 57, 68, 87, 99, 114, 128, 131);

		assertEquals(expected, aoc3.findPositionOfStar(line));
	}

	@Test
	public void canCollectAllStarsFromInput() {
		String line1 = ".............*............................612*......304..*..........*.......@175...#...*...........*890...........*.............*..*........";
		String line2 = "..........346......................997........923......*..253..........698........122.746.....-832..........766.432..229.....674....415.....";
		List<String> input = List.of(line1, line2);

		List<Integer> lst1 = List.of(13, 45, 57, 68, 87, 99, 114, 128, 131);
		List<Integer> lst2 = List.of(55);
		Map<Integer, List<Integer>> expected = Map.of(0, lst1, 1, lst2);

		assertEquals(expected, aoc3.collectAllStars(input));
	}
}