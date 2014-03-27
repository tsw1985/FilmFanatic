package dad.filmfanatic.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtils {
	public static Date nuevaFecha(int dia, int mes, int anio) {
		return new GregorianCalendar(anio, mes - 1, dia).getTime();
	}
	
	public static int getDay(Date fecha){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	public static int getMonth(Date fecha){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		
		return calendar.get(Calendar.MONTH);
	}
	public static int getYear(Date fecha){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		
		return calendar.get(Calendar.YEAR);
	}
}
