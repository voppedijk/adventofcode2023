package com.advent2023.day05;

import java.util.Objects;

public class MapConverter {
	final long source;
	final long destination;
	final long range;

	public MapConverter(final String in) {
		String[] inSplitted = in.split(" ");
		this.source = Long.parseLong(inSplitted[1]);
		this.destination = Long.parseLong(inSplitted[0]);
		this.range = Long.parseLong(inSplitted[2]);
	}

	public MapConverter(final Long source, final Long destination, final Long range) {
		this.source = source;
		this.destination = destination;
		this.range = range;
	}

	long getSource() {
		return source;
	}

	long getDestination() {
		return destination;
	}

	long getRange() {
		return range;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final MapConverter that = (MapConverter) o;
		return source == that.source &&
				destination == that.destination &&
				range == that.range;
	}

	@Override
	public int hashCode() {
		return Objects.hash(source, destination, range);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{" +
				"source=" + source +
				", destination=" + destination +
				", range=" + range +
				"}";
	}
}