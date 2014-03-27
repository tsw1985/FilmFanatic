package dad.filmfanatic.window;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import net.sf.jasperreports.engine.JRException;
import dad.filmfanatic.dialog.ColorsDialog;
import dad.filmfanatic.dialog.UniqueMovieReportDialog;
import dad.filmfanatic.panel.AddEditFromPCMoviePanel;
import dad.filmfanatic.panel.SearchMoviePcOrCloudPanel;
import dad.filmfanatic.reports.MovieMain;
import dad.filmfanatic.services.MovieDataBaseService;
import dad.filmfanatic.tmdb.TMDb;
import dad.filmfanatic.tmdb.TMDbException;
import dad.filmfanatic.utils.Iconos;

public class MainWindow extends JFrame{
	
	public static final String SEARCH_MOVIE = "SEARCHMOVIE";
	public static final String ADD_MOVIE    = "ADDMOVIE";
	
	public static Color colorParaMisPeliculas      = new Color(0,0,255); //azul
	public static Color colorParaMovieDataBase     = new Color(255,0,0);
	
	public static Color colorParaNuevaPelicula     = new Color( 30 , 159 , 30 );
	public static Color colorParaModificarPelicula = new Color( 125 , 0 , 255 );
	
	
	private Color colorOriginalNuevaPelicula     = colorParaNuevaPelicula;
	private Color colorOriginalMisPeliculas      = colorParaMisPeliculas;
	private Color colorOriginalModificarPelicula = colorParaModificarPelicula;
	private Color colorOriginalBuscarDataBase    = colorParaMovieDataBase;
	
	
	
	private static Color backGroundColor;
	
	private JMenuBar menubar;
	private JToolBar barraHerramientasToolBar;
	
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	
	private JMenu reportMenu;
	private JMenuItem listReportMenuItem;
	private JMenuItem mosaicReportMenuItem;
	private JMenuItem movieDetailReportMenuItem;
	
	
	private JMenu configurationProgramMenu;
	private JMenuItem customColorsMenuItem;
	
	private JPanel panelContainerCardLayout;
	
	private TMDb tmbd;
	private CardLayout cl;

	private AddEditFromPCMoviePanel addEditFromPCMoviePanel;
	private SearchMoviePcOrCloudPanel searchMoviePcOrCloudPanel;
	private JButton myMoviesButton;
	private JButton addMovieFromPCButton;
	private JButton findMoviesButton;
	private JMenu helpProgramMenu;
	private JMenuItem helpMenuItem;
	
	
	public MainWindow(TMDb tmdb) {
		this.tmbd = tmdb;
		initColors();
		
		initFrame();
		initComponent();
		initMovieGenres();
		
		searchMoviePcOrCloudPanel.setMyMovies(true);
		searchMoviePcOrCloudPanel.getTitleSearchLabel().setText("MIS PELÍCULAS");
		searchMoviePcOrCloudPanel.getAddMovieButton().setVisible(false);
		
		goToMyMovies();
		
	}
	
	private void goToMyMovies(){
		searchMoviePcOrCloudPanel.getTitleHeaderPanel().setBackground(colorParaMisPeliculas);
		cl = (CardLayout)(panelContainerCardLayout.getLayout());
        cl.show(panelContainerCardLayout, SEARCH_MOVIE);
	}
	


