package com.advent2023.day05;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SolutionTest {

	Solution actual = new Solution();

	@Test
	public void canCreateSeedsList() {
		List<Long> seedLst = List.of(101L, 404L, 0L, 999666L);
		String input = "seeds: 101 404 0 999666";
		List<String> inputLst = List.of(input);

		assertEquals(seedLst, actual.createSeedLst(inputLst));
	}

	@Test
	public void canCreateSeedToSoilLst() {
		String in1 = "50 98 2";
		String in2 = "52 50 48";
		List<String> inLst = List.of(in1, in2);
		List<MapConverter> actualMc = new ArrayList<>();

		for (final String s : inLst) {
			MapConverter mc = new MapConverter(s);
			actualMc.add(mc);
		}

		List<MapConverter> expectedMc = new ArrayList<>();
		expectedMc.add(new MapConverter(98L, 50L, 2L));
		expectedMc.add(new MapConverter(50L, 52L, 48L));

		assertEquals(expectedMc, actualMc);
	}

	@Test
	public void canCalculateNewDestination() {
		MapConverter actualMc = new MapConverter(98L, 50L, 2L);
		assertEquals(51L, actual.calculateNewDestination(99L, actualMc));

		MapConverter actualMc2 = new MapConverter(50L, 52L, 48L);
		assertEquals(98L, actual.calculateNewDestination(96L, actualMc2));

		MapConverter actualMc3 = new MapConverter(50L, 52L, 48L);
		assertEquals(98L, actual.calculateNewDestination(98L, actualMc3));

		MapConverter actualMc4 = new MapConverter(50L, 52L, 48L);
		assertEquals(52L, actual.calculateNewDestination(50L, actualMc4));

		MapConverter actualMc5 = new MapConverter(50L, 52L, 48L);
		assertEquals(49L, actual.calculateNewDestination(49L, actualMc5));
	}

	@Test
	public void canCreateSeedsForPart2() {
		List<Long> seed = List.of(79L, 14L, 55L, 13L);
		List<Long> seedExpected = List.of(79L, 92L,
				55L, 67L);

		assertEquals(seedExpected, actual.createSeedLstForPart2(seed));
	}
}