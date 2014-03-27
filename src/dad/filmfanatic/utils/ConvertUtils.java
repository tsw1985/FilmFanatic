package dad.filmfanatic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtils {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static Date toDate(Object object) throws ParseException {
		if (object == null || "".equals(object)) return null;
		return DATE_FORMAT.parse(object.toString());
	}
	
	public static Integer toInt(Object object) {
		if (object == null || "".equals(object)) return null;
		return Integer.parseInt(object.toString());
	}
	
	public static Long floatToLong(Object object) {
		if (object == null || "".equals(object)) return null;
		
		Float numeroFloat = toFloat(object);
		return (long)numeroFloat.intValue();
	}
	
	public static Long toLong(Object object) {
		if (object == null || "".equals(object)) return null;
		
		return (long)Long.parseLong(object.toString());
	}
	

	public static Float toFloat(Object object) {
		if (object == null || "".equals(object)) return null;
		return Float.parseFloat(object.toString());
	}

	public static Double toDouble(Object object) {
		if (object == null || "".equals(object)) return null;
		return Double.parseDouble(object.toString());
	}

}