	private void initColors() {
		
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("colors.properties");
			if ( input != null ){

					prop.load(input);
			 
					String[] coloresParaNuevaPelicula = prop.getProperty("panelNuevaPelicula").split("-");
					String[] coloresParaModificarPelicula = prop.getProperty("panelModificarPelicula").split("-");
					
					String[] coloresParaMisPeliculas  = prop.getProperty("panelMisPeliculas").split("-");
					String[] coloresParaMovieDataBase = prop.getProperty("panelMovieDataBase").split("-");
					
		
					colorParaNuevaPelicula     = new Color( Integer.valueOf(coloresParaNuevaPelicula[0]).intValue() , 
															Integer.valueOf(coloresParaNuevaPelicula[1]).intValue() , 
															Integer.valueOf(coloresParaNuevaPelicula[2]).intValue()  );
					
					colorParaModificarPelicula = new Color( Integer.valueOf(coloresParaModificarPelicula[0]).intValue() ,
															Integer.valueOf(coloresParaModificarPelicula[1]).intValue() ,
															Integer.valueOf(coloresParaModificarPelicula[2]).intValue());
					
					colorParaMisPeliculas      = new Color( Integer.valueOf(coloresParaMisPeliculas[0]).intValue() ,
															Integer.valueOf(coloresParaMisPeliculas[1]).intValue() ,
															Integer.valueOf(coloresParaMisPeliculas[2]).intValue()); //azul
					
					colorParaMovieDataBase     = new Color( Integer.valueOf(coloresParaMovieDataBase[0]).intValue() ,
															Integer.valueOf(coloresParaMovieDataBase[1]).intValue() ,
															Integer.valueOf(coloresParaMovieDataBase[2]).intValue() );
					
			}
			
	 
		} catch (IOException ex) {
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {

				}
			}
		}
		
	}


	private void initMovieGenres() {
		try{
			if ( tmbd != null){
			MovieDataBaseService.getGenreModelService().initMovieGenres(tmbd.getGenres());
		}}
		catch (TMDbException ex){
			System.out.println("Error al crear los géneros");
		}
	}


	private void initFrame() {
		setTitle("FilmFanatic - Menú Principal");
		setSize(1010,758);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);	
	}

	private void initComponent() {
		
		backGroundColor = new Color(0);
		
		menubar = new JMenuBar();
		
		createFileMenuBar();
		createConfigurationMenuBar();
		createReportMenuBar();
		createHelpMenuBar();
		createHelpContent();
		
		setJMenuBar(menubar);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		barraHerramientasToolBar = new JToolBar();
		barraHerramientasToolBar.setBorderPainted(false);
		barraHerramientasToolBar.setRollover(false);
		barraHerramientasToolBar.setFloatable(false);
		
		
		addMovieFromPCButton = new JButton("");
		addMovieFromPCButton.setHorizontalTextPosition(JButton.CENTER);
		addMovieFromPCButton.setVerticalTextPosition(JButton.BOTTOM);
		addMovieFromPCButton.setBorderPainted(false);
		addMovieFromPCButton.setIcon( Iconos.ADD_MOVIE_FROM_PC );
		
		addMovieFromPCButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAddMovieFromPcActionPerformed(e);
			}
		});
		
				
				myMoviesButton = new JButton("");
				myMoviesButton.setFont(new Font( myMoviesButton.getName() , Font.BOLD, myMoviesButton.getFont().getSize() ));
				myMoviesButton.setBorderPainted(false);
				myMoviesButton.setHorizontalTextPosition(JButton.CENTER);
				myMoviesButton.setVerticalTextPosition(JButton.BOTTOM);
				myMoviesButton.setIcon( Iconos.MY_MOVIES );
				
				myMoviesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onMyMoviesActionPerformed(e);
					}
				});
				
				barraHerramientasToolBar.add(myMoviesButton);

		barraHerramientasToolBar.add(addMovieFromPCButton);
		getContentPane().add(barraHerramientasToolBar, BorderLayout.NORTH);
		
		findMoviesButton = new JButton("");
		findMoviesButton.setFont(new Font( findMoviesButton.getName() , Font.BOLD, findMoviesButton.getFont().getSize() ));
		findMoviesButton.setBorderPainted(false);
		findMoviesButton.setHorizontalTextPosition(JButton.CENTER);
		findMoviesButton.setVerticalTextPosition(JButton.BOTTOM);
		findMoviesButton.setIcon( Iconos.SEARCH_MOVIE );
		findMoviesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSearchMoviesActionPerformed(e);
			}
		});
		
		barraHerramientasToolBar.add(findMoviesButton);
		addMovieFromPCButton.setFont(new Font( findMoviesButton.getName() , Font.BOLD, addMovieFromPCButton.getFont().getSize() ));
		
		panelContainerCardLayout = new JPanel();
		//panelContainerCardLayout.setBackground(bg);
		getContentPane().add(panelContainerCardLayout, BorderLayout.CENTER);
		panelContainerCardLayout.setLayout(new CardLayout(0, 0));
		
		addEditFromPCMoviePanel = new AddEditFromPCMoviePanel(tmbd , this);
		addEditFromPCMoviePanel.setBackground(backGroundColor);
		
		panelContainerCardLayout.add(addEditFromPCMoviePanel , ADD_MOVIE );
		
		searchMoviePcOrCloudPanel = new SearchMoviePcOrCloudPanel(tmbd , this );
		panelContainerCardLayout.add(searchMoviePcOrCloudPanel, SEARCH_MOVIE );
		
	}
	

	private void createHelpContent() {
		 try { 
				File fichero = new File("src/dad/filmfanatic/help/help.hs"); 
				URL hsURL = fichero.toURI().toURL();   
				HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL); 

				HelpBroker helpBroker = helpSet.createHelpBroker();   
				helpBroker.enableHelpOnButton( helpMenuItem , "manual", helpSet); 
				
			} catch (Exception ex){ 
				System.out.println("Error en helpset " + ex.toString() );
			}
		
	}


	protected void helpProgramActionPerformed(ActionEvent e) {
	}
		
	private void createConfigurationMenuBar() {
		
		configurationProgramMenu = new JMenu("Personalizar");
		customColorsMenuItem = new JMenuItem("Cambiar colores del programa");		
		customColorsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseColorsActionPerformed(e);
			}
		});
		configurationProgramMenu.add(customColorsMenuItem);
		
		menubar.add( fileMenu );
		menubar.add( configurationProgramMenu );
	}


	protected void chooseColorsActionPerformed(ActionEvent e) {
		
		final ColorsDialog colors = new ColorsDialog(this);
		
		colors.getGuardarColores().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateColorsActionPerformed(e);
				colors.dispose();
			}
		});
		
		
		colors.getCerrarDialogo().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MainWindow.colorParaNuevaPelicula     = colorOriginalNuevaPelicula; 
				MainWindow.colorParaMisPeliculas      = colorOriginalMisPeliculas;
				MainWindow.colorParaModificarPelicula = colorOriginalModificarPelicula;
				MainWindow.colorParaMovieDataBase     = colorOriginalBuscarDataBase ; 
				
				String textoCabecera = addEditFromPCMoviePanel.getTitleActionInMovieLabel().getText();
				String textoCabeceraSearch = searchMoviePcOrCloudPanel.getTitleSearchLabel().getText();
				
				
				if (textoCabecera.equals("AÑADIR PELÍCULA")){
					addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().setBackground(MainWindow.colorParaNuevaPelicula);
					addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().updateUI();
					addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().repaint();
				}
				
				else if (textoCabecera.equals("MODIFICAR PELÍCULA")){
					addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().setBackground(MainWindow.colorParaModificarPelicula);
					addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().updateUI();
					addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().repaint();
				}
				
				
				if ( textoCabeceraSearch.equals("MIS PELÍCULAS")){
					searchMoviePcOrCloudPanel.getTitleHeaderPanel().setBackground(MainWindow.colorParaMisPeliculas);
					searchMoviePcOrCloudPanel.getTitleHeaderPanel().updateUI();
					searchMoviePcOrCloudPanel.getTitleHeaderPanel().repaint();
					
				}
				else if ( textoCabeceraSearch.equals("AÑADIR DESDE MOVIE DATA BASE")){
					searchMoviePcOrCloudPanel.getTitleHeaderPanel().setBackground(MainWindow.colorParaMovieDataBase);
					searchMoviePcOrCloudPanel.getTitleHeaderPanel().updateUI();
					searchMoviePcOrCloudPanel.getTitleHeaderPanel().repaint();
				}
				
				colors.dispose();
				
			}
		});
		
		colors.setVisible(true);
		
	}


	protected void updateColorsActionPerformed(ActionEvent e) {

		String textoCabecera = addEditFromPCMoviePanel.getTitleActionInMovieLabel().getText();
		String textoCabeceraSearch = searchMoviePcOrCloudPanel.getTitleSearchLabel().getText();
		
		System.out.println("color -> " + MainWindow.colorParaMisPeliculas.toString());
		
		System.out.println("Texto cabecera -> " + textoCabecera );
		System.out.println("Texto cabecera searcg-> " + textoCabeceraSearch );
		
		if (textoCabecera.equals("AÑADIR PELÍCULA")){
			addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().setBackground(MainWindow.colorParaNuevaPelicula);
			addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().updateUI();
			addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().repaint();
		}
		
		else if (textoCabecera.equals("MODIFICAR PELÍCULA")){
			addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().setBackground(MainWindow.colorParaModificarPelicula);
			addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().updateUI();
			addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().repaint();
		}
		
		
		if ( textoCabeceraSearch.equals("MIS PELÍCULAS")){
			searchMoviePcOrCloudPanel.getTitleHeaderPanel().setBackground(MainWindow.colorParaMisPeliculas);
			searchMoviePcOrCloudPanel.getTitleHeaderPanel().updateUI();
			searchMoviePcOrCloudPanel.getTitleHeaderPanel().repaint();
			
		}
		else if ( textoCabeceraSearch.equals("AÑADIR DESDE MOVIE DATA BASE")){
			searchMoviePcOrCloudPanel.getTitleHeaderPanel().setBackground(MainWindow.colorParaMovieDataBase);
			searchMoviePcOrCloudPanel.getTitleHeaderPanel().updateUI();
			searchMoviePcOrCloudPanel.getTitleHeaderPanel().repaint();
		}
		
		
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream("colors.properties");
	 
			String rojoNuevaPelicula  = String.valueOf(MainWindow.colorParaNuevaPelicula.getRed());
			String verdeNuevaPelicula = String.valueOf(MainWindow.colorParaNuevaPelicula.getGreen());
			String blueNuevaPelicula  = String.valueOf(MainWindow.colorParaNuevaPelicula.getBlue());
			
			String rojoModificarPelicula  = String.valueOf(MainWindow.colorParaModificarPelicula.getRed());
			String verdeModificarPelicula = String.valueOf(MainWindow.colorParaModificarPelicula.getGreen());
			String blueModificarPelicula  = String.valueOf(MainWindow.colorParaModificarPelicula.getBlue());

			String rojoMisPelicula  = String.valueOf(MainWindow.colorParaMisPeliculas.getRed());
			String verdeMisPelicula = String.valueOf(MainWindow.colorParaMisPeliculas.getGreen());
			String blueMisPelicula  = String.valueOf(MainWindow.colorParaMisPeliculas.getBlue());

			String rojoMovieDataBasePelicula  = String.valueOf(MainWindow.colorParaMovieDataBase.getRed());
			String verdeMovieDataBasePelicula = String.valueOf(MainWindow.colorParaMovieDataBase.getGreen());
			String blueMovieDataBasePelicula  = String.valueOf(MainWindow.colorParaMovieDataBase.getBlue());
			
			
			
			
			prop.setProperty("panelNuevaPelicula", rojoNuevaPelicula + "-" + verdeNuevaPelicula + "-" + blueNuevaPelicula);
			prop.setProperty("panelModificarPelicula", rojoModificarPelicula + "-" + verdeModificarPelicula + "-" + blueModificarPelicula);
			
			prop.setProperty("panelMisPeliculas" , rojoMisPelicula + "-" + verdeMisPelicula + "-" + blueMisPelicula);
			prop.setProperty("panelMovieDataBase" , rojoMovieDataBasePelicula + "-" + verdeMovieDataBasePelicula + "-" + blueMovieDataBasePelicula);
	 
			prop.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
	 
		}
		
	}


	private void createFileMenuBar() {
		
		fileMenu = new JMenu("Archivo");
		exitMenuItem= new JMenuItem("Salir");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		fileMenu.add(exitMenuItem);
	}

	
	private void createReportMenuBar(){
		reportMenu = new JMenu("Informes");
		listReportMenuItem = new JMenuItem("Ver informe como listado de todas las películas");
		listReportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onListReportActionPerformed(e);
			}
		});
		mosaicReportMenuItem = new JMenuItem("Ver informe como mosaico de todas las peliculas");
		mosaicReportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onMosaicReportActionPerformed(e);
			}
		});
		movieDetailReportMenuItem = new JMenuItem("Ver informe una película");
		movieDetailReportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOneMovieReportActionPerformed(e);
			}
		});

		reportMenu.add(listReportMenuItem);
		reportMenu.add(mosaicReportMenuItem);
		reportMenu.add(movieDetailReportMenuItem);
	
		menubar.add(reportMenu);
	
	}
	
	private void createHelpMenuBar() {
		
		helpProgramMenu = new JMenu("Ayuda");
		helpMenuItem = new JMenuItem("Ayuda del programa");		
		helpMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				helpProgramActionPerformed(e);
			}
		});
		configurationProgramMenu.add(customColorsMenuItem);
		
		helpProgramMenu.add(helpMenuItem);
		menubar.add( helpProgramMenu );
		
	}
	
	protected void onOneMovieReportActionPerformed(ActionEvent e) {
		UniqueMovieReportDialog unique = new UniqueMovieReportDialog();
		unique.setVisible(true);
	}


	protected void onMosaicReportActionPerformed(ActionEvent e) {
		
		try {
			MovieMain.reportLikeMosaic();
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}


	protected void onListReportActionPerformed(ActionEvent e) {

		try {
			MovieMain.reportLikeList();
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}


	protected void onMyMoviesActionPerformed(ActionEvent e) {
		
		searchMoviePcOrCloudPanel.getTitleHeaderPanel().setBackground(colorParaMisPeliculas);
		searchMoviePcOrCloudPanel.getTitleHeaderPanel().repaint();
		searchMoviePcOrCloudPanel.getRowPagePanel().setVisible(false);
		searchMoviePcOrCloudPanel.getLikeDetailPanel().removeAll();
		searchMoviePcOrCloudPanel.getLikeMosaicPanel().removeAll();
		searchMoviePcOrCloudPanel.getSearchMovieTitleTextField().setText("");
		searchMoviePcOrCloudPanel.getEditMovieButton().setVisible(true);
		searchMoviePcOrCloudPanel.getDeleteMyMovieButton().setVisible(true);
		searchMoviePcOrCloudPanel.getWatchedRadio().setVisible(false);
		searchMoviePcOrCloudPanel.getNotWatchedRadio().setVisible(false);
		searchMoviePcOrCloudPanel.getListTableModelMovie().getValues().clear();
		searchMoviePcOrCloudPanel.getTableResultmovieModel().updateUI();
		searchMoviePcOrCloudPanel.getWatchedRadio().setVisible(true);
		searchMoviePcOrCloudPanel.getNotWatchedRadio().setVisible(true);
		searchMoviePcOrCloudPanel.getLikeDetailPanel().removeAll();
		searchMoviePcOrCloudPanel.getLikeMosaicPanel().removeAll();
		searchMoviePcOrCloudPanel.setMyMovies(true);
		searchMoviePcOrCloudPanel.getTitleSearchLabel().setText("MIS PELÍCULAS");
		searchMoviePcOrCloudPanel.getAddMovieButton().setVisible(false);
		searchMoviePcOrCloudPanel.goToMyMovies();
		searchMoviePcOrCloudPanel.getSearchMovieTitleTextField().requestFocus();
	       
	       
	       try { 
				File fichero = new File("src/dad/filmfanatic/help/help.hs"); 
				URL hsURL = fichero.toURI().toURL();   
				HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL); 

				HelpBroker helpBroker = helpSet.createHelpBroker();   
				helpBroker.enableHelpKey( searchMoviePcOrCloudPanel , "mispeliculas", helpSet); 
				
			} catch (Exception ex){ 
				System.out.println("Error en helpset " + ex.toString() );
			}
	}

	//Añadir manual buton
	protected void onAddMovieFromPcActionPerformed(ActionEvent e) {
		addEditFromPCMoviePanel.getTitleActionInMovieLabel().setText("AÑADIR PELÍCULA");
		addEditFromPCMoviePanel.getHeaderTitleAddMoviePanel().setBackground(colorParaNuevaPelicula);
        
        emptyFields();
		cl = (CardLayout)(panelContainerCardLayout.getLayout());
        cl.show(panelContainerCardLayout,  ADD_MOVIE);
        
        addEditFromPCMoviePanel.getTitleNewMovieGtextField().requestFocus();
        
        try { 
			File fichero = new File("src/dad/filmfanatic/help/help.hs"); 
			URL hsURL = fichero.toURI().toURL();   
			HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL); 

			HelpBroker helpBroker = helpSet.createHelpBroker();   
			helpBroker.enableHelpKey( addEditFromPCMoviePanel , "anadir", helpSet); 
			
		} catch (Exception ex){ 
			System.out.println("Error en helpset " + ex.toString() );
		}
        
	}

	private void emptyFields() {
		
		addEditFromPCMoviePanel.getTitleNewMovieGtextField().setText("");
		addEditFromPCMoviePanel.getReleaseDateNewMovie().setFecha(new Date());
		addEditFromPCMoviePanel.getSinopsisNewMovieTextArea().setText("");
		addEditFromPCMoviePanel.getOriginalTitleNewMovieGTextField().setText("");
		addEditFromPCMoviePanel.getLemaNewMovieTextField().setText("");
		addEditFromPCMoviePanel.getStartsPanel().setPoints(0L);
		addEditFromPCMoviePanel.getImagePosterLabel().setIcon(null);

		addEditFromPCMoviePanel.getYesRadio().setSelected(true);
		
		addEditFromPCMoviePanel.getCastCastPeoplePanel().getListTableModel().getValues().clear();
		addEditFromPCMoviePanel.getCastCrewPeoplePanel().getListTableModel().getValues().clear();
		
		addEditFromPCMoviePanel.getCastCastPeoplePanel().repaint();
		addEditFromPCMoviePanel.getCastCrewPeoplePanel().repaint();
		
		//GenreList
		addEditFromPCMoviePanel.getGenresDataPanel().getDataJListModel().clear();
		
		//LanguageItem
		addEditFromPCMoviePanel.getLanguageDataPanel().getDataJListModel().clear();
		
		//Company
		addEditFromPCMoviePanel.getCompanyDataPanel().getDataJListModel().clear();
		
		//CountryItem
		addEditFromPCMoviePanel.getCountryDataPanel().getDataJListModel().clear();
		

		
	}

	//Boton añadir desde moviedatabase
	protected void onSearchMoviesActionPerformed(ActionEvent e) {
		
		//pinto fondo de cabecera
		searchMoviePcOrCloudPanel.getTitleHeaderPanel().setBackground(colorParaMovieDataBase);
		
		searchMoviePcOrCloudPanel.getRowPagePanel().setVisible(true);
		searchMoviePcOrCloudPanel.getNextPageButton().setEnabled(false);
		
		//vacio panales mosaico y alargado y vacio caja texto
		searchMoviePcOrCloudPanel.getLikeDetailPanel().removeAll();
		searchMoviePcOrCloudPanel.getLikeMosaicPanel().removeAll();
		searchMoviePcOrCloudPanel.getSearchMovieTitleTextField().setText("");
		
		//oculto botones pequeños
		searchMoviePcOrCloudPanel.getEditMovieButton().setVisible(false);
		searchMoviePcOrCloudPanel.getDeleteMyMovieButton().setVisible(false);
		searchMoviePcOrCloudPanel.getWatchedRadio().setVisible(false);
		searchMoviePcOrCloudPanel.getNotWatchedRadio().setVisible(false);

		//vacio tablas
		searchMoviePcOrCloudPanel.getListTableModelMovie().getValues().clear();
		searchMoviePcOrCloudPanel.getTableResultmovieModel().updateUI();
		
		//vacio mosaicos
		searchMoviePcOrCloudPanel.getLikeDetailPanel().removeAll();
	
		//	vacio paneles largos
		searchMoviePcOrCloudPanel.getLikeMosaicPanel().removeAll();

		//pongo a true que es mis peliculas
		searchMoviePcOrCloudPanel.setMyMovies(false);
		searchMoviePcOrCloudPanel.getTitleSearchLabel().setText("AÑADIR DESDE MOVIE DATA BASE");
		searchMoviePcOrCloudPanel.getAddMovieButton().setVisible(true);
		searchMoviePcOrCloudPanel.getRowPagePanel().setVisible(true);

		cl = (CardLayout)(panelContainerCardLayout.getLayout());
        cl.show(panelContainerCardLayout, SEARCH_MOVIE);
        
       searchMoviePcOrCloudPanel.getSearchMovieTitleTextField().requestFocus();
       
       
       try { 
			File fichero = new File("src/dad/filmfanatic/help/help.hs"); 
			URL hsURL = fichero.toURI().toURL();   
			HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL); 

			HelpBroker helpBroker = helpSet.createHelpBroker();   
			helpBroker.enableHelpKey( searchMoviePcOrCloudPanel , "anadirmoviedatabase", helpSet); 
			
		} catch (Exception ex){ 
			System.out.println("Error en helpset " + ex.toString() );
		}
       
	}


	public JPanel getPanelContainerCardLayout() {
		return panelContainerCardLayout;
	}


	public void setPanelContainerCardLayout(JPanel panelContainerCardLayout) {
		this.panelContainerCardLayout = panelContainerCardLayout;
	}


	public CardLayout getCl() {
		return cl;
	}


	public void setCl(CardLayout cl) {
		this.cl = cl;
	}


	public AddEditFromPCMoviePanel getAddEditFromPCMoviePanel() {
		return addEditFromPCMoviePanel;
	}


	public void setAddEditFromPCMoviePanel(
			AddEditFromPCMoviePanel addEditFromPCMoviePanel) {
		this.addEditFromPCMoviePanel = addEditFromPCMoviePanel;
	}


	public SearchMoviePcOrCloudPanel getSearchMoviePcOrCloudPanel() {
		return searchMoviePcOrCloudPanel;
	}


	public void setSearchMoviePcOrCloudPanel(
			SearchMoviePcOrCloudPanel searchMoviePcOrCloudPanel) {
		this.searchMoviePcOrCloudPanel = searchMoviePcOrCloudPanel;
	}


	public JButton getFindMoviesButton() {
		return findMoviesButton;
	}


	public void setFindMoviesButton(JButton findMoviesButton) {
		this.findMoviesButton = findMoviesButton;
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
		System.exit(0);
	}
	
	
	
}