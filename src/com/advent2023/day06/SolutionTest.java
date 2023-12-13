package com.advent2023.day06;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SolutionTest {

	@Test
	public void canCalculateWinningOptions() {
		Solution exptected = new Solution();
		assertEquals(List.of(2L, 3L, 4L, 5L), exptected.calculateWinningOptions(7L, 9L));
	}

	@Test
	public void canMultiplyWinningOptions() {
		Solution exptected = new Solution();
		assertEquals(288L, exptected.multiplyWinningOptions(4L, 8L, 9L));
	}
}