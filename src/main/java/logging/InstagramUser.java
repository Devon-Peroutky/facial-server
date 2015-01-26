package logging;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InstagramUser {
	public String id;
	
	public String profile_picture;
	
	@FormParam("username")
	public String username;
	
	public int follows;
	public String bio;
	public String website;
	public int media;
	public int followed_by;
	public String full_name;
	
	public InstagramUser() {
		
	}

	public InstagramUser(String id, String profile_picture, String username,
			int follows, String bio, String website, int media,
			int followed_by, String full_name) {
		super();
		this.id = id;
		this.profile_picture = profile_picture;
		this.username = username;
		this.follows = follows;
		this.bio = bio;
		this.website = website;
		this.media = media;
		this.followed_by = followed_by;
		this.full_name = full_name;
	}

	@Override
	public String toString() {
		return "InstagramUser [id=" + id + ", profile_picture="
				+ profile_picture + ", username=" + username + ", follows="
				+ follows + ", bio=" + bio + ", website=" + website
				+ ", media=" + media + ", followed_by=" + followed_by
				+ ", full_name=" + full_name + "]";
	}
	
	
}
