package com.advent2023.day07;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution1 {

	private static List<String> input;

	public static void main(String[] args) {
		input = textFileToList("d07.txt");

		Solution1 aoc7 = new Solution1();
		List<CardsAndBid> typeAndHandLst = aoc7.processInput(input);

		System.out.println("solution 1: " + aoc7.calculateScore(typeAndHandLst));
	}

	long calculateScore(List<CardsAndBid> typeAndHandLst) {
		long score = 0;
		int rank = typeAndHandLst.size();

		for (CardsAndBid cardsAndBid : typeAndHandLst) {
			int singleScore = rank * Integer.parseInt(cardsAndBid.getBid());
			score += singleScore;
			rank--;
		}
		return score;
	}

	List<CardsAndBid> processInput(List<String> input) {
		List<CardsAndBid> result = new ArrayList<>();

		for (final String s : input) {
			CardsAndBid handProcessed = new CardsAndBid(s);
			Type type = detectTypeOfHand(handProcessed);
			handProcessed.setType(type);
			result.add(handProcessed);
		}

		Comparator<CardsAndBid> compareByTypeAndCards
				= Comparator.comparing(CardsAndBid::getType)
				.thenComparing(CardsAndBid::getCard1)
				.thenComparing(CardsAndBid::getCard2)
				.thenComparing(CardsAndBid::getCard3)
				.thenComparing(CardsAndBid::getCard4)
				.thenComparing(CardsAndBid::getCard5);
		result.sort(compareByTypeAndCards);

		return result;
	}

	Type detectTypeOfHand(final CardsAndBid handAndBid) {
		String card1 = handAndBid.getCard1();
		String card2 = handAndBid.getCard2();
		String card3 = handAndBid.getCard3();
		String card4 = handAndBid.getCard4();
		String card5 = handAndBid.getCard5();

		int similarCardsAsCard1 = 1;
		int similarCardsAsCard2 = 1;
		int similarCardsAsCard3 = 1;
		int similarCardsAsCard4 = 1;
		int similarCardsAsCard5 = 1;

		if (card1.equals(card2)) {
			similarCardsAsCard1++;
			similarCardsAsCard2++;
		}

		if (card1.equals(card3)) {
			similarCardsAsCard1++;
			similarCardsAsCard3++;
		}

		if (card1.equals(card4)) {
			similarCardsAsCard1++;
			similarCardsAsCard4++;
		}

		if (card1.equals(card5)) {
			similarCardsAsCard1++;
			similarCardsAsCard5++;
		}

		if (card2.equals(card3)) {
			similarCardsAsCard2++;
			similarCardsAsCard3++;
		}

		if (card2.equals(card4)) {
			similarCardsAsCard2++;
			similarCardsAsCard4++;
		}

		if (card2.equals(card5)) {
			similarCardsAsCard2++;
			similarCardsAsCard5++;
		}

		if (card3.equals(card4)) {
			similarCardsAsCard3++;
			similarCardsAsCard4++;
		}

		if (card3.equals(card5)) {
			similarCardsAsCard3++;
			similarCardsAsCard5++;
		}

		if (card4.equals(card5)) {
			similarCardsAsCard4++;
			similarCardsAsCard5++;
		}

		int total = similarCardsAsCard1 + similarCardsAsCard2 + similarCardsAsCard3 +
				similarCardsAsCard4 + similarCardsAsCard5;

		if (total == 25) {
			return Type.FIVEOFAKIND;
		} else if (total == 17) {
			return Type.FOUROFAKIND;
		} else if (total == 13) {
			return Type.FULLHOUSE;
		} else if (total == 11) {
			return Type.THREEOFAKIND;
		} else if (total == 9) {
			return Type.TWOPAIR;
		} else if (total == 7) {
			return Type.ONEPAIR;
		} else {
			return Type.HIGHCARD;
		}
	}
}