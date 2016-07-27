package txyd.util;


import java.io.InputStream;
import java.util.Properties;
public class PropertyUtil {
	private static String DEFAULT_CONFIG = "/jdbc.properties";
	private static Properties PROPERTIES;
	static {
		PROPERTIES = new Properties();
		try {
			Class<?> config_class = Class.forName(PropertyUtil.class.getName());
			InputStream is = config_class.getResourceAsStream(DEFAULT_CONFIG);
			PROPERTIES.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return PROPERTIES.getProperty(key);
	}
	
	public static String getProperty(String key, String defaultValue) {
		String value = PROPERTIES.getProperty(key);
		if (value == null)
			return defaultValue;
		
		return value;
	}
	public static void main(String[] args) {
		System.out.println(PropertyUtil.getProperty("htmlDirectory"));
	}
}
