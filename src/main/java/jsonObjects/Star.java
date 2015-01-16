package jsonObjects;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Star {
	public String name;
	public String bio;
	public String twitter;
	public String website;
	public String recentWork;
	public ArrayList<String> images;
	
	public Star() {
		
	}

	public Star(String name, String bio, String twitter, String website, String recentWork, ArrayList<String> images) {
		this.name = name;
		this.bio = bio;
		this.twitter = twitter;
		this.website = website;
		this.recentWork = recentWork;
		this.images = images;
	}
}
