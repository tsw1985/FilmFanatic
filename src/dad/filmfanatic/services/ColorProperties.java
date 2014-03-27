package dad.filmfanatic.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ColorProperties {
	
	public static void saveColors(String section, int red, int green , int blue){
		
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream("color.properties");
	 
			// set the properties value
			prop.setProperty("section", section);
			prop.setProperty("red", String.valueOf(red ));
			prop.setProperty("green", String.valueOf(green));
			prop.setProperty("blue", String.valueOf(blue));
	 
			// save properties to project root folder
			prop.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
		
	}

}
