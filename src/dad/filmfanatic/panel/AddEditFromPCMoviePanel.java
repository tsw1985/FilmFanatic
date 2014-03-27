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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dad.filmfanatic.customcomponent.AddCastCrewPeoplePanel;
import dad.filmfanatic.customcomponent.AddDataPanel;
import dad.filmfanatic.customcomponent.GDate;
import dad.filmfanatic.customcomponent.GTextField;
import dad.filmfanatic.customcomponent.StartsPanel;
import dad.filmfanatic.items.CompanyItem;
import dad.filmfanatic.items.CountryItem;
import dad.filmfanatic.items.GenreItem;
import dad.filmfanatic.items.LanguageItem;
import dad.filmfanatic.items.MovieItem;
import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.model.CountryProductionModel;
import dad.filmfanatic.model.GenreModel;
import dad.filmfanatic.model.GenreModelItem;
import dad.filmfanatic.model.HistoryModel;
import dad.filmfanatic.services.MovieDataBaseService;
import dad.filmfanatic.tmdb.TMDb;
import dad.filmfanatic.utils.ImageUtil;
import dad.filmfanatic.window.MainWindow;

public class AddEditFromPCMoviePanel extends JPanel {
	
	private JLabel titleActionInMovieLabel;
	private GTextField titleNewMovieGtextField;
	private GTextField originalTitleNewMovieGTextField;
	private JLabel imagePosterLabel;
	private GDate releaseDateNewMovie;
	private JTextArea sinopsisNewMovieTextArea;
	private JTextField lemaNewMovieTextField;
	private JTextField durationTextField;
	private JPanel addMoviePanel;
	private AddCastCrewPeoplePanel castCrewPeoplePanel;
	private AddDataPanel genresDataPanel;
	private AddDataPanel languageDataPanel;
	private AddDataPanel countryDataPanel;
	private AddCastCrewPeoplePanel castCastPeoplePanel;
	private AddDataPanel companyDataPanel;
	private File imagefile;
	private TMDb tmbd;
	private JTextField idMovieTextField;
	private StartsPanel startsPanel;
	private JLabel lblPuntuacin;
	private JLabel lblVista;
	private JPanel panel;
	private JRadioButton yesRadio;
	private JRadioButton noRadio;
	private JPanel headerTitleAddMoviePanel;

	private MainWindow mainWindow;
	
