package dad.filmfanatic.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dad.filmfanatic.customcomponent.GTextField;
import dad.filmfanatic.customcomponent.MosaicPanelCloud;
import dad.filmfanatic.customcomponent.MovieDataPanel;
import dad.filmfanatic.dialog.InformationMovieDialog;
import dad.filmfanatic.dialog.LoadingDialog;
import dad.filmfanatic.exception.MovieExistException;
import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.model.CompanyModel;
import dad.filmfanatic.model.CountryProductionModel;
import dad.filmfanatic.model.GenreModel;
import dad.filmfanatic.model.HistoryModel;
import dad.filmfanatic.model.LanguageModel;
import dad.filmfanatic.model.MovieCastPeople;
import dad.filmfanatic.model.MovieCrewPeople;
import dad.filmfanatic.model.MovieModel;
import dad.filmfanatic.services.MovieDataBaseService;
import dad.filmfanatic.tmdb.TMDb;
import dad.filmfanatic.utils.Iconos;
import dad.filmfanatic.utils.ImageUtil;
import dad.filmfanatic.utils.ListTableModel;
import dad.filmfanatic.window.MainWindow;

public class SearchMoviePcOrCloudPanel extends JPanel{
	
	private JPanel principalResultSearchPanel;
	private JButton previusPageButton;
	private JButton nextPageButton;
	private JLabel addMovieButton;
	private JLabel informationMovieButton;
	private JPanel typeShowDataMovieSearchPanel;
	private JTable tableResultmovieModel;
	private JPanel likeDetailPanel;
	private JPanel likeMosaicPanel;
	private JPanel basicSearchMoviePanel;
	private JLabel titleSearchLabel;
	private GTextField searchMovieTitleTextField;
	private JPanel searchMoviePanel;
	private ListTableModel<MovieModel> listTableModelMovie;
	private TMDb tmbd;
	private boolean rowWasClicked;
	private boolean myMovies = false;
	
	private static Color searchBackGroundColor;
	
	public static int totalPages = 0;
	private int actualPage = 1;
	
	
	public static final String LIKE_LIST = "LIKE_LIST";
	public static final String LIKE_DETAIL = "LIKE_PANEL";
	public static final String LIKE_MOSAIC = "LIKE_MOSAIC";
	
	private MainWindow mainWindow;
	private JPanel panel_2;
	private JPanel likeListPanel;
	
	private JRadioButton watchedRadio;

	private JLabel deleteMyMovieButton;
	private JLabel editMovieButton;
	private JButton searchMovieButtonButton;
	
	private JRadioButton notWatchedRadio;
	private JPanel rowPagePanel;
	private JButton clearData;
	private JPanel titleHeaderPanel;


