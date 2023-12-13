package com.advent2023.day07;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Objects;

class CardsAndBid {

	private String card1;
	private String card2;
	private String card3;
	private String card4;
	private String card5;
	private String bid;
	private Type type;

	CardsAndBid(String handAndBid) {
		String s = translateCardsToLetters(handAndBid);

		this.card1 = s.substring(0, 1);
		this.card2 = s.substring(1, 2);
		this.card3 = s.substring(2, 3);
		this.card4 = s.substring(3, 4);
		this.card5 = s.substring(4, 5);
		this.bid = handAndBid.substring(6);
	}

	CardsAndBid(final String card1, final String card2, final String card3, final String card4, final String card5,
			final String bid, final Type type) {
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.card4 = card4;
		this.card5 = card5;
		this.bid = bid;
		this.type = type;
	}

	CardsAndBid() {
	}

	String translateCardsToLetters(final String handAndBid) {
		String result = "";

		CharacterIterator it = new StringCharacterIterator(handAndBid.split(" ")[0]);
		while (it.current() != CharacterIterator.DONE) {
			switch (it.current()) {
			case '2':
				result = result + "N";
				break;
			case '3':
				result = result + "M";
				break;
			case '4':
				result = result + "L";
				break;
			case '5':
				result = result + "K";
				break;
			case '6':
				result = result + "J";
				break;
			case '7':
				result = result + "I";
				break;
			case '8':
				result = result + "H";
				break;
			case '9':
				result = result + "G";
				break;
			case 'T':
				result = result + "F";
				break;
			case 'J':
				result = result + "E";
				break;
			case 'Q':
				result = result + "D";
				break;
			case 'K':
				result = result + "C";
				break;
			case 'A':
				result = result + "B";
				break;

			}
			it.next();
		}

		return result;
	}

	Type getType() {
		return type;
	}

	void setType(final Type type) {
		this.type = type;
	}

	String getCard1() {
		return card1;
	}

	String getCard2() {
		return card2;
	}

	String getCard3() {
		return card3;
	}

	String getCard4() {
		return card4;
	}

	String getCard5() {
		return card5;
	}

	String getBid() {
		return bid;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CardsAndBid that = (CardsAndBid) o;
		return card1.equals(that.card1) &&
				card2.equals(that.card2) &&
				card3.equals(that.card3) &&
				card4.equals(that.card4) &&
				card5.equals(that.card5) &&
				bid.equals(that.bid) &&
				type == that.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(card1, card2, card3, card4, card5, bid, type);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"card1='" + card1 + "'" +
				", card2='" + card2 + "'" +
				", card3='" + card3 + "'" +
				", card4='" + card4 + "'" +
				", card5='" + card5 + "'" +
				", bid='" + bid + "'" +
				", type=" + type +
				"}";
	}
}