package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;

@ManagedBean
@RequestScoped
public class TestBean implements Serializable {
	private static final long serialVersionUID = 1990585830530945577L;
	private Logger log = Logger.getLogger(getClass());

	private String text;
	private String validatedText;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("ss:SS");
		return sdf.format(new Date());
	}

	public String testAction(int s) {
		log.debug("testAction(" + s + ")");
		return null;
	}

	public void testActionListener(ActionEvent event) {
		log.debug("testActionListener()");
	}
	
	public void fAjaxListener() {
		log.debug("fAjaxListener");
	}
	
	public String getValidatedText() {
		return validatedText;
	}
	
	public void setValidatedText(String validatedText) {
		this.validatedText = validatedText;
	}
}
