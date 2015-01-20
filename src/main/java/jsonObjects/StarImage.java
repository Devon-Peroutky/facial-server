package jsonObjects;

public class StarImage {
	public String name;
	public String url;
	public String faceId;
	
	public StarImage() {
		
	}

	public StarImage(String name, String url, String faceId) {
		super();
		this.name = name;
		this.url = url;
		this.faceId = faceId;
	}

	@Override
	public String toString() {
		return "StarImage [name=" + name + ", url=" + url + ", faceId="
				+ faceId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		StarImage other = (StarImage) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	
	
}
