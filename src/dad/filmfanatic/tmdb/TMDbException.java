package dad.filmfanatic.tmdb;

@SuppressWarnings("serial")
public class TMDbException extends Exception {
	
	public TMDbException(String message) {
		super(message);
	}
	
	public TMDbException(Exception e) {
		super(e);
	}

}
