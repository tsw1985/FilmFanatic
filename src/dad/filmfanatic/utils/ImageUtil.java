package dad.filmfanatic.utils;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class ImageUtil {
	
	public static ImageIcon getImageFromByteArray(byte[] byteArray , int width , int height ){
		
		try {
				ImageIcon icono = new ImageIcon(byteArray);
				Image img = icono.getImage();
			    Image newimg = img.getScaledInstance( width, height, java.awt.Image.SCALE_SMOOTH ) ;  
				ImageIcon icon = new ImageIcon( newimg );
				return icon;
			
		}catch ( Exception ex){
		}
		
		return null;
	}
	
	
	public static Image getImageAwtFromByteArray(byte[] byteArray , int width , int height ){
		
		try {
				ImageIcon icono = new ImageIcon(byteArray);
				Image img = icono.getImage();
			    Image newimg = img.getScaledInstance( width, height, java.awt.Image.SCALE_SMOOTH ) ;  
				ImageIcon icon = new ImageIcon( newimg );
				return icon.getImage();
			
		}catch ( Exception ex){
		}
		
		return null;
	}
	
	public static Image getIconToImage(Icon icon) {
		           if (icon instanceof ImageIcon) {
		               return ((ImageIcon)icon).getImage();
		           } else {
		               int w = icon.getIconWidth();
		               int h = icon.getIconHeight();
		               GraphicsEnvironment ge = 
		                 GraphicsEnvironment.getLocalGraphicsEnvironment();
		               GraphicsDevice gd = ge.getDefaultScreenDevice();
		               GraphicsConfiguration gc = gd.getDefaultConfiguration();
		               BufferedImage image = gc.createCompatibleImage(w, h);
		               Graphics2D g = image.createGraphics();
		               icon.paintIcon(null, g, 0, 0);
		               g.dispose();
		               return image;
		           }
		       }
	
	
	public static void createFileFromByteArray(String path , String imageName , byte[] byteArray){
		
		try{
			InputStream in = new ByteArrayInputStream(byteArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			
			File file = new File(path + imageName);
			if ( !file.exists() ){
				ImageIO.write(bImageFromConvert, "jpg", new File(path + imageName));
			}
			
		}
		catch(Exception  ex){
			System.out.println("Error al crear archivo de imagen " + ex.toString() );
		}
		
	}
	
	
	public static ImageIcon getImageFromFile(File file , int width , int height ){
		
		try {
				ImageIcon icono = new ImageIcon(file.getAbsolutePath());
				Image img = icono.getImage();
			    Image newimg = img.getScaledInstance( width, height, java.awt.Image.SCALE_SMOOTH ) ;  
				ImageIcon icon = new ImageIcon( newimg );
				return icon;
			
		}catch ( Exception ex){
		}
		
		return null;
	}
	
	
	public static byte[] getImageBytesFromURL(String urlString){
		try {
				URL url = new URL(urlString);
				BufferedImage image = ImageIO.read(url);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				//baos.flush();
				byte[] imageInBytes = baos.toByteArray();
				
				return imageInBytes;
			
		}catch (MalformedURLException ex){
			System.out.println("URL MAL FORMADA !" + ex.toString() );
			
		}catch ( IOException ex){
			System.out.println("IO EXCEPTION " + ex.toString());
		}
		
		return null;
	}
	
	
	public static ImageIcon resizeImage(ImageIcon imageIcon , int width, int height ){
		
		Image img = imageIcon.getImage();
	    Image newimg = img.getScaledInstance( width, height, java.awt.Image.SCALE_SMOOTH ) ;  
		ImageIcon icon = new ImageIcon( newimg );
		return icon;
		
	}
	
	
	
	public static byte[] getImageBytesFromIcon(Icon icon){
		
		try{
			
			if ( icon == null){
				icon = Iconos.IMAGE_NOT_FOUND;
			}
			
			BufferedImage bi = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] imageInBytes = baos.toByteArray();
			return imageInBytes;
			
		}catch (IOException ex){
			System.out.println("Error al obtener imagen from icon " + ex.getStackTrace());
			return null;
		}
	}
	
	
	public static ImageIcon getImageIconFromURL(String urlString){
		
		try {
				URL url = new URL(urlString);
				BufferedImage image = ImageIO.read(url);
				ImageIcon icono = new ImageIcon(image);
				return icono;
			
		}catch (MalformedURLException ex){
			System.out.println("URL MAL FORMADA !" + ex.toString() );
			
		}catch ( IOException ex){
			System.out.println("IO EXCEPTION " + ex.toString());
		}
		
		return null;
	}
	
	
	
	
	public static byte[] getByteArrayImageFromFile(File file){
		
		try {
			BufferedImage image = null ;
				
			if ( file == null ){
				image = ImageIO.read(new File("dad/filmfanatic/imagenotfound.jpeg"));
			}
			
				image = ImageIO.read(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				byte[] imageInBytes = baos.toByteArray();
				return imageInBytes;
				
			
		}catch (MalformedURLException ex){
			System.out.println("URL MAL FORMADA !" + ex.toString() );
			
		}catch ( IOException ex){
			System.out.println("IO EXCEPTION " + ex.toString());
		}
		
		return null;
	}

	
	
	
}
