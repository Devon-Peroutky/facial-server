package jsonObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResponse {
	public List<Candidate> candidate;
	public String session_id;
	private boolean isSorted = false;
	
	
	public double getAvgMatch() {
		double total = 0;
		int count = 0;
		for (Candidate c : this.candidate) {
			total += c.similarity;
			count++;
		}
		return total / count;
	}
	
	public double getMedianMatch() {
		if (!isSorted) {
			Collections.sort(candidate);
			isSorted = true;
		}
		return candidate.get(candidate.size() / 2).similarity;
	}
	
	public static class Candidate implements Comparable<Candidate> {
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

		@Override
		public int compareTo(Candidate o) {
			// TODO Auto-generated method stub
			return (int)(this.similarity * 1000 - o.similarity*1000);
		}

			
	}
}