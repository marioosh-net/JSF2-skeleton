package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.entities.Product;
import model.entities.User;
import utils.FacesUtil;
import utils.UndefinedUtils;

@ManagedBean
@RequestScoped
public class TestBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	
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

	public String testAction() {
		//log.debug("testAction(" + s + ")");
		return null;
	}

	public void testActionListener(ActionEvent event) {
		getLog().debug("testActionListener()");
	}
	
	public void fAjaxListener() {
		getLog().debug("fAjaxListener");
	}
	
	public String getValidatedText() {
		return validatedText;
	}
	
	public void setValidatedText(String validatedText) {
		this.validatedText = validatedText;
	}
	
	public List<User> getAllUsers() {
		return getUserDAO().findAll();
	}

	public List<Product> getAllProducts() {
		return getProductDAO().findAll();
	}

	public String addRandomUser() {
		getUserDAO().add(new User(UndefinedUtils.nextRandomString(), UndefinedUtils.nextRandomString()));
		return null;
	}

	public String addRandomProduct() {
		List<User> users = getUserDAO().findAll();
		if (users.size() > 0) {
			int max = users.size() - 1;
			Integer index = 0 + (int) (Math.random() * ((max - 0) + 1));
			User user = users.get(index);
			getProductDAO().add(new Product(UndefinedUtils.nextRandomString(), user));
		}
		return null;
	}

	public String deleteUser() {
		return null;
	}

	public void deleteUser(ActionEvent event) {
		Integer id = Integer.parseInt(FacesUtil.getRequestParameter("id"));
		getUserDAO().delete(getUserDAO().get(id));
	}

	public String deleteProduct() {
		return null;
	}

	public void deleteProduct(ActionEvent event) {
		Integer id = Integer.parseInt(FacesUtil.getRequestParameter("id"));
		getProductDAO().delete(getProductDAO().get(id));
	}

}
