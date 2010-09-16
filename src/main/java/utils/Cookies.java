package utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Klasa użytkowa do obsługi ciasteczek
 * 
 * @author Mariusz Wróbel
 */
public class Cookies {
	
	/**
	 * Pobierz wartość ciasteczka o podanej nazwie
	 * @param cookieName
	 * @return wartość ciasteczka
	 */
	public static String getCookieValue(String cookieName) {
		Cookie[] cookies = getCookies();
		String value = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equalsIgnoreCase(cookieName)) {
					value = cookies[i].getValue();
				}
			}
		}
		return value;
	}
	
	/**
	 * Pobierz obiekt ciasteczka o podanej nazwie 
	 * @param cookieName
	 * @return ciasteczko
	 */
	public static Cookie getCookie(String cookieName) {
		Cookie[] cookies = getCookies();
		Cookie cookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equalsIgnoreCase(cookieName)) {
					cookie = cookies[i];
				}
			}
		}
		return cookie;
	}
	
	/**
	 * Daj mi wszystkie ciasteczka z requesta
	 * 
	 * @return tablica ciasteczek
	 */
	public static Cookie[] getCookies() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = ((HttpServletRequest) context.getExternalContext().getRequest());
		return request.getCookies();
	}
	
	/**
	 * Usuń ciasteczko o podanej nazwie 
	 * @param cookieName - nazwa ciasteczka
	 */
	public static void removeCookie(String cookieName) {
		FacesContext context = FacesContext.getCurrentInstance();		
		HttpServletResponse response = ((HttpServletResponse)context.getExternalContext().getResponse());
		//Cookie cookie = getCookie(cookieName);	// tak nie dziala
		Cookie cookie = new Cookie(cookieName,"");
		cookie.setMaxAge(0);
		cookie.setValue("");
		cookie.setPath(context.getExternalContext().getRequestContextPath());
		response.addCookie(cookie);
	}
	
	/**
	 * Zachowaj ciasteczko
	 * 
	 * @param cookieName - nazwa
	 * @param cookieValue - wartość
	 * @param expiry - ekspiracja ciasteczka
	 */
	public static void setCookie(String cookieName, String cookieValue, int expiry) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = ((HttpServletResponse)context.getExternalContext().getResponse());
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(expiry);
		cookie.setPath(context.getExternalContext().getRequestContextPath()); 
		response.addCookie(cookie);
	}
}
