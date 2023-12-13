package com.advent2023.day08;

import java.util.Objects;

public class Day08Map {

	private final String currentLocation;
	private final String leftLocation;
	private final String rightLocation;

	public Day08Map(String input) {
		this.currentLocation = input.substring(0, 3);
		this.leftLocation = input.substring(3, 6);
		this.rightLocation = input.substring(6, 9);
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public String getLeftLocation() {
		return leftLocation;
	}

	public String getRightLocation() {
		return rightLocation;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Day08Map day08Map = (Day08Map) o;
		return currentLocation.equals(day08Map.currentLocation) &&
				leftLocation.equals(day08Map.leftLocation) &&
				rightLocation.equals(day08Map.rightLocation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentLocation, leftLocation, rightLocation);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"currentLocation='" + currentLocation + "'" +
				", leftLocation='" + leftLocation + "'" +
				", rightLocation='" + rightLocation + "'" +
				"}";
	}
}