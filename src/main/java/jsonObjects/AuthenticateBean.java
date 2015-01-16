package jsonObjects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticateBean {
	public boolean success;
	
	public AuthenticateBean() {
		
	}
	
	public AuthenticateBean(boolean success) {
		this.success = success;
	}
}
