package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import code.QueryService;

public class LoadImages {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("images.txt"));
		int state = 0;
		String curName = "";
		ArrayList<String> images = new ArrayList<String>();
		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			switch(state) {
			case 0:
				curName = line;
				images = new ArrayList<String>();
				state++;
				break;
			case 1:
				if (line.equals("")) {
					state = 0;
					// call create here
					System.out.println(curName);
					QueryService.createImages(curName, images);
					break;
				} else {
					images.add(line);
				}
			}
		}
	}
}