	public SearchMoviePcOrCloudPanel(TMDb tmdb, MainWindow mainWindow){
		this.mainWindow = mainWindow;
		this.tmbd = tmdb;
		initComponent();
		loadMoviesInProgram(searchMovieTitleTextField.getText(), null , null , "MYPC" , "YES");
		   
		searchMovieTitleTextField.requestFocus();
		
        try { 
			File fichero = new File("src/dad/filmfanatic/help/help.hs"); 
			URL hsURL = fichero.toURI().toURL();   
			HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL); 

			HelpBroker helpBroker = helpSet.createHelpBroker();   
			helpBroker.enableHelpKey( this.mainWindow.getContentPane() , "mispeliculas", helpSet); 
			
		} catch (Exception ex){ 
			//JOptionPane.showMessageDialog(this, "Error al cargar la ayuda " + ex.toString() ); 
			System.out.println("Error en helpset " + ex.toString() );
		}
		
	}

	
	
	private void initComponent() {

		searchBackGroundColor = new Color(0);
		setLayout(new BorderLayout(0,0));
		
		searchMoviePanel = new JPanel();
		searchMoviePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchMoviePanel.setLayout(new BorderLayout(0, 0));
		
		add(searchMoviePanel , BorderLayout.CENTER);
		
		
		rowPagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER ));
		//rowPagePanel.setBackground(searchBackGroundColor);
		rowPagePanel.setBounds(new Rectangle(5, 5, 5, 5));
		
		
		principalResultSearchPanel = new JPanel();
		principalResultSearchPanel.setBorder(null);
		principalResultSearchPanel.setLayout(new BorderLayout(0, 0));
		
		searchMoviePanel.add( principalResultSearchPanel , BorderLayout.CENTER );
		principalResultSearchPanel.add( rowPagePanel , BorderLayout.SOUTH );
		
		previusPageButton = new JButton("");
		previusPageButton.setBorder(null);
		previusPageButton.setSize(new Dimension(50, 50));
		previusPageButton.setBorderPainted(false);
		previusPageButton.setEnabled(false);
		previusPageButton.setIcon(new ImageIcon(MainWindow.class.getResource("/dad/filmfanatic/iconos/flecha-izquierda-png.png")));
		previusPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onForwardPageActionPerformed(e);
			}
		});
		rowPagePanel.add(previusPageButton);
		
		nextPageButton = new JButton("");
		nextPageButton.setBorder(null);
		nextPageButton.setSize(new Dimension(50, 50));
		nextPageButton.setBorderPainted(false);
		nextPageButton.setEnabled(false);
		nextPageButton.setIcon(new ImageIcon(MainWindow.class.getResource("/dad/filmfanatic/iconos/flecha-derecha.png")));
		nextPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onNextPageActionPerformed(e);
			}
		});
		rowPagePanel.add(nextPageButton);
		
		typeShowDataMovieSearchPanel = new JPanel();
		typeShowDataMovieSearchPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		principalResultSearchPanel.add(typeShowDataMovieSearchPanel, BorderLayout.CENTER);
		typeShowDataMovieSearchPanel.setLayout(new CardLayout(0, 0));
		
		likeListPanel = new JPanel();
		typeShowDataMovieSearchPanel.add(likeListPanel, LIKE_LIST);
		likeListPanel.setLayout(new BorderLayout(0, 0));
		
		
		listTableModelMovie = new ListTableModel<MovieModel>();
		listTableModelMovie.addColumn("TÍTULO"     , String.class , false , "title");
		listTableModelMovie.addColumn("LEMA"       , String.class   , false , "tagLine");
		listTableModelMovie.addColumn("PUNTUACIÓN" , String.class , false , "voteAverage");

		tableResultmovieModel = new JTable(listTableModelMovie);
		tableResultmovieModel.setRowHeight(25);
		tableResultmovieModel.revalidate();
		tableResultmovieModel.repaint();
		

		
		likeListPanel.add(new JScrollPane(tableResultmovieModel) , BorderLayout.CENTER);
	
		
		
		
		likeDetailPanel = new JPanel();
		typeShowDataMovieSearchPanel.add(new JScrollPane(likeDetailPanel), LIKE_DETAIL);
		likeDetailPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		likeMosaicPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		typeShowDataMovieSearchPanel.add(scrollPane, LIKE_MOSAIC);
		likeMosaicPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		scrollPane.setViewportView(likeMosaicPanel);
	
		
		JPanel patherPanelSearchMovie = new JPanel();
		searchMoviePanel.add(patherPanelSearchMovie, BorderLayout.NORTH);
		patherPanelSearchMovie.setLayout(new BorderLayout(0, 0));
		
		basicSearchMoviePanel = new JPanel();
		basicSearchMoviePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		patherPanelSearchMovie.add(basicSearchMoviePanel, BorderLayout.NORTH);
		GridBagLayout gbl_basicSearchMoviePanel = new GridBagLayout();
		gbl_basicSearchMoviePanel.columnWidths = new int[]{64, 237, 168, 0, 0};
		gbl_basicSearchMoviePanel.rowHeights = new int[]{0, 0, 0};
		gbl_basicSearchMoviePanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_basicSearchMoviePanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		basicSearchMoviePanel.setLayout(gbl_basicSearchMoviePanel);
		
		titleHeaderPanel = new JPanel();
		titleHeaderPanel.setBackground(MainWindow.colorParaMovieDataBase);
		
		
		//panel_1.setBackground(searchBackGroundColor);
		GridBagConstraints gbc_titleHeaderPanel = new GridBagConstraints();
		gbc_titleHeaderPanel.gridwidth = 4;
		gbc_titleHeaderPanel.insets = new Insets(0, 0, 5, 0);
		gbc_titleHeaderPanel.fill = GridBagConstraints.BOTH;
		gbc_titleHeaderPanel.gridx = 0;
		gbc_titleHeaderPanel.gridy = 0;
		basicSearchMoviePanel.add(titleHeaderPanel, gbc_titleHeaderPanel);
		
		titleSearchLabel = new JLabel("AÑADIR DESDE MOVIE DATA BASE");
		titleSearchLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		titleHeaderPanel.add(titleSearchLabel);
		
		JLabel tituloBasicSearchLabel = new JLabel("Título");
		tituloBasicSearchLabel.setForeground(Color.BLACK);
	
		GridBagConstraints gbc_tituloBasicSearchLabel = new GridBagConstraints();
		gbc_tituloBasicSearchLabel.insets = new Insets(0, 0, 0, 5);
		gbc_tituloBasicSearchLabel.anchor = GridBagConstraints.EAST;
		gbc_tituloBasicSearchLabel.gridx = 0;
		gbc_tituloBasicSearchLabel.gridy = 1;
		basicSearchMoviePanel.add(tituloBasicSearchLabel, gbc_tituloBasicSearchLabel);
		
		GridBagConstraints gbc_searchMovieTitleTextField_1 = new GridBagConstraints();
		gbc_searchMovieTitleTextField_1.fill = GridBagConstraints.BOTH;
		gbc_searchMovieTitleTextField_1.anchor = GridBagConstraints.WEST;
		gbc_searchMovieTitleTextField_1.insets = new Insets(0, 0, 0, 5);
		gbc_searchMovieTitleTextField_1.gridx = 1;
		gbc_searchMovieTitleTextField_1.gridy = 1;
		
		JPanel titleMoviePanel = new JPanel();
		GridBagLayout gbl_titleMoviePanel = new GridBagLayout();
		gbl_titleMoviePanel.columnWidths = new int[]{389, 0};
		gbl_titleMoviePanel.rowHeights = new int[]{19, 25, 0};
		gbl_titleMoviePanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_titleMoviePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		titleMoviePanel.setLayout(gbl_titleMoviePanel);
		
		searchMovieTitleTextField = new GTextField( 250 , 80 , true );
			if ( MovieDataBaseService.getHistoryService().getAllHistory() != null  ){
				for ( HistoryModel historyModel :  MovieDataBaseService.getHistoryService().getAllHistory() ){
					searchMovieTitleTextField.getDataList().add(historyModel.getText());
				}
			}
			
		searchMovieTitleTextField.setColumns(35);
		searchMovieTitleTextField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				onSearchMovieBasicKeyRelease(e);
			}
			
		});
	
		
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{432, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		basicSearchMoviePanel.add(searchMovieTitleTextField , gbc_textField);

		ButtonGroup group = new ButtonGroup();
		
		watchedRadio = new JRadioButton("Vistas");
		watchedRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSearchMovieButtonActionPerformed(e);
			}
		});
		watchedRadio.setSelected(true);
		
		group.add(watchedRadio);
		watchedRadio.setBorder(null);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		
		JPanel eyePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		eyePanel.add(watchedRadio);
		
		basicSearchMoviePanel.add(eyePanel, gbc_btnNewButton);
		
		notWatchedRadio = new JRadioButton("No Vistas");
		notWatchedRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSearchMovieButtonActionPerformed(e);
			}
		});
		group.add(notWatchedRadio);
		eyePanel.add(notWatchedRadio);
		
		searchMovieButtonButton = new JButton("");
		searchMovieButtonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSearchMovieButtonActionPerformed(e);
			}
			
		});
		searchMovieButtonButton.setBorder(null);
		searchMovieButtonButton.setIcon(new ImageIcon(SearchMoviePcOrCloudPanel.class.getResource("/dad/filmfanatic/iconos/search20x20.png")));
		eyePanel.add(searchMovieButtonButton);
		
		clearData = new JButton("");
		clearData.setBorder(null);
		clearData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearData(e);
			}
		});
		clearData.setIcon(new ImageIcon(SearchMoviePcOrCloudPanel.class.getResource("/dad/filmfanatic/iconos/clear23x23.png")));
		eyePanel.add(clearData);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 1;
		basicSearchMoviePanel.add(panel, gbc_panel);
		
		
		JButton listResultSearchBasicModeRadioButton = new JButton("");
		listResultSearchBasicModeRadioButton.setBorder(null);
		listResultSearchBasicModeRadioButton.setMargin(new Insets(0, 0, 0, 0));
		listResultSearchBasicModeRadioButton.setIcon(new ImageIcon(MainWindow.class.getResource("/dad/filmfanatic/iconos/listlist.png")));
		listResultSearchBasicModeRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onListMovieLikeListActionPermformed(e);
			}
		});
		
		editMovieButton = new JLabel("");
		editMovieButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				onEditMovieMyMoviesSmallButton(e);
			}
		});
		editMovieButton.setBorder(null);
		editMovieButton.setIcon(new ImageIcon(SearchMoviePcOrCloudPanel.class.getResource("/dad/filmfanatic/iconos/movie_edit50X50.png")));
		panel.add(editMovieButton);
		
		deleteMyMovieButton = new JLabel("");
		deleteMyMovieButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				onDeleteMovieActionPerformed(e);
			}
		});
		deleteMyMovieButton.setBorder(null);
		deleteMyMovieButton.setIcon(new ImageIcon(SearchMoviePcOrCloudPanel.class.getResource("/dad/filmfanatic/iconos/remove.png")));
		panel.add(deleteMyMovieButton);
		
		addMovieButton = new JLabel("");
		addMovieButton.setBorder(null);
		panel.add(addMovieButton);
		addMovieButton.setSize(new Dimension(50, 50));
		addMovieButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				onAddMovieButton(e);
			}
		});
		
		//addMovieButton.setBorderPainted(false);
		addMovieButton.setIcon(new ImageIcon(SearchMoviePcOrCloudPanel.class.getResource("/dad/filmfanatic/iconos/addgreencircle.png")));
		
		informationMovieButton = new JLabel("");
		informationMovieButton.setBorder(null);
		panel.add(informationMovieButton);
		informationMovieButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e){
				onInformationMovie(e);
			}
		});
		informationMovieButton.setIcon(new ImageIcon(SearchMoviePcOrCloudPanel.class.getResource("/dad/filmfanatic/iconos/detail.png")));
		panel.add(listResultSearchBasicModeRadioButton);
		
		JButton detailResultSearchMovieFullRadioButton = new JButton("");
		detailResultSearchMovieFullRadioButton.setBorder(null);
		detailResultSearchMovieFullRadioButton.setMargin(new Insets(0, 0, 0, 0));
		detailResultSearchMovieFullRadioButton.setIcon(new ImageIcon(MainWindow.class.getResource("/dad/filmfanatic/iconos/fulllist.png")));
		detailResultSearchMovieFullRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onListMovieLikeDetailActionPermformed(e);
			}
		});
		
		JButton mosaicButton = new JButton("");
		mosaicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onListMovieLikeMosaicActionPermformed(e);
			}
		});
		mosaicButton.setMargin(new Insets(0, 0, 0, 0));
		mosaicButton.setBorder(null);
		mosaicButton.setIcon(new ImageIcon(MainWindow.class.getResource("/dad/filmfanatic/iconos/mosaiclist.png")));
		panel.add(mosaicButton);
		panel.add(detailResultSearchMovieFullRadioButton);
		
	}



	protected void clearData(ActionEvent e) {
		
		searchMovieTitleTextField.setText("");
		listTableModelMovie.getValues().clear();
		tableResultmovieModel.updateUI();
		typeShowDataMovieSearchPanel.setBorder(BorderFactory.createTitledBorder("Página 0 de 0"));
		
		
		
		
		likeMosaicPanel.removeAll();
		likeMosaicPanel.revalidate();
		likeMosaicPanel.repaint();
		
		likeDetailPanel.removeAll();
		likeDetailPanel.revalidate();
		likeDetailPanel.repaint();
		
		totalPages = 0;
		actualPage = 1;
		
	}



	protected void onInformationMovie(MouseEvent e) {
		
		if ( tableResultmovieModel.getSelectedRow() != -1){
			
			Long idMovie = listTableModelMovie.getValues().get(tableResultmovieModel.getSelectedRow()).getId();
			Long idCloud = listTableModelMovie.getValues().get(tableResultmovieModel.getSelectedRow()).getIdMovieInCloud();
			
			InformationMovieDialog info = null;
			
			if ( ! myMovies ){
				System.out.println("Dialogo Informacion desde internet");
				info = MovieDataBaseService.getDialogService().getInformationDialogFromINTERNET( idCloud , tmbd );
			}
			else {
				System.out.println("Dialogo Informacion LOCAL");
				info = MovieDataBaseService.getDialogService().getInformationDialogFromPC(idMovie);
			}
			
			info.setModal(true);
			info.setVisible(true);
			
			
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una película para poder ver su información.", "Error" , JOptionPane.ERROR_MESSAGE);
		}
		
	}



	protected void onDeleteMovieActionPerformed(MouseEvent e) {
		
		if ( tableResultmovieModel.getSelectedRow() != -1){
			
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere eliminar esta película?" , "Confirmar" ,JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE);
			
			if ( option == JOptionPane.YES_OPTION ){
				Long id = listTableModelMovie.getValues().get(  tableResultmovieModel.getSelectedRow() ).getId();
	
				MovieModel movie = MovieDataBaseService.getMovieService().get(id);
				MovieDataBaseService.getMovieService().delete( movie   );
				JOptionPane.showMessageDialog(this, "Película borrada con éxito!" , "Correcto" , JOptionPane.INFORMATION_MESSAGE);
				goToMyMovies();
			}
		
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una película para poder eliminar.", "Error" , JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	public void goToMyMovies(){
		mainWindow.getSearchMoviePcOrCloudPanel().setMyMovies(true);
		mainWindow.getSearchMoviePcOrCloudPanel().getTitleSearchLabel().setText("MIS PELÍCULAS");
		mainWindow.getSearchMoviePcOrCloudPanel().getAddMovieButton().setVisible(false);
		
		CardLayout cl = (CardLayout)(mainWindow.getPanelContainerCardLayout().getLayout());
        cl.show( mainWindow.getPanelContainerCardLayout(), MainWindow.SEARCH_MOVIE);

        loadMoviesInProgram( searchMovieTitleTextField.getText() , null , null , "MYPC", "YES");
	}



	protected void onEditMovieMyMoviesSmallButton(MouseEvent e) {

		if ( tableResultmovieModel.getSelectedRow() != -1){
			Long idMovie = listTableModelMovie.getValues().get(tableResultmovieModel.getSelectedRow()).getId();
			updateMovie(idMovie);
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una película para poder modificarla.");
		}
		
	}



	protected void onSearchMovieButtonActionPerformed(ActionEvent e) {
			
			if ( myMovies){
				String watched = "";
				
				if (watchedRadio.isSelected()) watched="YES";
				if (!watchedRadio.isSelected()) watched="NO";
			
				loadMoviesInProgram( searchMovieTitleTextField.getText() , null , null , "MYPC", watched);
				nextPageButton.setEnabled(true);
				previusPageButton.setEnabled(true);

			}else{
				
				if ( ! searchMovieTitleTextField.getText().isEmpty()){
					loadMoviesInProgram(searchMovieTitleTextField.getText(), null , 1 , "INTERNET", "");
				}else{
					JOptionPane.showMessageDialog(this, "Escriba un título para poder buscar.");
				}
			}	
	}
	
	
	private void deleteByMosaicAndPanel(ActionEvent e, Long idMovie){
		
		int option = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere eliminar esta película?" , "Confirmar" ,JOptionPane.YES_NO_OPTION , JOptionPane.INFORMATION_MESSAGE);
		
		if ( option == JOptionPane.YES_OPTION ){
			MovieModel movie = MovieDataBaseService.getMovieService().get(idMovie);
			MovieDataBaseService.getMovieService().delete( movie   );
		}
	}
	
	
	



	public ListTableModel<MovieModel> getListTableModelMovie() {
		return listTableModelMovie;
	}



	public void setListTableModelMovie(
			ListTableModel<MovieModel> listTableModelMovie) {
		this.listTableModelMovie = listTableModelMovie;
	}



	private void fillHistory(){
		
		if ( MovieDataBaseService.getHistoryService().getAllHistory() != null  ){
			for ( HistoryModel historyModel :  MovieDataBaseService.getHistoryService().getAllHistory() ){
				searchMovieTitleTextField.getDataList().add(historyModel.getText());
			}
		}
	}
	
	
	
	protected void onSearchMovieBasicKeyRelease(KeyEvent e) {
		
		if ( myMovies ){

			String watched = "";
			if (watchedRadio.isSelected()) watched="YES";
			if (!watchedRadio.isSelected()) watched="NO";
			
			if ( e.getKeyCode() != KeyEvent.VK_UP || e.getKeyCode() != KeyEvent.VK_DOWN || e.getKeyChar() != 8  ) {
					loadMoviesInProgram(searchMovieTitleTextField.getText(), null , null , "MYPC", watched);
					nextPageButton.setEnabled(true);
					previusPageButton.setEnabled(true);
			}
			
		}else{
		
			if ( e.getKeyCode() == KeyEvent.VK_ENTER ){
				
				if(searchMovieTitleTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(this, "Escriba un título por favor", "Error" , JOptionPane.ERROR_MESSAGE );
				}else{
					
					HistoryModel historyModel = new HistoryModel();
					historyModel.setText(searchMovieTitleTextField.getText());
					MovieDataBaseService.getHistoryService().create(historyModel);
					
					if ( MovieDataBaseService.getHistoryService().getAllHistory() != null  ){

						searchMovieTitleTextField.getDataList().clear();
						
						for ( HistoryModel historyModelItem :  MovieDataBaseService.getHistoryService().getAllHistory() ){
							searchMovieTitleTextField.getDataList().add(historyModelItem.getText());
						}
					}
					
					
					loadMoviesInProgram( searchMovieTitleTextField.getText() , null , null , "INTERNET" , "");
					nextPageButton.setEnabled(true);
					previusPageButton.setEnabled(true);
				}
				
				
			}
		}
	}
	
	
	
	private void loadMoviesInProgram(final String title , final Integer year , final Integer page, final String option, final String watched){
		
		  final SwingWorker worker = new SwingWorker(){  
			  
			  
	            @Override  
	            protected Object doInBackground() throws Exception {
	            	
	            	LoadingDialog loading = new LoadingDialog("Buscando...");
	            	//System.out.println("VALOR TITUTLO -> " + title + " PAGINA-> " + page + " OPCION-> " + option + " WATCHED-> " + watched);
	            	
	            	if (rowWasClicked){
	            		nextPageButton.setEnabled(false);
	            		previusPageButton.setEnabled(false);
	            	}
	            	
	            	loading.setVisible(true);
	            	List<MovieModel> movieList = MovieDataBaseService.getMovieService().getMovies(title, year, page, tmbd, option , watched);
	            	
	            	if ( movieList.size() == 0 && option.equals("INTERNET")) {
	            		notFoundMoviesMessage();
	            		rowPagePanel.setVisible(false);
	            	}else{
	            		rowPagePanel.setVisible(true);
	            	
			            	
			            	List<MosaicPanelCloud> mosaicList = new ArrayList<MosaicPanelCloud>();
			            	List<MovieDataPanel> movieDataPanelList = new ArrayList<MovieDataPanel>();
			            	
			            	//System.out.println("LONGITUD DE LA LISTA -> " + movieList.size() );
	            	
			        		
			        		listTableModelMovie.getValues().clear();
			        		listTableModelMovie.setValues(  movieList  );
			        		
			        		//likeDetailPanel.removeAll();
			        		//likeMosaicPanel.removeAll();
			        	
			        		int vuelta = 0;
			        		
			        		for ( final MovieModel movieModel :  movieList){
			        			//System.out.println("VUELTA -> " + vuelta + " PAGINADO EN " + actualPage );
			        			
			        				ImageIcon icono = null;
			        				
			        				//**************** DECLARACION DE MOSAICOS Y PANELES***********************
			        			    final MosaicPanelCloud mosaicPanelCloud = new MosaicPanelCloud(movieModel);
			        			    mosaicPanelCloud.setPreferredSize(new Dimension(150,250));
			        			    
			        			    final MovieDataPanel moviePanel = new MovieDataPanel(movieModel);
			        			    //**************************************************************************
			        			    
			        			    if ( option.equals("INTERNET")){
			        			    	
			        			    	mosaicPanelCloud.getAddEditMovieButton().setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/addmovielikewatched.png")));
			        			    	mosaicPanelCloud.getDeleteMovieButton().setVisible(false);
			        			    	mosaicPanelCloud.getStartsPanel().setVisible(false);
			        			    	
			        			    	moviePanel.getAddMovieButton().setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/addmovielikewatched.png")));
			        			    	moviePanel.getAddMovieButton().setVisible(true);
			        			    	moviePanel.getDeleteButton().setVisible(false);
			        			    	moviePanel.getStartsPanel().setVisible(false);
			        			    	
			        			    	moviePanel.getSinopsisTextArea().setText(movieModel.getOverview());
			        			    	
			        			    	if ( movieModel.getImagePath() != null ){
					        			  	icono = ImageUtil.getImageIconFromURL( movieModel.getImagePath());
			        			    	}else{
				        			    	icono = (ImageIcon) Iconos.IMAGE_NOT_FOUND;
					        			}
			        			    	
			        			    	
			        			    }else if (option.equals("MYPC")){
			        			    	
			        			    	//*****************************************
			        			    	//Configuramos los puntos.
			        			    	if (movieModel.getVoteAverage()!=null){
			        			    		mosaicPanelCloud.getStartsPanel().setPoints(movieModel.getVoteAverage());
			        			    		moviePanel.getStartsPanel().setPoints(movieModel.getVoteAverage());
			        			    	}else{
			        			    		mosaicPanelCloud.getStartsPanel().setPoints(0L);
			        			    		moviePanel.getStartsPanel().setPoints(0L);
			        			    	}
		        			    		
		        			    		mosaicPanelCloud.updateUI();
		        			    		moviePanel.updateUI();
			        			    	//*****************************************

			        			    	mosaicPanelCloud.getAddEditMovieButton().setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/editMovie20x20.png")));
			        			    	mosaicPanelCloud.getDeleteMovieButton().setVisible(true);
			        			    	mosaicPanelCloud.getStartsPanel().setVisible(true);
			        			    	
			        			    	
			        			    	moviePanel.getAddMovieButton().setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/editMovie20x20.png")));
			        			    	moviePanel.getAddMovieButton().setVisible(true);
			        			    	moviePanel.getDeleteButton().setVisible(true);
			        			    	
			        			    	//System.out.println("DENTOR DE MI PC EN VUELTA " + vuelta );
		        			    		
			        			    	if ( movieModel.getImgPoster() != null){
			        			    		icono = ImageUtil.getImageFromByteArray(movieModel.getImgPoster(), 91, 136);
			        			    	}else{
			        			    		icono = (ImageIcon) Iconos.IMAGE_NOT_FOUND;
					        			}
			        			    	
			        			    	//oculto panel de flechas
			        			    	mainWindow.getSearchMoviePcOrCloudPanel().getRowPagePanel().setVisible(false);
			        			    	
			        			    	
			        			    }
			        			    
			        			    MouseAdapter startPanelListener = new MouseAdapter() {

										@Override
										public void mouseClicked(MouseEvent e) {
											String starName = ((JLabel)e.getSource()).getName();
											
											if (starName.equals("E1")){
												MovieDataBaseService.getMovieService().updatePoints( 1L, movieModel.getId() );
												mosaicPanelCloud.getStartsPanel().setPoints(1L);
												moviePanel.getStartsPanel().setPoints(1L);
												
											}
											
											if (starName.equals("E2")){
												MovieDataBaseService.getMovieService().updatePoints( 2L, movieModel.getId() );
												mosaicPanelCloud.getStartsPanel().setPoints(2L);
												moviePanel.getStartsPanel().setPoints(2L);
											}
											
											if (starName.equals("E3")){
												MovieDataBaseService.getMovieService().updatePoints( 3L, movieModel.getId() );
												mosaicPanelCloud.getStartsPanel().setPoints(3L);
												moviePanel.getStartsPanel().setPoints(3L);
												
											}
											
											if (starName.equals("E4")){
												MovieDataBaseService.getMovieService().updatePoints( 4L, movieModel.getId() );
												mosaicPanelCloud.getStartsPanel().setPoints(4L);
												moviePanel.getStartsPanel().setPoints(4L);
											}
											
											if (starName.equals("E5")){
												MovieDataBaseService.getMovieService().updatePoints( 5L, movieModel.getId() );
												mosaicPanelCloud.getStartsPanel().setPoints(5L);
												moviePanel.getStartsPanel().setPoints(5L);
											}
										}
									};
			        			    
									//a las estrellas les damos el listener para que actualice
			        			    mosaicPanelCloud.getStartsPanel().getStart1().addMouseListener(startPanelListener);
			        			    mosaicPanelCloud.getStartsPanel().getStart2().addMouseListener(startPanelListener);
			        			    mosaicPanelCloud.getStartsPanel().getStart3().addMouseListener(startPanelListener);
			        			    mosaicPanelCloud.getStartsPanel().getStart4().addMouseListener(startPanelListener);
			        			    mosaicPanelCloud.getStartsPanel().getStart5().addMouseListener(startPanelListener);
			        			    
			        			    moviePanel.getStartsPanel().getStart1().addMouseListener(startPanelListener);
			        			    moviePanel.getStartsPanel().getStart2().addMouseListener(startPanelListener);
			        			    moviePanel.getStartsPanel().getStart3().addMouseListener(startPanelListener);
			        			    moviePanel.getStartsPanel().getStart4().addMouseListener(startPanelListener);
			        			    moviePanel.getStartsPanel().getStart5().addMouseListener(startPanelListener);
			        			    
			        			    //añadir o modificar button
			        			    ActionListener listener = new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											if (option.equals("INTERNET")){
												onAddMovieButtonMosaic(e , movieModel );
											}
											else if (option.equals("MYPC")){
												updateMovie(movieModel.getId());
											}
											
										}
									};
									
									ActionListener removeListener = new ActionListener(){

										@Override
										public void actionPerformed(ActionEvent e) {
											deleteByMosaicAndPanel(e , movieModel.getId() );
										}
										
									};
									
									ActionListener informationButtonListener = new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											
											
											
											if (option.equals("INTERNET")){
												System.out.println("Dialogo Informacion desde internet");
												InformationMovieDialog info = MovieDataBaseService.getDialogService().getInformationDialogFromINTERNET( movieModel.getIdMovieInCloud() , tmbd );
												
												ImageIcon imagen = ImageUtil.getImageIconFromURL(movieModel.getImagePath());
												if (imagen != null ){
													info.getImageLabel().setIcon(imagen);
												}else{
													imagen = (ImageIcon) Iconos.IMAGE_NOT_FOUND;
												}
												
												if ( movieModel.getTitle()!= null){
													info.setTitle("Información de la película: " + movieModel.getTitle());
												}
												info.setModal(true);
												info.setVisible(true);
											}
											else if (option.equals("MYPC")){
												System.out.println("Dialogo Informacion LOCAL");
												InformationMovieDialog info = MovieDataBaseService.getDialogService().getInformationDialogFromPC(movieModel.getId());
												
												ImageIcon imagen = ImageUtil.getImageFromByteArray(movieModel.getImgPoster(), 136, 90);
												if (imagen != null ){
													info.getImageLabel().setIcon(imagen);
												}else{
													imagen = (ImageIcon) Iconos.IMAGE_NOT_FOUND;
												}
												
												if ( movieModel.getTitle()!= null){
													info.setTitle("Información de la película: " + movieModel.getTitle());
												}
												info.setModal(true);
												info.setVisible(true);
											}
											
											//info.setModal(true);
											//info.setVisible(true);
										}
									};
									
			        			    //boton add-update movie
			        			    mosaicPanelCloud.getAddEditMovieButton().addActionListener(listener);
			        			    moviePanel.getAddMovieButton().addActionListener(listener);
			        			    
			        			    //information movie
			        			    mosaicPanelCloud.getInfoMovieButton().addActionListener(informationButtonListener);
			        			    moviePanel.getDetailButton().addActionListener(informationButtonListener);
			        			    
			        			    mosaicPanelCloud.getDeleteMovieButton().addActionListener(removeListener);
			        			    moviePanel.getDeleteButton().addActionListener(removeListener);
			        			    
			        			    
			        			    if ( icono != null){
			        			    	mosaicPanelCloud.getImageMovieLabel().setIcon (icono);
	        			    			moviePanel.getPosterLabel().setIcon(icono);
			        			    }else{
			        			    	mosaicPanelCloud.getImageMovieLabel().setIcon (Iconos.IMAGE_NOT_FOUND);
	        			    			moviePanel.getPosterLabel().setIcon(Iconos.IMAGE_NOT_FOUND);
			        			    }
	        			    			
	        			    		if ( movieModel.getOverview() != null){
	        			    			moviePanel.getSinopsisTextArea().setText(movieModel.getOverview());
	        			    			moviePanel.getSinopsisTextArea().updateUI();
	        			    			moviePanel.getSinopsisTextArea().repaint();
	        			    		}
	        			    		//puntuacion arreglar la puntuacion solo cuando entra en mi pc


	        			    		mosaicPanelCloud.getStartsPanel().repaint();
	        			    		mosaicPanelCloud.getStartsPanel().updateUI();
	        			    		mosaicPanelCloud.updateUI();
	        			    		
	        			    		//los inserto a los padres panel
			        			    //likeDetailPanel.add(moviePanel);
			        			    //likeMosaicPanel.add(mosaicPanelCloud);
	        			    		mosaicList.add(mosaicPanelCloud);
	        			    		movieDataPanelList.add(moviePanel);
			        			    
			        			    vuelta++;
			        		}
			        		
			        		
			        		//Los añado cuando los tenga todos cargados en los paneles
			        		//limpio y añado
			        		likeDetailPanel.removeAll();
			        		likeMosaicPanel.removeAll();
			        		System.out.println("CONTENIDO MOSAICO ------> " +likeMosaicPanel.getComponentCount());
			        		for (int i = 0 ; i < mosaicList.size() ; i++){
		        			  likeMosaicPanel.add(mosaicList.get(i));
	        			      likeDetailPanel.add(movieDataPanelList.get(i));
			        		}
			        		
			        		likeDetailPanel.revalidate();
			        		likeMosaicPanel.revalidate();
			        		likeDetailPanel.repaint();
			        		likeMosaicPanel.repaint();
			        		
			        		
			        		tableResultmovieModel.setModel(listTableModelMovie);
			       	        tableResultmovieModel.setPreferredScrollableViewportSize(tableResultmovieModel.getPreferredSize());
			 	            tableResultmovieModel.setRowHeight(25);

			 	            
			 	            //si esta en el principio tnego que bloquear ir atras
			 	            if ( actualPage == 1 ){
			 	            	previusPageButton.setEnabled(false);
			 	            	nextPageButton.setEnabled(true);
				        		rowWasClicked = false;
			 	            }else if ( actualPage == totalPages ){
			 	            	previusPageButton.setEnabled(true);
			 	            	nextPageButton.setEnabled(false);
			 	            	rowWasClicked = false;
			 	            }else{
					        		nextPageButton.setEnabled(true);
					        		previusPageButton.setEnabled(true);
					        		rowWasClicked = false;
			 	            }
			 	            
			 	            
			 	            //si solo hay una pagina bloquear la flecha siguiente
			 	            if ( totalPages == 1 ){
			 	            	nextPageButton.setEnabled(false);
			 	            }
			 	            
			 	            
			        		if ( option.equals("INTERNET")){
			        			String dataPages = "Página " + actualPage + " de " + totalPages;
			        			typeShowDataMovieSearchPanel.setBorder(BorderFactory.createTitledBorder(dataPages));
			        		}
			        		
			        		else if ( option.equals("MYPC")){
			        			typeShowDataMovieSearchPanel.setBorder(BorderFactory.createTitledBorder(""));
			        		}
			        		
			        		
			        		
			        		//loading.dispose();
	            	}//fin if si listado == null
			                //return null;
			                
			                loading.dispose();
			                return null;
	            }
	              
	        };  

	        worker.execute();  

	}
	

	protected void notFoundMoviesMessage() {
		JOptionPane.showMessageDialog(this, "No se han encontrado resultados","Lo siento :(", JOptionPane.INFORMATION_MESSAGE);
	}



	protected void onNextPageActionPerformed(ActionEvent e) {
		actualPage++;
		rowWasClicked = true;
		if ( ! searchMovieTitleTextField.getText().isEmpty() ){
			loadMoviesInProgram(searchMovieTitleTextField.getText(), null , actualPage , "INTERNET", "");
		}else{
			actualPage = 1;
			totalPages = 0;
			JOptionPane.showMessageDialog(this, "Escriba el título de la película para poder buscar" , "Error" , JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	protected void onForwardPageActionPerformed(ActionEvent e) {
		actualPage--;
		rowWasClicked = true;
		if ( ! searchMovieTitleTextField.getText().isEmpty() ){
			loadMoviesInProgram(searchMovieTitleTextField.getText(), null , actualPage , "INTERNET", "");
		}else{
			actualPage = 1;
			totalPages = 0;
			JOptionPane.showMessageDialog(this, "Escriba el título de la película para poder buscar" , "Error" , JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	protected void onListMovieLikeDetailActionPermformed(ActionEvent e) {
		System.out.println("Detalles");
		addMovieButton.setVisible(false);
		editMovieButton.setVisible(false);
		deleteMyMovieButton.setVisible(false);
		
		CardLayout cl = (CardLayout)(typeShowDataMovieSearchPanel.getLayout());
        cl.show(typeShowDataMovieSearchPanel, LIKE_DETAIL);
	}


	protected void onListMovieLikeListActionPermformed(ActionEvent e) {
		
		if ( myMovies ){
			editMovieButton.setVisible(true);
			deleteMyMovieButton.setVisible(true);
			informationMovieButton.setVisible(true);
			addMovieButton.setVisible(false);
		
		}else{ //internet
			addMovieButton.setVisible(true);
			informationMovieButton.setVisible(true);
			editMovieButton.setVisible(false);
			deleteMyMovieButton.setVisible(false);
		}
		
		
		CardLayout cl = (CardLayout)(typeShowDataMovieSearchPanel.getLayout());
		cl.show(typeShowDataMovieSearchPanel, LIKE_LIST);
	}
	
	protected void onListMovieLikeMosaicActionPermformed(ActionEvent e) {
		addMovieButton.setVisible(false);
		editMovieButton.setVisible(false);
		deleteMyMovieButton.setVisible(false);
		informationMovieButton.setVisible(false);
		
		CardLayout cl = (CardLayout)(typeShowDataMovieSearchPanel.getLayout());
        cl.show(typeShowDataMovieSearchPanel, LIKE_MOSAIC);	
		
	}
	
	private void onAddMovieButton(MouseEvent e) {
		
		
		if ( tableResultmovieModel.getSelectedRow() != -1){
			
		    String title = listTableModelMovie.getValues().get( tableResultmovieModel.getSelectedRow() ).getTitle();
			int option = JOptionPane.showConfirmDialog(this, "Va a añadir esta película a su ordenador. ¿ Ha visto la película \"" + title + "\" ?.\nSi no quiere guardar esta película pulse cancelar" , "Confirmar" , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE );
			
			if ( option == JOptionPane.YES_OPTION ){
				saveMovie("YES");
			}
			else if ( option == JOptionPane.NO_OPTION ){
				saveMovie("NO");
			}
				
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una película para poder guardarla", "Aviso" , JOptionPane.QUESTION_MESSAGE );
		}
		
		
		
	}
	
	
	private void onAddMovieButtonMosaic(ActionEvent e, MovieModel movieModel) {
		
		    String title = movieModel.getTitle();

		    int option = JOptionPane.showConfirmDialog(this, "¿Ha visto la película \"" + title + "\" ?. Si no quiere guardar esta película pulse cancelar" , "Confirmar" , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE );
			
			if ( option == JOptionPane.YES_OPTION ){
				saveMovieMosaic( movieModel.getIdMovieInCloud() , "YES");
			}
			else if ( option == JOptionPane.NO_OPTION ){
				saveMovieMosaic( movieModel.getIdMovieInCloud() , "NO");
			}
	}

	
	private void saveMovieMosaic(Long idMovie , String option){
		
		try{
			if (MovieDataBaseService.getMovieService().saveMovie( idMovie  , tmbd , option )){
				JOptionPane.showMessageDialog(this, "Pelicula guardada con éxito", "Correcto" , JOptionPane.QUESTION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(this, "No se ha podido guardar la película", "ERROR" , JOptionPane.ERROR_MESSAGE );
			}
		}catch(MovieExistException e){
			JOptionPane.showMessageDialog(this, "Esta película ya esta guardada !!", "ERROR" , JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
	
	private void saveMovie(final String option){

		final SwingWorker worker = new SwingWorker(){
			 

			@Override
			protected Object doInBackground() throws Exception {
				
				LoadingDialog loading = new LoadingDialog("Guardando...");
				
				Long idMovie = listTableModelMovie.getValues().get( tableResultmovieModel.getSelectedRow() ).getIdMovieInCloud();
				loading.setAlwaysOnTop(true);
				loading.setVisible(true);
				
				try{
					
					boolean isOk = MovieDataBaseService.getMovieService().saveMovie(idMovie, tmbd , option ); 
					
					if ( isOk ){
						loading.dispose();
						messageOk();
					}else{
						loading.dispose();
						messaggeError();
					}
				}catch ( MovieExistException e){
					loading.dispose();
					messaggeError();
				}
				
				
				return null;
			}  
		 };
		 
		 worker.execute();
	}
	
	private void messageOk(){
		JOptionPane.showMessageDialog(this, "Pelicula guardada con éxito", "Correcto" , JOptionPane.QUESTION_MESSAGE);
	}
	
	private void messaggeError(){
		JOptionPane.showMessageDialog(this, "La película que intenta guardar ya existe", "ERROR" , JOptionPane.ERROR_MESSAGE );
	}
	
	//Escribo los datos en la pantalla de añadir
	private void updateMovie(Long id){
		
		mainWindow.getAddEditFromPCMoviePanel().getHeaderTitleAddMoviePanel().setBackground(MainWindow.colorParaModificarPelicula);
		
		MovieModel movieModel = MovieDataBaseService.getMovieService().get(id);
		
		mainWindow.getAddEditFromPCMoviePanel().getIdMovieTextField().setText(movieModel.getId().toString());
		mainWindow.getAddEditFromPCMoviePanel().getTitleNewMovieGtextField().setText(movieModel.getTitle());
		mainWindow.getAddEditFromPCMoviePanel().getReleaseDateNewMovie().setFecha(movieModel.getReleaseDate());
		mainWindow.getAddEditFromPCMoviePanel().getStartsPanel().setPoints(movieModel.getVoteAverage());
		mainWindow.getAddEditFromPCMoviePanel().getSinopsisNewMovieTextArea().setText(movieModel.getOverview());
		mainWindow.getAddEditFromPCMoviePanel().getDurationTextField().setText(String.valueOf( movieModel.getDuration()) );
		mainWindow.getAddEditFromPCMoviePanel().getLemaNewMovieTextField().setText(movieModel.getTagLine());
		
		if ( movieModel.getWatched().equals("YES")){
			mainWindow.getAddEditFromPCMoviePanel().getYesRadio().setSelected(true);
		}
			
		if ( movieModel.getWatched().equals("NO")){
			mainWindow.getAddEditFromPCMoviePanel().getYesRadio().setSelected(false);
		}
		
		mainWindow.getAddEditFromPCMoviePanel().getOriginalTitleNewMovieGTextField().setText(movieModel.getOriginalTitle());
		
		if ( movieModel.getImgPoster() != null){
			mainWindow.getAddEditFromPCMoviePanel().getImagePosterLabel().setIcon( ImageUtil.getImageFromByteArray( movieModel.getImgPoster(), 91, 136 ));
		}else{
			mainWindow.getAddEditFromPCMoviePanel().getImagePosterLabel().setIcon(Iconos.IMAGE_NOT_FOUND);
		}
		
		
		//Paises
		mainWindow.getAddEditFromPCMoviePanel().getCountryDataPanel().getDataJListModel().clear();
		for ( CountryProductionModel country : movieModel.getProductionCountries()){
			mainWindow.getAddEditFromPCMoviePanel().getCountryDataPanel().getDataJListModel().addElement(country.getName());
		}
		
//		//Cast people
		mainWindow.getAddEditFromPCMoviePanel().getCastCastPeoplePanel().getListTableModel().getValues().clear();
		for ( MovieCastPeople movieCastPeople : movieModel.getMovieCastPeople() ){
			PersonItem personItem = new PersonItem();
			personItem.setName(movieCastPeople.getPersonModelCast().getName());
			personItem.setCharacter(movieCastPeople.getPersonCharacter());
			mainWindow.getAddEditFromPCMoviePanel().getCastCastPeoplePanel().getListTableModel().getValues().add(personItem);
		}
		mainWindow.getAddEditFromPCMoviePanel().getCastCastPeoplePanel().getTable().updateUI();
		
		//Crew people
		//Pasandole la clase padre por constructor
		mainWindow.getAddEditFromPCMoviePanel().getCastCrewPeoplePanel().getListTableModel().getValues().clear();
		for ( MovieCrewPeople movieCrewPeople : movieModel.getMovieCrewPeople() ){
			PersonItem personItem = new PersonItem();
			personItem.setName(movieCrewPeople.getPersonModelCrew().getName());
			personItem.setJob(movieCrewPeople.getJob());
			mainWindow.getAddEditFromPCMoviePanel().getCastCrewPeoplePanel().getListTableModel().getValues().add(personItem);
		}
		mainWindow.getAddEditFromPCMoviePanel().getCastCrewPeoplePanel().getTable().updateUI();
		
		
		//Language panel
		mainWindow.getAddEditFromPCMoviePanel().getLanguageDataPanel().getDataJListModel().clear();
		for ( LanguageModel languageModel : movieModel.getSpokenLanguages()){
			mainWindow.getAddEditFromPCMoviePanel().getLanguageDataPanel().getDataJListModel().addElement(languageModel.getName());
		}
		
		//Generos
		mainWindow.getAddEditFromPCMoviePanel().getGenresDataPanel().getDataJListModel().clear();
		for ( GenreModel genreModel : movieModel.getGenres()){
			mainWindow.getAddEditFromPCMoviePanel().getGenresDataPanel().getDataJListModel().addElement(genreModel.getName());
		}
		
		//Compañias
		mainWindow.getAddEditFromPCMoviePanel().getCompanyDataPanel().getDataJListModel().clear();
		for (CompanyModel companyModel : movieModel.getProductionCompanies() ){
			mainWindow.getAddEditFromPCMoviePanel().getCompanyDataPanel().getDataJListModel().addElement(companyModel.getName());
		}
		
		goToMainWindow();
	}
	
	
	private void goToMainWindow(){
		mainWindow.getAddEditFromPCMoviePanel().getTitleActionInMovieLabel().setText("MODIFICAR PELÍCULA");
		CardLayout aqui =  (CardLayout)( mainWindow.getPanelContainerCardLayout().getLayout() );
		aqui.show( mainWindow.getPanelContainerCardLayout(), MainWindow.ADD_MOVIE );
		
		 mainWindow.getAddEditFromPCMoviePanel().getTitleNewMovieGtextField().requestFocus();
	        
	        try { 
				File fichero = new File("src/dad/filmfanatic/help/help.hs"); 
				URL hsURL = fichero.toURI().toURL();   
				HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL); 

				HelpBroker helpBroker = helpSet.createHelpBroker();   
				helpBroker.enableHelpKey(  mainWindow.getAddEditFromPCMoviePanel() , "mispeliculas", helpSet); 
				
			} catch (Exception ex){ 
				//JOptionPane.showMessageDialog(this, "Error al cargar la ayuda " + ex.toString() ); 
				System.out.println("Error en helpset " + ex.toString() );
			}
		
		
	}
	
	
	
	public JPanel getPrincipalResultSearchPanel() {
		return principalResultSearchPanel;
	}
	public void setPrincipalResultSearchPanel(JPanel principalResultSearchPanel) {
		this.principalResultSearchPanel = principalResultSearchPanel;
	}
	public JButton getPreviusPageButton() {
		return previusPageButton;
	}
	public void setPreviusPageButton(JButton previusPageButton) {
		this.previusPageButton = previusPageButton;
	}
	public JButton getNextPageButton() {
		return nextPageButton;
	}
	public void setNextPageButton(JButton nextPageButton) {
		this.nextPageButton = nextPageButton;
	}
	
//	public JButton getAddMovieButton() {
//		return addMovieButton;
//	}
//	public void setAddMovieButton(JButton addMovieButton) {
//		this.addMovieButton = addMovieButton;
//	}
	
	
	
	public JLabel getInformationMovieButton() {
		return informationMovieButton;
	}
	public JLabel getAddMovieButton() {
		return addMovieButton;
	}



	public void setAddMovieButton(JLabel addMovieButton) {
		this.addMovieButton = addMovieButton;
	}



	public void setInformationMovieButton(JLabel informationMovieButton) {
		this.informationMovieButton = informationMovieButton;
	}
	public JPanel getTypeShowDataMovieSearchPanel() {
		return typeShowDataMovieSearchPanel;
	}
	public void setTypeShowDataMovieSearchPanel(JPanel typeShowDataMovieSearchPanel) {
		this.typeShowDataMovieSearchPanel = typeShowDataMovieSearchPanel;
	}
	public JTable getTableResultmovieModel() {
		return tableResultmovieModel;
	}
	public void setTableResultmovieModel(JTable tableResultmovieModel) {
		this.tableResultmovieModel = tableResultmovieModel;
	}
	public JPanel getLikeDetailPanel() {
		return likeDetailPanel;
	}
	public void setLikeDetailPanel(JPanel likeDetailPanel) {
		this.likeDetailPanel = likeDetailPanel;
	}
	public JPanel getLikeMosaicPanel() {
		return likeMosaicPanel;
	}
	public void setLikeMosaicPanel(JPanel likeMosaicPanel) {
		this.likeMosaicPanel = likeMosaicPanel;
	}
	public JPanel getBasicSearchMoviePanel() {
		return basicSearchMoviePanel;
	}
	public void setBasicSearchMoviePanel(JPanel basicSearchMoviePanel) {
		this.basicSearchMoviePanel = basicSearchMoviePanel;
	}
	public JLabel getTitleSearchLabel() {
		return titleSearchLabel;
	}
	public void setTitleSearchLabel(JLabel titleSearchLabel) {
		this.titleSearchLabel = titleSearchLabel;
	}
	public GTextField getSearchMovieTitleTextField() {
		return searchMovieTitleTextField;
	}
	public void setSearchMovieTitleTextField(GTextField searchMovieTitleTextField) {
		this.searchMovieTitleTextField = searchMovieTitleTextField;
	}
	public JPanel getSearchMoviePanel() {
		return searchMoviePanel;
	}
	public void setSearchMoviePanel(JPanel searchMoviePanel) {
		this.searchMoviePanel = searchMoviePanel;
	}
	public boolean isMyMovies() {
		return myMovies;
	}
	public void setMyMovies(boolean myMovies) {
		this.myMovies = myMovies;
	}
	public JPanel getLikeListPanel() {
		return likeListPanel;
	}
	public void setLikeListPanel(JPanel likeListPanel) {
		this.likeListPanel = likeListPanel;
	}
	public JRadioButton getWatchedRadio() {
		return watchedRadio;
	}
	public void setWatchedRadio(JRadioButton watchedRadio) {
		this.watchedRadio = watchedRadio;
	}
	public JRadioButton getNotWatchedRadio() {
		return notWatchedRadio;
	}
	public void setNotWatchedRadio(JRadioButton notWatchedRadio) {
		this.notWatchedRadio = notWatchedRadio;
	}
	public JLabel getDeleteMyMovieButton() {
		return deleteMyMovieButton;
	}
	public void setDeleteMyMovieButton(JLabel deleteMyMovieButton) {
		this.deleteMyMovieButton = deleteMyMovieButton;
	}
	public JLabel getEditMovieButton() {
		return editMovieButton;
	}
	public void setEditMovieButton(JLabel editMovieButton) {
		this.editMovieButton = editMovieButton;
	}
	public JPanel getRowPagePanel() {
		return rowPagePanel;
	}
	public void setRowPagePanel(JPanel rowPagePanel) {
		this.rowPagePanel = rowPagePanel;
	}



	public JPanel getTitleHeaderPanel() {
		return titleHeaderPanel;
	}



	public void setTitleHeaderPanel(JPanel titleHeaderPanel) {
		this.titleHeaderPanel = titleHeaderPanel;
	}
	
}