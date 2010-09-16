package beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import utils.FacesUtil;
import org.apache.log4j.Logger;

/**
 * Bean abastrakcyjny po którym dziedziczą inne managed bean'y
 * - dostęp do loga
 * - dostęp od klas DAO
 * - dostęp do FacesContext
 * ...
 * 
 * @author Mariusz Wróbel
 */
abstract public class BaseBean implements Serializable {

	/**
	 * dostep do sesji (bean sesyjny)
	 */
	public MySessionBean getSession() {
		return FacesUtil.findBean("mySessionBean", MySessionBean.class);
	}

	/**
	 * dostep do dao ksiazki
	 */
	public UserDAO getUserDao() {
		return (UserDAO) (FacesUtil.findDaoBean("userDAO"));
	}
	
	public ProductDAO getProductDao() {
		return (ProductDAO) (FacesUtil.findDaoBean("productDAO"));
	}

	/**
	 * dostep do loggera
	 * 
	 * @return
	 */
	public Logger getLog() {
		return Logger.getLogger(getClass());
	}

	/**
	 * dostep do kontekstu aplikacji
	 * 
	 * @return FacesContext
	 */
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * dostep do web aplikacji
	 * 
	 * @return web aplikacja
	 */
	public javax.faces.application.Application getApplication() {
		return getFacesContext().getApplication();
	}

	/**
	 * generacja Faces Message (globalnego) dla h:messages / rich:messages
	 */
	public void putMessage(FacesMessage.Severity severity, String summary, String details) {
		//getFacesContext().addMessage(FacesMessage.FACES_MESSAGES, new FacesMessage(severity, summary, details));
		getFacesContext().addMessage(null, new FacesMessage(severity, summary, details));
	}
	public void errorMessage(String summary, Throwable e) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, e.getMessage()));
	}
	public void errorMessage(String summary) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, ""));
	}
	public void fatalMessage(String summary) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, ""));
	}
	public void warnMessage(String summary) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, ""));
	}
	public void infoMessage(String summary) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, ""));
	}
	public void errorMessageFromBundle(String name) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getValueFromBundle("m", name), ""));
	}
	public void errorMessageFromBundle(String name, Throwable e) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getValueFromBundle("m", name), e.getMessage()));
	}	
	public void fatalMessageFromBundle(String name) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, FacesUtil.getValueFromBundle("m", name), ""));
	}
	public void warnMessageFromBundle(String name) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, FacesUtil.getValueFromBundle("m", name), ""));
	}
	public void infoMessageFromBundle(String name) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtil.getValueFromBundle("m", name), ""));
	}
	
	/**
	 * generacja Faces Message (dla konkretnego elementu) dla h:message / rich:message
	 */
	public void putMessage(String clientId, FacesMessage.Severity severity, String summary, String details) {
		getFacesContext().addMessage(clientId, new FacesMessage(severity, summary, details));
	}
	
	/**
	 * zwraca nazwe beana
	 * @return
	 */
	public String getBeanName() {
		return this.getClass().getName();
	}
	
	/**
	 * zwraca dao dla konkretnych encji
	 */
	public UserDAO getUserDAO() {
		return (UserDAO)FacesUtil.findDaoBean("userDAO");
	}
	public ProductDAO getProductDAO() {
		return (ProductDAO)FacesUtil.findDaoBean("productDAO");
	}

}
