package dad.filmfanatic.utils;

import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;

public class HelpUtil {
	
	private static HelpSet helpSet;
	private static HelpBroker helpBroker;
	
	public HelpUtil(){
		createHelpContent();
	}
	
	public  void createHelpContent() {
		
		try { 
			File fichero = new File("src/dad/filmfanatic/help/help.hs"); 
			URL hsURL = fichero.toURI().toURL();   
			helpSet = new HelpSet(getClass().getClassLoader(), hsURL); 

			helpBroker = helpSet.createHelpBroker();   
			
		} catch (Exception ex){ 
			System.out.println("Error en helpset " + ex.toString() );
		}
		
	}

	public static HelpSet getHelpSet() {
		return helpSet;
	}

	public static void setHelpSet(HelpSet helpSet) {
		HelpUtil.helpSet = helpSet;
	}

	public static HelpBroker getHelpBroker() {
		return helpBroker;
	}

	public static void setHelpBroker(HelpBroker helpBroker) {
		HelpUtil.helpBroker = helpBroker;
	}
}