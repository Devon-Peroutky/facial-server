package jsonObjects;

import java.util.ArrayList;

public class SearchResponse {
	public ArrayList<Candidate> candidates;
	
	
	public double getAvgMatch() {
		double total = 0;
		int count = 0;
		for (Candidate candidate : candidates) {
			total += candidate.similarity;
			count++;
		}
		return total / count;
	}
	
	public class Candidate {
		public String face_id;
		public double similarity;
		public String tag;
			
	}
}