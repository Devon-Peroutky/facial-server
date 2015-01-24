package jsonObjects;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
	public List<Candidate> candidate;
	public String session_id;
	
	
	public double getAvgMatch() {
		double total = 0;
		int count = 0;
		for (Candidate c : this.candidate) {
			total += c.similarity;
			count++;
		}
		return total / count;
	}
	
	public static class Candidate {
		public String face_id;
		public double similarity;
		public String tag;
		
		public Candidate() {
			
		}
		
		public Candidate(String face_id, double similarity, String tag) {
			this.face_id = face_id;
			this.similarity = similarity;
			this.tag = tag;
		}
			
	}
}