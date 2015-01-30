package com.example;

import java.util.ArrayList;

import jooq.generated.tables.Stars;
import jsonObjects.Star;
import code.QueryService;

public class AssignFaceIds {
	public static void main(String[] args) {
		ArrayList<Star> stars = QueryService.loadStars();
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).id = i;
			QueryService.updateStar(stars.get(i));
		}
	}
}
