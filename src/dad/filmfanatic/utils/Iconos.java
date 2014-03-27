package dad.filmfanatic.utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Iconos {
	
	public static final Icon SEARCH_MOVIE      = cargarIcono("buscarpelicula.png");
	public static final Icon ADD_MOVIE_FROM_PC = cargarIcono("movie_add.png");
	public static final Icon MOVIE_WATCHED     = cargarIcono("moviewatched.png");
	public static final Icon MOVIE_NOT_WATCHED = cargarIcono("movienowatched.png");
	public static final Icon WATCHED_MOVIE     = cargarIcono("visto.png");
	public static final Icon NO_WATCHED_MOVIE  = cargarIcono("novisto.png");
	public static final Icon STAR              = cargarIcono("star.png");
	public static final Icon DETAIL            = cargarIcono("detail.png");
	public static final Icon MY_MOVIES         = cargarIcono("mispeliculas.png");
	public static final Icon WAITING           = cargarIcono("waiting.png");
	public static final Icon ADD_MOVIE_GREEN_CIRCLE = cargarIcono("addgreencircle.png"); 
	public static final Icon IMAGE_NOT_FOUND   = cargarIcono("imagenotfound.jpeg");
	
	public static Icon cargarIcono(String nombre){
		return new ImageIcon(Iconos.class.getResource("/dad/filmfanatic/iconos/"+nombre));
	}

}
