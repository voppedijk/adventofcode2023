package com.advent2023.day07;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Solution1Test {

	private Solution1 aoc7 = new Solution1();

	@Test
	public void canCreateCardsClass() {
		CardsAndBid result = new CardsAndBid("32T3K 765");
		assertEquals("M", result.getCard1());
		assertEquals("N", result.getCard2());
		assertEquals("F", result.getCard3());
		assertEquals("M", result.getCard4());
		assertEquals("C", result.getCard5());
		assertEquals("765", result.getBid());
	}

	@Test
	public void canGetTypeValue() {
		Type fiveOfAKind = Type.FIVEOFAKIND;
		Type fourOfAKind = Type.FOUROFAKIND;
		Type FullHouse = Type.FULLHOUSE;
		Type threeOfaKind = Type.THREEOFAKIND;
		Type twoPair = Type.TWOPAIR;
		Type onePair = Type.ONEPAIR;
		Type highCard = Type.HIGHCARD;
		assertEquals(0, fiveOfAKind.ordinal());
		assertEquals(1, fourOfAKind.ordinal());
		assertEquals(2, FullHouse.ordinal());
		assertEquals(3, threeOfaKind.ordinal());
		assertEquals(4, twoPair.ordinal());
		assertEquals(5, onePair.ordinal());
		assertEquals(6, highCard.ordinal());
	}

	@Test
	public void canDetectFiveOfAKind() {
		CardsAndBid result = new CardsAndBid("AAAAA 3");

		assertEquals(Type.FIVEOFAKIND, aoc7.detectTypeOfHand(result));
	}

	@Test
	public void canDetectFourOfAKind() {
		CardsAndBid result = new CardsAndBid("33K33 13");

		assertEquals(Type.FOUROFAKIND, aoc7.detectTypeOfHand(result));
	}

	@Test
	public void canDetectFullHouse() {
		CardsAndBid result = new CardsAndBid("33TT3 399");

		assertEquals(Type.FULLHOUSE, aoc7.detectTypeOfHand(result));
	}

	@Test
	public void canDetectThreeOfAKind() {
		CardsAndBid result = new CardsAndBid("33K3A 3");

		assertEquals(Type.THREEOFAKIND, aoc7.detectTypeOfHand(result));
	}

	@Test
	public void canDetectTwoPair() {
		CardsAndBid result = new CardsAndBid("AKQQA 500");

		assertEquals(Type.TWOPAIR, aoc7.detectTypeOfHand(result));
	}

	@Test
	public void canDetectOnePair() {
		CardsAndBid result = new CardsAndBid("76T9T 123");

		assertEquals(Type.ONEPAIR, aoc7.detectTypeOfHand(result));
	}

	@Test
	public void canDetectHighCard() {
		CardsAndBid result = new CardsAndBid("23456 321");

		assertEquals(Type.HIGHCARD, aoc7.detectTypeOfHand(result));
	}

	@Test
	public void canTranslateCardInput() {
		String input = "J57Q8 571";
		CardsAndBid cb = new CardsAndBid();
		String actual = cb.translateCardsToLetters(input);

		assertEquals("EKIDH", actual);
	}

	@Test
	public void canProcessInput() {
		CardsAndBid cb1 = new CardsAndBid("M", "N", "F", "M", "E", "893", Type.ONEPAIR);
		CardsAndBid cb2 = new CardsAndBid("B", "G", "G", "L", "N", "54", Type.ONEPAIR);
		CardsAndBid cb3 = new CardsAndBid("I", "I", "G", "F", "C", "931", Type.ONEPAIR);
		CardsAndBid cb4 = new CardsAndBid("J", "G", "J", "G", "J", "457", Type.FULLHOUSE);
		CardsAndBid cb5 = new CardsAndBid("L", "N", "F", "L", "E", "193", Type.ONEPAIR);
		List<CardsAndBid> expected = List.of(cb4, cb2, cb3, cb5, cb1);

		List<String> actual = List.of(
				"32T3J 893",
				"A9942 54",
				"779TK 931",
				"69696 457",
				"42T4J 193");

		assertEquals(expected, aoc7.processInput(actual));
	}

	@Test
	public void canCalculateScore() {
		CardsAndBid cb1 = new CardsAndBid("M", "N", "F", "M", "E", "893", Type.ONEPAIR);
		CardsAndBid cb2 = new CardsAndBid("B", "G", "G", "L", "N", "54", Type.ONEPAIR);
		CardsAndBid cb3 = new CardsAndBid("I", "I", "G", "F", "C", "931", Type.ONEPAIR);
		CardsAndBid cb4 = new CardsAndBid("J", "G", "J", "G", "J", "457", Type.FULLHOUSE);
		CardsAndBid cb5 = new CardsAndBid("L", "N", "F", "L", "E", "193", Type.ONEPAIR);
		List<CardsAndBid> actual = List.of(cb4, cb2, cb3, cb5, cb1);

		assertEquals(6573, aoc7.calculateScore(actual));
	}

}