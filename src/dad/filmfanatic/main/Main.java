package dad.filmfanatic.main;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dad.filmfanatic.services.MovieDataBaseService;
import dad.filmfanatic.tmdb.TMDb;
import dad.filmfanatic.tmdb.TMDbException;
import dad.filmfanatic.window.MainWindow;

public class Main {
	
	public static void main(String[] args) throws TMDbException {
		
		  SwingUtilities.invokeLater(new Runnable(){  
	            
			  public void run(){  
				  
	            	try{
	            		
	            		MovieDataBaseService.openSession();
		            	TMDb tmdb = new TMDb(); 
		            	MainWindow mainWindow = new MainWindow(tmdb);
		            	mainWindow.setVisible(true);
		            	
	            	}catch ( Exception ex){
	            		MainWindow mainWindow = new MainWindow(null);
	            		message("");
	            		mainWindow.setVisible(true);
	            		
	            	}
	            }  
	        });  
	}
	
	private static void message(String ex){
		JOptionPane.showMessageDialog(null, "No es posible conectar con MovieDataBase ,  no podrá utilizar esta opción." + ex ,null, JOptionPane.INFORMATION_MESSAGE);
	}
	
}