	public AddEditFromPCMoviePanel(TMDb tmdb , MainWindow mainWindow){
		this.tmbd = tmdb;
		
		this.mainWindow = mainWindow;
		
		setLayout(new BorderLayout(0, 0));
		
		addMoviePanel = new JPanel();
		add(addMoviePanel, BorderLayout.NORTH);
		
		
		
		GridBagLayout gbl_addMoviePanel = new GridBagLayout();
		gbl_addMoviePanel.columnWidths = new int[]{0, 158, 0, 236, 107, 0, 0};
		gbl_addMoviePanel.rowHeights = new int[]{0, 0, 167, 0};
		gbl_addMoviePanel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_addMoviePanel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		addMoviePanel.setLayout(gbl_addMoviePanel);
		
		headerTitleAddMoviePanel = new JPanel();
		headerTitleAddMoviePanel.setBackground(MainWindow.colorParaNuevaPelicula);
		
		GridBagConstraints gbc_headerTitleAddMoviePanel = new GridBagConstraints();
		gbc_headerTitleAddMoviePanel.gridwidth = 6;
		gbc_headerTitleAddMoviePanel.insets = new Insets(0, 0, 5, 5);
		gbc_headerTitleAddMoviePanel.fill = GridBagConstraints.BOTH;
		gbc_headerTitleAddMoviePanel.gridx = 0;
		gbc_headerTitleAddMoviePanel.gridy = 0;
		addMoviePanel.add(headerTitleAddMoviePanel, gbc_headerTitleAddMoviePanel);
		
		titleActionInMovieLabel = new JLabel("AÑADIR PELÍCULA");
		headerTitleAddMoviePanel.add(titleActionInMovieLabel);
		titleActionInMovieLabel.setBackground(Color.WHITE);
		titleActionInMovieLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		
		JPanel introDataMoviePanel = new JPanel();
		introDataMoviePanel.setBorder(new TitledBorder(null, "Nueva Pel\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_introDataMoviePanel = new GridBagConstraints();
		gbc_introDataMoviePanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_introDataMoviePanel.gridwidth = 5;
		gbc_introDataMoviePanel.gridx = 1;
		gbc_introDataMoviePanel.gridy = 2;
		addMoviePanel.add(introDataMoviePanel, gbc_introDataMoviePanel);
		GridBagLayout gbl_introDataMoviePanel = new GridBagLayout();
		gbl_introDataMoviePanel.columnWidths = new int[]{0, 355, 0, 198, 0, 0, 0, 0};
		gbl_introDataMoviePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_introDataMoviePanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_introDataMoviePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		introDataMoviePanel.setLayout(gbl_introDataMoviePanel);
		
		JLabel lblTtulo = new JLabel("Título:");
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 0;
		introDataMoviePanel.add(lblTtulo, gbc_lblTtulo);
		
		titleNewMovieGtextField = new GTextField(205,80, false);
		titleNewMovieGtextField.setColumns(30);
		
		if ( MovieDataBaseService.getHistoryService().getAllHistory() != null ){
			for ( HistoryModel historyItem : MovieDataBaseService.getHistoryService().getAllHistory()){
				titleNewMovieGtextField.getDataList().add(historyItem.getText() );
			}
		}
		
		
		
		
		GridBagConstraints gbc_titleNewMovieGtextField = new GridBagConstraints();
		gbc_titleNewMovieGtextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleNewMovieGtextField.anchor = GridBagConstraints.NORTH;
		gbc_titleNewMovieGtextField.insets = new Insets(0, 0, 5, 5);
		gbc_titleNewMovieGtextField.gridx = 1;
		gbc_titleNewMovieGtextField.gridy = 0;
		
		
		introDataMoviePanel.add( titleNewMovieGtextField , gbc_titleNewMovieGtextField);
		
		JLabel lblTtuloOriginal = new JLabel("Título original:");
		GridBagConstraints gbc_lblTtuloOriginal = new GridBagConstraints();
		gbc_lblTtuloOriginal.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblTtuloOriginal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtuloOriginal.gridx = 2;
		gbc_lblTtuloOriginal.gridy = 0;
		introDataMoviePanel.add(lblTtuloOriginal, gbc_lblTtuloOriginal);
		
		originalTitleNewMovieGTextField = new GTextField(250,80, true );
		originalTitleNewMovieGTextField.setColumns(25);
		
		
		GridBagConstraints gbc_originalTitleNewMovieGTextField = new GridBagConstraints();
		gbc_originalTitleNewMovieGTextField.anchor = GridBagConstraints.NORTH;
		gbc_originalTitleNewMovieGTextField.gridwidth = 2;
		gbc_originalTitleNewMovieGTextField.insets = new Insets(0, 0, 5, 5);
		gbc_originalTitleNewMovieGTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_originalTitleNewMovieGTextField.gridx = 3;
		gbc_originalTitleNewMovieGTextField.gridy = 0;
		introDataMoviePanel.add(originalTitleNewMovieGTextField, gbc_originalTitleNewMovieGTextField);
		originalTitleNewMovieGTextField.setColumns(10);
		
		JPanel posterPanel = new JPanel();
		posterPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_posterPanel = new GridBagConstraints();
		gbc_posterPanel.fill = GridBagConstraints.BOTH;
		gbc_posterPanel.gridwidth = 3;
		gbc_posterPanel.gridheight = 4;
		gbc_posterPanel.insets = new Insets(0, 0, 5, 0);
		gbc_posterPanel.gridx = 5;
		gbc_posterPanel.gridy = 0;
		introDataMoviePanel.add(posterPanel, gbc_posterPanel);
		posterPanel.setLayout(new BorderLayout(0, 0));
		
		imagePosterLabel = new JLabel("");
		posterPanel.add(imagePosterLabel, BorderLayout.CENTER);
		
		JLabel lblFechaDeEstreno = new JLabel("Fecha de estreno:");
		GridBagConstraints gbc_lblFechaDeEstreno = new GridBagConstraints();
		gbc_lblFechaDeEstreno.anchor = GridBagConstraints.NORTH;
		gbc_lblFechaDeEstreno.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeEstreno.gridx = 0;
		gbc_lblFechaDeEstreno.gridy = 1;
		introDataMoviePanel.add(lblFechaDeEstreno, gbc_lblFechaDeEstreno);
		
		releaseDateNewMovie = new GDate();
		GridBagConstraints gbc_releaseDateNewMovie = new GridBagConstraints();
		gbc_releaseDateNewMovie.anchor = GridBagConstraints.WEST;
		gbc_releaseDateNewMovie.insets = new Insets(0, 0, 5, 5);
		gbc_releaseDateNewMovie.fill = GridBagConstraints.VERTICAL;
		gbc_releaseDateNewMovie.gridx = 1;
		gbc_releaseDateNewMovie.gridy = 1;
		introDataMoviePanel.add(releaseDateNewMovie, gbc_releaseDateNewMovie);
		//introDataMoviePanel.add(new JScrollPane(sinopsisNewMovieTextArea), gbc_textArea);
		
		JLabel lblLema = new JLabel("Lema:");
		GridBagConstraints gbc_lblLema = new GridBagConstraints();
		gbc_lblLema.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblLema.insets = new Insets(0, 0, 5, 5);
		gbc_lblLema.gridx = 2;
		gbc_lblLema.gridy = 1;
		introDataMoviePanel.add(lblLema, gbc_lblLema);
		
		lemaNewMovieTextField = new JTextField();
		GridBagConstraints gbc_lemaNewMovieTextField = new GridBagConstraints();
		gbc_lemaNewMovieTextField.gridwidth = 2;
		gbc_lemaNewMovieTextField.anchor = GridBagConstraints.NORTH;
		gbc_lemaNewMovieTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lemaNewMovieTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lemaNewMovieTextField.gridx = 3;
		gbc_lemaNewMovieTextField.gridy = 1;
		introDataMoviePanel.add(lemaNewMovieTextField, gbc_lemaNewMovieTextField);
		lemaNewMovieTextField.setColumns(10);
		
		JLabel lblSinopsis = new JLabel("Sinopsis:");
		GridBagConstraints gbc_lblSinopsis = new GridBagConstraints();
		gbc_lblSinopsis.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSinopsis.insets = new Insets(0, 0, 5, 5);
		gbc_lblSinopsis.gridx = 0;
		gbc_lblSinopsis.gridy = 2;
		introDataMoviePanel.add(lblSinopsis, gbc_lblSinopsis);
		
		sinopsisNewMovieTextArea = new JTextArea();
		sinopsisNewMovieTextArea.setRows(5);
		sinopsisNewMovieTextArea.setMaximumSize(new Dimension(400, 100));
		sinopsisNewMovieTextArea.setLineWrap(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 3;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		
		JPanel sinopsisPanel = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(sinopsisNewMovieTextArea);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sinopsisPanel.add(scrollPane, BorderLayout.CENTER);
		introDataMoviePanel.add( sinopsisPanel, gbc_textArea);
		
		idMovieTextField = new JTextField();
		idMovieTextField.setVisible(false);
		
		JLabel lblDuracin = new JLabel("Duración:");
		GridBagConstraints gbc_lblDuracin = new GridBagConstraints();
		gbc_lblDuracin.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDuracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracin.gridx = 2;
		gbc_lblDuracin.gridy = 2;
		introDataMoviePanel.add(lblDuracin, gbc_lblDuracin);
		
		durationTextField = new JTextField();
		GridBagConstraints gbc_durationTextField = new GridBagConstraints();
		gbc_durationTextField.gridwidth = 2;
		gbc_durationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_durationTextField.anchor = GridBagConstraints.NORTH;
		gbc_durationTextField.insets = new Insets(0, 0, 5, 5);
		gbc_durationTextField.gridx = 3;
		gbc_durationTextField.gridy = 2;
		introDataMoviePanel.add(durationTextField, gbc_durationTextField);
		durationTextField.setColumns(5);
		GridBagConstraints gbc_idMovieTextField = new GridBagConstraints();
		gbc_idMovieTextField.anchor = GridBagConstraints.NORTH;
		gbc_idMovieTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idMovieTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idMovieTextField.gridx = 0;
		gbc_idMovieTextField.gridy = 3;
		introDataMoviePanel.add(idMovieTextField, gbc_idMovieTextField);
		idMovieTextField.setColumns(10);
		
		lblPuntuacin = new JLabel("Puntuación:");
		GridBagConstraints gbc_lblPuntuacin = new GridBagConstraints();
		gbc_lblPuntuacin.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblPuntuacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuntuacin.gridx = 2;
		gbc_lblPuntuacin.gridy = 3;
		introDataMoviePanel.add(lblPuntuacin, gbc_lblPuntuacin);
		
		JButton btnExaminar = new JButton("Examinar...");
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onFindFileActionPerformed(e);
			}
		});
		
		JPanel watchedPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) watchedPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_watchedPanel = new GridBagConstraints();
		gbc_watchedPanel.gridwidth = 2;
		gbc_watchedPanel.anchor = GridBagConstraints.NORTH;
		gbc_watchedPanel.insets = new Insets(0, 0, 5, 5);
		gbc_watchedPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_watchedPanel.gridx = 3;
		gbc_watchedPanel.gridy = 3;
		introDataMoviePanel.add(watchedPanel, gbc_watchedPanel);
		
