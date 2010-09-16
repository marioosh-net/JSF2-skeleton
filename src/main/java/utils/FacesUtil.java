package utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import beans.MySessionBean;
import javax.servlet.ServletContext;

/**
 * Główna klasa użytkowa JSF
 * 
 * @author Mariusz Wróbel
 *
 */
public class FacesUtil {

	/**
	 * znajdz managed beana
	 * 
	 * @param <T>
	 * @param managedBeanName
	 * @param beanClass
	 * @return
	 */
	
	private static Logger log = Logger.getLogger(FacesUtil.class);
	
	public static <T> T findBean(String managedBeanName, Class<T> beanClass) {
		FacesContext context = FacesContext.getCurrentInstance();
		return beanClass.cast(context.getApplication().evaluateExpressionGet(context, "#{" + managedBeanName + "}", beanClass));
	}

	public String getMD5(String pass) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			byte[] data = pass.getBytes();
			m.update(data, 0, data.length);
			BigInteger i = new BigInteger(1, m.digest());
			String md5sum = String.format("%1$032X", i).toLowerCase();
			System.out.println(md5sum);
			return md5sum;
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}

	public static String getMessageValue(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getResourceBundle(context, "m").getString(name);

	}

	/**
	 * Pobiera wartość o nazwie name z zasobu o nazwie bundle
	 * 
	 * @param bundle
	 *            - nazwa bundla zdeklarowana w faces-config.xml
	 * @param name
	 *            - nazwa/klucz
	 * @return wartość klucza
	 */
	public static String getValueFromBundle(String bundle, String name) {

		FacesContext fc = FacesContext.getCurrentInstance();
		Application ap = fc.getApplication();
		ResourceBundle rb = ap.getResourceBundle(fc, bundle);

		if (rb != null) {
			String value = rb.getString(name);
			return value;
		} else {
			return "";
		}
	}

	/**
	 * zwraca InputStream do wybranego zasobu np. /images/x.png lub
	 * /WEB-INF/....
	 * zwraca null, jak zasob nie istnieje
	 * 
	 * @param path
	 *            - sciezka do zasobu
	 * @return InputStream
	 */
	public static InputStream getResourceInputStream(String path) {
		FacesContext context = FacesContext.getCurrentInstance();
		javax.faces.context.ExternalContext external = context.getExternalContext();
		return external.getResourceAsStream(path);
	}

	/**
	 * Zwraca name z ResourceBundle o nazwie bundle, który ma wartość value.
	 * Zwraca pierwszy znaleziony wpis, bo nie wartości nie są unikalne
	 * 
	 * @param bundle
	 *            - skrót do bundla
	 * @param value
	 *            - klucz/name który chcemy pobrać
	 * @return wartość klucza/name'a
	 */
	public static String getNameFromBundle(String bundle, String value) {

		FacesContext fc = FacesContext.getCurrentInstance();
		Application ap = fc.getApplication();
		ResourceBundle rb = ap.getResourceBundle(fc, bundle);
		for (String key : rb.keySet()) {
			if (rb.getString(key).equals(value)) {
				return key;
			}
		}
		return null;
	}

	/**
	 * znajdz bean hibernatowego / springowego
	 * 
	 * @param beanName
	 *            - nazwa beana
	 * @return
	 */
	public static Object findDaoBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return appContext.getBean(beanName);
	}

	/**
	 * szuka komponentu face'owego po jego id
	 * 
	 * @param context
	 *            - kontekst
	 * @param clientId
	 *            - id komponentu
	 */
	public static void findComponent(FacesContext context, String clientId) {
		context.getViewRoot().invokeOnComponent(context, clientId, new ContextCallback() {

			@Override
			public void invokeContextCallback(FacesContext facescontext,
					UIComponent uicomponent) {
				Logger log = Logger.getLogger(getClass());
				log.debug("FOUND COMP.");
			}

		});
	}

	/**
	 * pobiera zmienną sesyjną o nazwie key
	 * 
	 * @param key
	 *            - nazwa zmiennej sesyjnej
	 * @return wartość zmiennej sesyjnej
	 */
	public static Object getSessionMapValue(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	/**
	 * ustawia zmienną sesyjną o nazwie key na wartość value
	 * 
	 * @param key
	 *            - nazwa zmiennej sesji
	 * @param value
	 *            - wartość zmiennej sesji
	 */
	public static void setSessionMapValue(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	/**
	 * usuwa zmienną sesyjną o podanej nazwie
	 * 
	 * @param key
	 *            - nazwa zmiennej sesyjnej
	 */
	public static void removeSessionMapValue(String key) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	/**
	 * zwraca wartość parametru przekazywanego w request-cie
	 * przez <f:param> do beana
	 * 
	 * @param name
	 * @return
	 */
	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

	/**
	 * zwraca wartosc atrybutu przekazywanego do actionListenera przez uzycie <f:attribute/>
	 * @param event
	 * @param name
	 * @return
	 */
	public static Object getActionAttribute(ActionEvent event, String name) {
		return (Object) event.getComponent().getAttributes().get(name);
	}
	
	/**
	 * przekierwuje na podanego url'a
	 * @param url
	 * @throws IOException
	 */
	public static void redirectToUrl(String url) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext extContext = context.getExternalContext();
		try {
			extContext.redirect(extContext.encodeActionURL(extContext.getRequestContextPath() + url));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	/**
	 * zwraca captchaId do validacji captcha przy rejestracji konta 
	 * @return
	 */
	public static String getCaptchaId() {
		MySessionBean b = findBean("mySessionBean", MySessionBean.class);
		return Integer.toString(b.getHashCode()); 
		//return "alamakota";
	}
}
