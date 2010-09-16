package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean sesyjny. 
 * 
 * @author Mariusz Wróbel
 * 
 */
@ManagedBean
@SessionScoped
public class MySessionBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	public MySessionBean() {
		getLog().debug("MySessionBean()");
	}
	
	public int getHashCode() {
		return hashCode();
	}	

}
