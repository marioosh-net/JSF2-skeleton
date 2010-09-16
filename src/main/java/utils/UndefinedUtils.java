package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Użytki nie-faceowe
 * 
 * @author Mariusz Wróbel
 *
 */
public class UndefinedUtils {

	private static Logger log = Logger.getLogger(UndefinedUtils.class);
	
	/**
	 * Zwraca listę nazw pól klasy, którym odpowiada beanowa metoda getXXXX...()
	 * 
	 * @param className - nazwa klasy
	 * @return
	 */
	public static List<String> getBeanProperties(String className) {

		ArrayList<String> a = new ArrayList();
		try {
			Class c = Class.forName(className);
			//Field fields[] = c.getDeclaredFields();
			Method methods[] = c.getMethods();
			for (int i = 0; i < methods.length; i++) {
				String m = methods[i].getName();
				if(m.startsWith("get") && methods[i].getParameterTypes().length == 0) {
					String f = m.substring(3);
					char ch = f.charAt(0);
					char lower = Character.toLowerCase(ch);
					f = lower + f.substring(1);
					//log.debug(f);
					a.add(f);					
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return a;
	}
	
	private static SecureRandom randomString = new SecureRandom();

	public static String nextRandomString() {
		return new BigInteger(130, randomString).toString(32);
	}

}
