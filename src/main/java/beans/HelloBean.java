package beans;

import java.io.Serializable;

/*import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped*/
public class HelloBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String message = "Hello World";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
