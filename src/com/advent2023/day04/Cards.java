package com.advent2023.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Cards {

	private List<Integer> myCards = new ArrayList<>();
	private List<Integer> winningNumbers = new ArrayList<>();
	private int copiesOfCard = 1;

	Cards(String input) {
		String[] cardsAndWinningNumbers = input.split(Pattern.quote(":"))[1].split(Pattern.quote("|"));
		String[] cards = cardsAndWinningNumbers[0].split(" ");
		String[] winningNumbers = cardsAndWinningNumbers[1].split(" ");

		for (final String card : cards) {
			if (card.isBlank()) {
				continue;
			}
			myCards.add(Integer.valueOf(card));
		}

		for (final String winningNumber : winningNumbers) {
			if (winningNumber.isBlank()) {
				continue;
			}
			this.winningNumbers.add(Integer.valueOf(winningNumber));
		}

	}

	int getCopiesOfCard() {
		return copiesOfCard;
	}

	void setCopiesOfCard(final int copiesOfCard) {
		this.copiesOfCard = copiesOfCard;
	}

	List<Integer> getMyCards() {
		return myCards;
	}

	List<Integer> getWinningNumbers() {
		return winningNumbers;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Cards that = (Cards) o;
		return copiesOfCard == that.copiesOfCard &&
				myCards.equals(that.myCards) &&
				winningNumbers.equals(that.winningNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(myCards, winningNumbers, copiesOfCard);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"myCards=" + myCards +
				", winningNumbers=" + winningNumbers +
				", copiesOfCard=" + copiesOfCard +
				"}";
	}
}