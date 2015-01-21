package jsonObjects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecognitionSearchResult {
	public Star star;
	public String bestUrl;
	
	public RecognitionSearchResult() {
		
	}
	
	public RecognitionSearchResult(Star star, String bestUrl) {
		this.star = star;
		this.bestUrl = bestUrl;
	}
}
