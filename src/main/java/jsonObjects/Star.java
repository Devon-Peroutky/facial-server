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

	@Override
	public String toString() {
		return "Star [name=" + name + ", bio=" + bio + ", twitter=" + twitter
				+ ", website=" + website + ", recentWork=" + recentWork
				+ ", images=" + images + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Star other = (Star) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
