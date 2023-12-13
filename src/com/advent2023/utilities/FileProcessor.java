package com.advent2023.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileProcessor {

	public static List<String> textFileToList(String filename) {
		Path path = Paths.get("src\\com\\advent2023\\inputfiles\\" + filename);
		List<String> read = null;

		try {
			read = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return read;
	}

}