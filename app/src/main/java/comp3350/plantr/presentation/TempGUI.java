package comp3350.plantr.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created: 5/28/2017
 * Raman Maan
 *
 * Purpose: A temporary CLI adapted from sample project
 * Commands:	exit 			- quit the program
 * 				get plant		- get from table plants
 * 				get allPlants	- get allPlants
 */

public class TempGUI {
	public static BufferedReader console;
	public static String inputLine;
	public static String[] inputTokens;

	public static void run() {
		try {
			console = new BufferedReader(new InputStreamReader(System.in));
			process();
			console.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void process() {
		readLine();
		while (inputLine != null && !inputLine.equalsIgnoreCase("exit")) {
			inputTokens = inputLine.split("\\s+");
			parse();
			readLine();
		}
	}

	public static void readLine() {
		try {
			System.out.print(">");
			inputLine = console.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void parse() {
		switch (inputTokens[0].toLowerCase()) {
			case "get":
				processGet();
				break;
			default:
				System.out.println("Invalid command <" + inputTokens[0] + ">");
		}
	}

	public static void processGet() {
		switch (inputTokens[1].toLowerCase()) {
			case "plant":
				if (inputTokens.length == 3) {
					processGet_Plant();
				} else {
					System.out.println("Usage: get plant <id : int>");
				}
				break;
			case "allplants":
				processGet_AllPlants();
				break;
			default:
				System.out.println("Invalid data type <" + inputTokens[1] + ">");
		}
	}

	public static void processGet_Plant() {
		System.out.println("whew lad");//TODO
	}

	public static void processGet_AllPlants() {
		System.out.println("dat boi");//TODO
	}
}
