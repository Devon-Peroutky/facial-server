package jsonObjects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyJaxBean {
	public String name;
	public int age;

	public MyJaxBean() {
	}

	// JAXB needs this
	public MyJaxBean(String name, int age) {
		this.name = name;
		this.age = age;
	}
}