		startsPanel = new StartsPanel();
	   MouseAdapter startPanelListener = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				String starName = ((JLabel)e.getSource()).getName();
				
				if (starName.equals("E1")){
					startsPanel.setPoints(1L);
				}
				if (starName.equals("E2")){
					startsPanel.setPoints(2L);
				}
				if (starName.equals("E3")){
					startsPanel.setPoints(3L);
				}
				if (starName.equals("E4")){
					startsPanel.setPoints(4L);
				}
				if (starName.equals("E5")){
					startsPanel.setPoints(5L);
				}
			}
		};
	    
		//a las estrellas les damos el listener para que actualice
		startsPanel.getStart1().addMouseListener(startPanelListener);
		startsPanel.getStart2().addMouseListener(startPanelListener);
		startsPanel.getStart3().addMouseListener(startPanelListener);
		startsPanel.getStart4().addMouseListener(startPanelListener);
		startsPanel.getStart5().addMouseListener(startPanelListener);

		watchedPanel.add(startsPanel);
		
		lblVista = new JLabel("Vista:");
		GridBagConstraints gbc_lblVista = new GridBagConstraints();
		gbc_lblVista.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblVista.insets = new Insets(0, 0, 0, 5);
		gbc_lblVista.gridx = 2;
		gbc_lblVista.gridy = 4;
		introDataMoviePanel.add(lblVista, gbc_lblVista);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 4;
		introDataMoviePanel.add(panel, gbc_panel);
		
		yesRadio = new JRadioButton("SI");
		panel.add(yesRadio);
		
		noRadio = new JRadioButton("NO");
		panel.add(noRadio);
		
		ButtonGroup group = new ButtonGroup();
		group.add(yesRadio);
		group.add(noRadio);
		
		
		GridBagConstraints gbc_btnExaminar = new GridBagConstraints();
		gbc_btnExaminar.gridwidth = 3;
		gbc_btnExaminar.gridx = 5;
		gbc_btnExaminar.gridy = 4;
		introDataMoviePanel.add(btnExaminar, gbc_btnExaminar);
		if ( MovieDataBaseService.getCountryProductionService().getAllCountryProductionModel() != null){
			for ( CountryProductionModel countryModel : MovieDataBaseService.getCountryProductionService().getAllCountryProductionModel()){
				//countryDataPanel.getTextField().getDataList().add(countryModel.getName());
			}
		}
		if(MovieDataBaseService.getGenreModelService().getGenreModelList() != null){
			for ( GenreModel genreModel : MovieDataBaseService.getGenreModelService().getGenreModelList()){
				//genreDataPanel.getTextField().getDataList().add(genreModel.getName());
			}
		}
		 
		
		
		JPanel savePanelSouth = new JPanel();
		GridBagConstraints gbc_savePanelSouth = new GridBagConstraints();
		gbc_savePanelSouth.anchor = GridBagConstraints.NORTH;
		gbc_savePanelSouth.gridwidth = 4;
		gbc_savePanelSouth.insets = new Insets(0, 0, 0, 5);
		gbc_savePanelSouth.gridx = 1;
		gbc_savePanelSouth.gridy = 4;
		add(savePanelSouth, BorderLayout.SOUTH);
		
		JButton saveNewMovieButton = new JButton("");
		saveNewMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSaveNewMovieActionPerformed(e);
			}
		});
		saveNewMovieButton.setBorder(null);
		saveNewMovieButton.setIcon(new ImageIcon(MainWindow.class.getResource("/dad/filmfanatic/iconos/save.png")));
		savePanelSouth.add(saveNewMovieButton);
		
		JPanel ExtraDataMoviePanel = new JPanel();
		ExtraDataMoviePanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		add(ExtraDataMoviePanel, BorderLayout.CENTER);
		ExtraDataMoviePanel.setLayout(new GridLayout(2, 3, 0, 0));
		
		genresDataPanel = new AddDataPanel( "Géneros", true );
		
		List<GenreModelItem> genreList = MovieDataBaseService.getGenreModelItemService().getGenreModelItemList();
		if ( genreList != null ){
			for( GenreModelItem genreItem : genreList ){
				genresDataPanel.getTextField().getDataList().add(genreItem.getName());
			}
		}
		
		
		
		
		ExtraDataMoviePanel.add(genresDataPanel);
		
		castCrewPeoplePanel = new AddCastCrewPeoplePanel("Producción", "Persona"  , "Función", "name"  , "job");
		ExtraDataMoviePanel.add(castCrewPeoplePanel);
		
		languageDataPanel = new AddDataPanel("Idiomas", true);
		ExtraDataMoviePanel.add(languageDataPanel);
		
		countryDataPanel = new AddDataPanel("Paises", true );
		ExtraDataMoviePanel.add(countryDataPanel);
		
		castCastPeoplePanel = new AddCastCrewPeoplePanel("Reparto", "Persona"  , "Personaje", "name"  , "character");
		ExtraDataMoviePanel.add(castCastPeoplePanel);
		
		companyDataPanel = new AddDataPanel("Compañias", true );
		ExtraDataMoviePanel.add(companyDataPanel);
		
		
		
	}
	
	
	public JTextField getIdMovieTextField() {
		return idMovieTextField;
	}


	public void setIdMovieTextField(JTextField idMovieTextField) {
		this.idMovieTextField = idMovieTextField;
	}


	protected void onSaveNewMovieActionPerformed(ActionEvent e) {
		
		String errors = "";
		
		if ( titleNewMovieGtextField.getText().isEmpty()){
			errors = errors + "Titulo, ";
		}
		
		if ( originalTitleNewMovieGTextField.getText().isEmpty()){
			errors = errors + "Titulo original, ";
		}
		
		try{
			Integer.valueOf(durationTextField.getText() );
		}catch (NumberFormatException ex){
			errors = errors + "Duración no es numérica, ";
		}
		/*
		if ( lemaNewMovieTextField.getText().isEmpty()){
			errors = errors + "Lema, ";
		}
		if ( sinopsisNewMovieTextArea.getText().isEmpty()){
			errors = errors + "Sinópsis, ";
		}
		if ( countryDataPanel.getDataJListModel().getSize() == 0){
			errors = errors + "Paises, ";
		}
		if ( genresDataPanel.getDataJListModel().getSize() == 0){
			errors = errors + "Géneros, ";
		}
		if ( languageDataPanel.getDataJListModel().getSize() == 0){
			errors = errors + "Idiomas, ";
		}
		if ( companyDataPanel.getDataJListModel().getSize() == 0){
			errors = errors + "Compañias, ";
		}
		if ( castCrewPeoplePanel.getListTableModel().getValues().size() == 0){
			errors = errors + "Producción, ";
		}
		if ( castCastPeoplePanel.getListTableModel().getValues().size() == 0){
			errors = errors + "Reparto, ";
		}
		if ( imagePosterLabel.getIcon() == null){
			errors = errors + "Imágen de película, ";
		}*/
		

		
		
		if ( !errors.isEmpty() ){
			errors = errors.substring(0, errors.length() -2 );
			JOptionPane.showMessageDialog(this, "Faltan por rellenar estos campos obligatorios: " + errors ,"Error", JOptionPane.ERROR_MESSAGE);
			errors = "";
			
		}else{
				
			if ( titleActionInMovieLabel.getText().equals("AÑADIR PELÍCULA") ){
				 int option = JOptionPane.showConfirmDialog(this, "¿ Los datos son correctos para la nueva película? ","Confirmación" , JOptionPane.YES_NO_OPTION );
				 
				if ( option == JOptionPane.YES_OPTION){
					if ( yesRadio.isSelected()){
						saveMovieToPC("YES");
					}
					else if ( noRadio.isSelected()){
						saveMovieToPC("NO");
					}
				}
			}
			
			else if ( titleActionInMovieLabel.getText().equals("MODIFICAR PELÍCULA") ){
				
				int option = JOptionPane.showConfirmDialog(this, "¿ Los datos son correctos en su modificación? ","Confirmación" , JOptionPane.YES_NO_OPTION );
					
				if ( option == JOptionPane.YES_OPTION){
					if ( yesRadio.isSelected()){
						updateMovieToPC("YES");
					}
					else if ( noRadio.isSelected()){
						updateMovieToPC("NO");
					}
				}
			}
				
		}
		
		//goToMyMovies();
	}
	
	private void goToMyMovies(){
		//carga panel mis peliculas
	    mainWindow.getSearchMoviePcOrCloudPanel().getTitleHeaderPanel().setBackground(MainWindow.colorParaMisPeliculas);
		CardLayout cl = (CardLayout)(mainWindow.getPanelContainerCardLayout().getLayout());
        cl.show(mainWindow.getPanelContainerCardLayout(), MainWindow.SEARCH_MOVIE);
	}
	
	
	private void updateMovieToPC(String optionWatched){
		
		MovieItem movieItem = getMovieItem();
		
		byte[] array = null ;
		
		if ( imagefile != null ){
				array = ImageUtil.getByteArrayImageFromFile(imagefile);
		}
		
		if ( MovieDataBaseService.getMovieService().updateMovieFromPC(movieItem, tmbd, optionWatched , array )){
			JOptionPane.showMessageDialog(this, "Película actualizada con éxito","Correcto", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "No se ha podido actualizar la película","Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void saveMovieToPC(String optionWatched){
		
		MovieItem movieItem = getMovieItem();
		
		if ( MovieDataBaseService.getMovieService().saveMovieFromPC( movieItem, tmbd, optionWatched , ImageUtil.getByteArrayImageFromFile( imagefile ))){
			JOptionPane.showMessageDialog(this, "Película guardada con éxito","Correcto" , JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "No se ha podido guardar la película","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	private MovieItem getMovieItem(){
		
		MovieItem movieItem = new MovieItem();
		
		if (!idMovieTextField.getText().equals("")){
			movieItem.setId(Long.valueOf(idMovieTextField.getText()));
		}
		movieItem.setTitle( titleNewMovieGtextField.getText());
		movieItem.setReleaseDate( releaseDateNewMovie.getFecha());
		movieItem.setOverview( sinopsisNewMovieTextArea.getText()  )   ;
		movieItem.setOriginalTitle( originalTitleNewMovieGTextField.getText());
		movieItem.setTagLine( lemaNewMovieTextField.getText());
		movieItem.setDuration( Integer.valueOf(durationTextField.getText()));
		
		if (startsPanel.getPoints() != null )
			movieItem.setVoteAverage(startsPanel.getPoints());
			//movieItem.setVoteAverage(.valueOf(startsPanel.getPoints()));

//		//CastPeople
		for ( PersonItem personItem : castCastPeoplePanel.getListTableModel().getValues()  ){
		    movieItem.getCast().add(personItem);
		}
//		
		//CrewPeople
		for ( PersonItem personItem : castCrewPeoplePanel.getListTableModel().getValues() ){
		    movieItem.getCrew().add(personItem);
		}
		
		//GenreList
		for ( GenreItem genreItem : MovieDataBaseService.getGenreModelService().getListGenreItem( genresDataPanel.getDataJListModel())){
			movieItem.getGenres().add(genreItem);
		}
		
		//LanguageItem
		for (LanguageItem languageItem : MovieDataBaseService.getSpokenLanguageService().getListSpokenLanguagesItem( languageDataPanel.getDataJListModel())){
			movieItem.getSpokenLanguages().add(languageItem);
		}
		
		//Company
		for (CompanyItem companyItem : MovieDataBaseService.getProductionCompaniesService().getListCompanyItem( companyDataPanel.getDataJListModel())){
			movieItem.getProductionCompanies().add(companyItem);
		}
		
		//CountryItem
		for (CountryItem countryItem : MovieDataBaseService.getCountryProductionService().getListCountryItem( countryDataPanel.getDataJListModel())){
			movieItem.getProductionCountries().add(countryItem);
		}
		
		return movieItem;
	}
	
	
	protected void onFindFileActionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Elegir imágen de poster");
		fileChooser.setToolTipText("Elegir una imágen de poster de película");
		fileChooser.showDialog(this,"Abrir");
		
		if ( fileChooser.getSelectedFile() != null ){
			imagePosterLabel.setIcon(ImageUtil.getImageFromFile(fileChooser.getSelectedFile() , 91,  136 ));
			imagefile = fileChooser.getSelectedFile();
		}
	}
	
	

	public JLabel getTitleActionInMovieLabel() {
		return titleActionInMovieLabel;
	}


	public void setTitleActionInMovieLabel(JLabel titleActionInMovieLabel) {
		this.titleActionInMovieLabel = titleActionInMovieLabel;
	}


	public GTextField getTitleNewMovieGtextField() {
		return titleNewMovieGtextField;
	}

	public void setTitleNewMovieGtextField(GTextField titleNewMovieGtextField) {
		this.titleNewMovieGtextField = titleNewMovieGtextField;
	}

	public GTextField getOriginalTitleNewMovieGTextField() {
		return originalTitleNewMovieGTextField;
	}

	public void setOriginalTitleNewMovieGTextField(
			GTextField originalTitleNewMovieGTextField) {
		this.originalTitleNewMovieGTextField = originalTitleNewMovieGTextField;
	}

	public JLabel getImagePosterLabel() {
		return imagePosterLabel;
	}

	public void setImagePosterLabel(JLabel imagePosterLabel) {
		this.imagePosterLabel = imagePosterLabel;
	}

	public GDate getReleaseDateNewMovie() {
		return releaseDateNewMovie;
	}

	public void setReleaseDateNewMovie(GDate releaseDateNewMovie) {
		this.releaseDateNewMovie = releaseDateNewMovie;
	}

	public JTextArea getSinopsisNewMovieTextArea() {
		return sinopsisNewMovieTextArea;
	}

	public void setSinopsisNewMovieTextArea(JTextArea sinopsisNewMovieTextArea) {
		this.sinopsisNewMovieTextArea = sinopsisNewMovieTextArea;
	}

	public JTextField getLemaNewMovieTextField() {
		return lemaNewMovieTextField;
	}

	public void setLemaNewMovieTextField(JTextField lemaNewMovieTextField) {
		this.lemaNewMovieTextField = lemaNewMovieTextField;
	}

	public JTextField getDurationTextField() {
		return durationTextField;
	}

	public void setDurationTextField(JTextField durationTextField) {
		this.durationTextField = durationTextField;
	}

	public JPanel getAddMoviePanel() {
		return addMoviePanel;
	}

	public void setAddMoviePanel(JPanel addMoviePanel) {
		this.addMoviePanel = addMoviePanel;
	}

	public AddCastCrewPeoplePanel getCastCrewPeoplePanel() {
		return castCrewPeoplePanel;
	}

	public void setCastCrewPeoplePanel(AddCastCrewPeoplePanel castCrewPeoplePanel) {
		this.castCrewPeoplePanel = castCrewPeoplePanel;
	}

	public AddDataPanel getGenresDataPanel() {
		return genresDataPanel;
	}

	public void setGenresDataPanel(AddDataPanel genresDataPanel) {
		this.genresDataPanel = genresDataPanel;
	}

	public AddDataPanel getLanguageDataPanel() {
		return languageDataPanel;
	}

	public void setLanguageDataPanel(AddDataPanel languageDataPanel) {
		this.languageDataPanel = languageDataPanel;
	}

	public AddDataPanel getCountryDataPanel() {
		return countryDataPanel;
	}

	public void setCountryDataPanel(AddDataPanel countryDataPanel) {
		this.countryDataPanel = countryDataPanel;
	}

	public AddCastCrewPeoplePanel getCastCastPeoplePanel() {
		return castCastPeoplePanel;
	}

	public void setCastCastPeoplePanel(AddCastCrewPeoplePanel castCastPeoplePanel) {
		this.castCastPeoplePanel = castCastPeoplePanel;
	}

	public AddDataPanel getCompanyDataPanel() {
		return companyDataPanel;
	}

	public void setCompanyDataPanel(AddDataPanel companyDataPanel) {
		this.companyDataPanel = companyDataPanel;
	}

	public File getImagefile() {
		return imagefile;
	}

	public void setImagefile(File imagefile) {
		this.imagefile = imagefile;
	}


	public StartsPanel getStartsPanel() {
		return startsPanel;
	}


	public void setStartsPanel(StartsPanel startsPanel) {
		this.startsPanel = startsPanel;
	}


	public JRadioButton getYesRadio() {
		return yesRadio;
	}


	public void setYesRadio(JRadioButton yesRadio) {
		this.yesRadio = yesRadio;
	}


	public JRadioButton getNoRadio() {
		return noRadio;
	}


	public void setNoRadio(JRadioButton noRadio) {
		this.noRadio = noRadio;
	}


	public JPanel getHeaderTitleAddMoviePanel() {
		return headerTitleAddMoviePanel;
	}


	public void setHeaderTitleAddMoviePanel(JPanel headerTitleAddMoviePanel) {
		this.headerTitleAddMoviePanel = headerTitleAddMoviePanel;
	}
	
	
	
	
	
}
