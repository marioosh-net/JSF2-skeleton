package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

@ManagedBean
@RequestScoped
public class HelloBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private transient Logger log = Logger.getLogger(getClass());
	private String message = "Hello World";

	
	public HelloBean() {
		log.debug("HelloBean()");
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
