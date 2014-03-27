package dad.filmfanatic.reports;

import java.awt.Image;

public class MovieReport {
	
	private String titulo;
	private String tituloOriginal;
	private String estreno;
	private String productora;
	private String generos;
	private String vista;
	private String productor;
	private String director;
	private String guionista;
	private String compositor;
	private String musica;
	private String paises;
	private String reparto;
	private String sinopsis;
	
	
	private Image poster;
	
	public MovieReport() {
	}
	
	

	public MovieReport(String titulo, String tituloOriginal, String estreno,
			String productora, String generos, String vista, String productor,
			String director, String guionista, String compositor,
			String musica, String paises, String reparto, String sinopsis,
			Image poster) {
		this.titulo = titulo;
		this.tituloOriginal = tituloOriginal;
		this.estreno = estreno;
		this.productora = productora;
		this.generos = generos;
		this.vista = vista;
		this.productor = productor;
		this.director = director;
		this.guionista = guionista;
		this.compositor = compositor;
		this.musica = musica;
		this.paises = paises;
		this.reparto = reparto;
		this.sinopsis = sinopsis;
		this.poster = poster;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTituloOriginal() {
		return tituloOriginal;
	}

	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}

	public String getEstreno() {
		return estreno;
	}

	public void setEstreno(String estreno) {
		this.estreno = estreno;
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	public String getGeneros() {
		return generos;
	}

	public void setGeneros(String generos) {
		this.generos = generos;
	}

	public String getVista() {
		return vista;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGuionista() {
		return guionista;
	}

	public void setGuionista(String guionista) {
		this.guionista = guionista;
	}

	public String getCompositor() {
		return compositor;
	}

	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}

	public Image getPoster() {
		return poster;
	}

	public void setPoster(Image poster) {
		this.poster = poster;
	}

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public String getPaises() {
		return paises;
	}

	public void setPaises(String paises) {
		this.paises = paises;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	
	
}
