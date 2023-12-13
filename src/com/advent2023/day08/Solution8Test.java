package com.advent2023.day08;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Solution8Test {

	Solution8 actual = new Solution8();
	private static final String START = "AAA";
	private static final String END = "ZZZ";

	@Test
	public void canProcessLine() {
		Day08Map result = new Day08Map("BBBAAAZZZ)");

		assertEquals(result, actual.processLine("BBB = (AAA, ZZZ)"));
		assertEquals(result.getCurrentLocation(), "BBB");
		assertEquals(result.getLeftLocation(), "AAA");
		assertEquals(result.getRightLocation(), "ZZZ");
	}

	@Test
	public void canCreateList() {
		List<String> input = List.of("AAA = (BBB, BBB)", "BBB = (AAA, ZZZ)", "ZZZ = (ZZZ, ZZZ)");

		Day08Map entry1 = new Day08Map("AAABBBBBB");
		Day08Map entry2 = new Day08Map("BBBAAAZZZ");
		Day08Map entry3 = new Day08Map("ZZZZZZZZZ");

		List<Day08Map> expected = List.of(entry1, entry2, entry3);

		assertEquals(expected, actual.createList(input));
	}

	@Test
	public void canCalculateStepsForOneLeftStep() {
		Day08Map entry1 = new Day08Map("AAAZZZBBB");
		Day08Map entry2 = new Day08Map("ZZZBBBAAA");

		Map<String, Day08Map> input = Map.of(entry1.getCurrentLocation(), entry1, entry2.getCurrentLocation(), entry2);

		assertEquals(1, actual.calculateStepsNeeded("L", input, START, END));
	}

	@Test
	public void canCalculateStepsForTwoLeftSteps() {
		Day08Map entry1 = new Day08Map("AAADDDBBB");
		Day08Map entry2 = new Day08Map("DDDZZZAAA");

		Map<String, Day08Map> input = Map.of(entry1.getCurrentLocation(), entry1, entry2.getCurrentLocation(), entry2);

		assertEquals(2, actual.calculateStepsNeeded("LL", input, START, END));
	}

	@Test
	public void canCalculateStepsForOneRightStep() {
		Day08Map entry1 = new Day08Map("AAABBBZZZ");
		Day08Map entry2 = new Day08Map("ZZZBBBAAA");

		Map<String, Day08Map> input = Map.of(entry1.getCurrentLocation(), entry1, entry2.getCurrentLocation(), entry2);

		assertEquals(1, actual.calculateStepsNeeded("R", input, START, END));
	}

	@Test
	public void canCalculateStepsForTwoRightSteps() {
		Day08Map entry1 = new Day08Map("AAABBBDDD");
		Day08Map entry2 = new Day08Map("DDDAAAZZZ");

		Map<String, Day08Map> input = Map.of(entry1.getCurrentLocation(), entry1, entry2.getCurrentLocation(), entry2);

		assertEquals(2, actual.calculateStepsNeeded("RR", input, START, END));
	}

	@Test
	public void canCalculateStepsForLeftAndRightStep() {
		Day08Map entry1 = new Day08Map("AAADDDBBB");
		Day08Map entry2 = new Day08Map("DDDAAAZZZ");

		Map<String, Day08Map> input = Map.of(entry1.getCurrentLocation(), entry1, entry2.getCurrentLocation(), entry2);

		assertEquals(2, actual.calculateStepsNeeded("LR", input, START, END));
	}

	@Test
	public void canCalculateStepsNeeded() {
		Day08Map entry1 = new Day08Map("AAABBBBBB");
		Day08Map entry2 = new Day08Map("BBBAAAZZZ");
		Day08Map entry3 = new Day08Map("ZZZZZZZZZ");

		Map<String, Day08Map> input = Map.of(entry1.getCurrentLocation(), entry1, entry2.getCurrentLocation(), entry2);

		assertEquals(6, actual.calculateStepsNeeded("LLR", input, START, END));
	}

	@Test
	public void canCalculateStepsNeededWithMultipleLoopsInSteps() {
		Day08Map entry1 = new Day08Map("AAABBBBBB");
		Day08Map entry2 = new Day08Map("BBBAAADDD");
		Day08Map entry3 = new Day08Map("CCCAAAEEE");
		Day08Map entry4 = new Day08Map("DDDEEEEEE");
		Day08Map entry5 = new Day08Map("EEEDDDZZZ");
		Day08Map entry6 = new Day08Map("ZZZEEEDDD");

		Map<String, Day08Map> input = Map.of(entry1.getCurrentLocation(), entry1, entry2.getCurrentLocation(), entry2,
				entry3.getCurrentLocation(), entry3, entry4.getCurrentLocation(), entry4,
				entry5.getCurrentLocation(), entry5, entry6.getCurrentLocation(), entry6);

		assertEquals(12, actual.calculateStepsNeeded("LLR", input, START, END));
	}

	@Test
	public void canCalculatePart2() {
		List<Long> input = List.of(1L, 1L, 1L, 1L, 1L, 10L);

		assertEquals(10L, actual.calculatePart2(input));
	}

}