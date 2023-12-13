package com.advent2023.day05;

import static com.advent2023.utilities.FileProcessor.textFileToList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

	static final List<MapConverter> mcOneLst = new ArrayList<>();
	static final List<MapConverter> mcTwoLst = new ArrayList<>();
	static final List<MapConverter> mcThreeLst = new ArrayList<>();
	static final List<MapConverter> mcFourLst = new ArrayList<>();
	static final List<MapConverter> mcFiveLst = new ArrayList<>();
	static final List<MapConverter> mcSixLst = new ArrayList<>();
	static final List<MapConverter> mcSevenLst = new ArrayList<>();

	public static void main(String[] args) {
		List<String> input = textFileToList("d05.txt");
		Solution aoc5 = new Solution();
		List<Long> seeds = aoc5.createSeedLst(input);
		createMcLst("seed-to-soil map:", mcOneLst, input);
		createMcLst("soil-to-fertilizer map:", mcTwoLst, input);
		createMcLst("fertilizer-to-water map:", mcThreeLst, input);
		createMcLst("water-to-light map:", mcFourLst, input);
		createMcLst("light-to-temperature map:", mcFiveLst, input);
		createMcLst("temperature-to-humidity map:", mcSixLst, input);
		createMcLst("humidity-to-location map:", mcSevenLst, input);

		System.out.println("Solution day5, part1: " + aoc5.getLowestLocation(input, seeds));

		//List<Long> seeds2 = aoc5.createSeedLstForPart2(seeds);
		//System.out.println("Solution day5, part2: " + aoc5.getLowestLocation2(input, seeds2));
	}

	private Long getLowestLocation2(final List<String> input, final List<Long> seeds2) {
		for (int i = 0; i < seeds2.size(); i++) {
			//seed range current seed from loop (input)
			Long firstSeed = seeds2.get(i);
			Long lastSeed = seeds2.get(i + 1);

			for (final MapConverter mapConverter : mcOneLst) {
				//seed range from mapConverter
				Long lowestInMC = mapConverter.getSource();
				Long highestInMC = lowestInMC + mapConverter.getRange() - 1;

			}



			//add lowest to lst
			i++;
		}

		return 0L;
	}

	List<Long> createSeedLstForPart2(final List<Long> seeds) {
		List<Long> seeds2 = new ArrayList<>();

		for (int i = 0; i < seeds.size(); i++) {
			long seed = seeds.get(i);
			long range = seeds.get(i + 1);

			seeds2.add(seed);
			seeds2.add(seed + range - 1);

			i++;
		}

		return seeds2;
	}

	public Long getLowestLocation(final List<String> input, final List<Long> seeds) {
		List<Long> temp1 = createLocations(seeds, mcOneLst);
		List<Long> temp2 = createLocations(temp1, mcTwoLst);
		List<Long> temp3 = createLocations(temp2, mcThreeLst);
		List<Long> temp4 = createLocations(temp3, mcFourLst);
		List<Long> temp5 = createLocations(temp4, mcFiveLst);
		List<Long> temp6 = createLocations(temp5, mcSixLst);
		List<Long> locationLst = createLocations(temp6, mcSevenLst);

		Collections.sort(locationLst);
		return locationLst.get(0);
	}

	List<Long> createLocations(final List<Long> seeds, final List<MapConverter> mcOneLst) {
		List<Long> tempLst = new ArrayList<>();

		for (final Long seed : seeds) {
			Long temp = seed;
			for (final MapConverter mapConverter : mcOneLst) {
				temp = calculateNewDestination(seed, mapConverter);
				if (!temp.equals(seed)) {
					break;
				}
			}
			tempLst.add(temp);
		}

		return tempLst;
	}

	private static void createMcLst(final String type, final List<MapConverter> mcLst, final List<String> input) {
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).contains(type)) {
				String line = input.get(++i);

				while (!line.isBlank()) {
					mcLst.add(new MapConverter(line));
					if (i + 1 < input.size()) {
						line = input.get(++i);
					} else {
						return;
					}
				}
			}
		}
	}

	public List<Long> createSeedLst(final List<String> input) {
		List<Long> result = new ArrayList<>();

		for (final String line : input) {
			if (line.contains("seeds:")) {
				String seedsStr = line.split(":")[1].strip();
				String[] seeds = seedsStr.split(" ");

				for (final String seed : seeds) {
					result.add(Long.valueOf(seed));
				}
			}
		}

		return result;
	}

	public long calculateNewDestination(final long current, MapConverter input) {
		long source = input.getSource();
		long destination = input.getDestination();
		long range = input.getRange();
		long sourceMax = source + range;
		long difference = destination - source;

		if (current >= source && current < sourceMax) {
			return current + difference;
		}

		return current;
	}
}