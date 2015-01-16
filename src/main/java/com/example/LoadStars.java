package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import code.QueryService;
import jsonObjects.Star;

public class LoadStars {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("stars.txt"));
		int state = 0;
		Star curStar = new Star();
		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			if (line.equals("")) {
				continue;
			}
			switch(state) {
			case 0:
				curStar = new Star();
				String name = line.substring(line.indexOf(" ") + 1);
				curStar.name = name;
				state++;
				break;
			case 1:
				String website = line.split(":")[1].trim();
				curStar.website = website;
				state++;
				break;
			case 2:
				String twitter = line.split(":")[1].trim();
				curStar.twitter = twitter;
				state++;
				break;
			case 3:
				String recentWork = line.split(":")[1].trim();
				curStar.recentWork = recentWork;
				state++;
				break;
			case 4:
				String bio = line;
				curStar.bio = bio;
				System.out.println(curStar);
				QueryService.createStar(curStar);
				
				state = 0;
				break;
			}
		}
	}
}
