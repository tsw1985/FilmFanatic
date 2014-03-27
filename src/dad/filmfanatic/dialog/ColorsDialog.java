package dad.filmfanatic.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dad.filmfanatic.window.MainWindow;

public class ColorsDialog extends JDialog{
	
	
	private Color newMovieColor;
	private Color updateMovieColor;
	private Color movieDataBaseColor;
	private Color myMoviesColor;
	
	private JPanel newMovieColor_1;
	private JPanel updateMovieColor_1;
	private JPanel movieDataBaseColor_1;
	private JRadioButton newMovieRadio;
	private JRadioButton updateMovieRadio;
	private JRadioButton databaseRadio;
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	
	private MainWindow mainWindow;
	private JPanel myMoviesColorPanel;
	private JRadioButton rdbtnMisPelculas;
	private JButton guardarColores;
	private JButton cerrarDialogo;

	
	
	
	
	public ColorsDialog(MainWindow mainWindow){
		
		
		
		
		
		this.mainWindow = mainWindow;
		
		setSize(469, 373);
		setTitle("Personalizar FilmFanatic");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		guardarColores = new JButton("Guardar ");
		panel_1.add(guardarColores);
		
		cerrarDialogo = new JButton("Cerrar");
		
		panel_1.add(cerrarDialogo);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{251, 27, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_2.add(panel_3, gbc_panel_3);
		
		JLabel lblSeleccioneSeccin = new JLabel("Seleccione sección:");
		lblSeleccioneSeccin.setFont(new Font("Dialog", Font.BOLD, 19));
		panel_3.add(lblSeleccioneSeccin);
		
		newMovieRadio = new JRadioButton("Nueva Película:");
		GridBagConstraints gbc_newMovieRadio = new GridBagConstraints();
		gbc_newMovieRadio.anchor = GridBagConstraints.NORTHEAST;
		gbc_newMovieRadio.insets = new Insets(0, 0, 5, 5);
		gbc_newMovieRadio.gridx = 0;
		gbc_newMovieRadio.gridy = 2;
		panel_2.add(newMovieRadio, gbc_newMovieRadio);
		
		newMovieColor_1 = new JPanel();
		newMovieColor_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_newMovieColor_1 = new GridBagConstraints();
		gbc_newMovieColor_1.fill = GridBagConstraints.BOTH;
		gbc_newMovieColor_1.insets = new Insets(0, 0, 5, 0);
		gbc_newMovieColor_1.gridx = 1;
		gbc_newMovieColor_1.gridy = 2;
		
		panel_2.add(newMovieColor_1, gbc_newMovieColor_1);
		
		updateMovieRadio = new JRadioButton("Modificar Película:");
		GridBagConstraints gbc_updateMovieRadio = new GridBagConstraints();
		gbc_updateMovieRadio.anchor = GridBagConstraints.NORTHEAST;
		gbc_updateMovieRadio.insets = new Insets(0, 0, 5, 5);
		gbc_updateMovieRadio.gridx = 0;
		gbc_updateMovieRadio.gridy = 3;
		panel_2.add(updateMovieRadio, gbc_updateMovieRadio);
		
		updateMovieColor_1 = new JPanel();
		updateMovieColor_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_updateMovieColor_1 = new GridBagConstraints();
		gbc_updateMovieColor_1.insets = new Insets(0, 0, 5, 0);
		gbc_updateMovieColor_1.fill = GridBagConstraints.BOTH;
		gbc_updateMovieColor_1.gridx = 1;
		gbc_updateMovieColor_1.gridy = 3;
		panel_2.add(updateMovieColor_1, gbc_updateMovieColor_1);
		
		databaseRadio = new JRadioButton("Buscar en Movie Data Base");
		GridBagConstraints gbc_databaseRadio = new GridBagConstraints();
		gbc_databaseRadio.anchor = GridBagConstraints.NORTHEAST;
		gbc_databaseRadio.insets = new Insets(0, 0, 5, 5);
		gbc_databaseRadio.gridx = 0;
		gbc_databaseRadio.gridy = 4;
		panel_2.add(databaseRadio, gbc_databaseRadio);
		
		movieDataBaseColor_1 = new JPanel();
		movieDataBaseColor_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_movieDataBaseColor_1 = new GridBagConstraints();
		gbc_movieDataBaseColor_1.insets = new Insets(0, 0, 5, 0);
		gbc_movieDataBaseColor_1.fill = GridBagConstraints.BOTH;
		gbc_movieDataBaseColor_1.gridx = 1;
		gbc_movieDataBaseColor_1.gridy = 4;
		panel_2.add(movieDataBaseColor_1, gbc_movieDataBaseColor_1);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblPersonalizarColoresElija = new JLabel("Personalizar Colores");
		panel.add(lblPersonalizarColoresElija);
		
		
		
		
		rdbtnMisPelculas = new JRadioButton("Mis películas");
		GridBagConstraints gbc_rdbtnMisPelculas = new GridBagConstraints();
		gbc_rdbtnMisPelculas.anchor = GridBagConstraints.EAST;
		gbc_rdbtnMisPelculas.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMisPelculas.gridx = 0;
		gbc_rdbtnMisPelculas.gridy = 5;
		panel_2.add(rdbtnMisPelculas, gbc_rdbtnMisPelculas);
		
		ButtonGroup group = new ButtonGroup();
		group.add(newMovieRadio);
		group.add(updateMovieRadio);
		group.add(databaseRadio);
		group.add(rdbtnMisPelculas);
		
		
		myMoviesColorPanel = new JPanel();
		myMoviesColorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_myMoviesColor = new GridBagConstraints();
		gbc_myMoviesColor.insets = new Insets(0, 0, 5, 0);
		gbc_myMoviesColor.fill = GridBagConstraints.BOTH;
		gbc_myMoviesColor.gridx = 1;
		gbc_myMoviesColor.gridy = 5;
		panel_2.add(myMoviesColorPanel, gbc_myMoviesColor);
		
		JLabel lblRojo = new JLabel("Rojo:");
		GridBagConstraints gbc_lblRojo = new GridBagConstraints();
		gbc_lblRojo.anchor = GridBagConstraints.EAST;
		gbc_lblRojo.insets = new Insets(0, 0, 5, 5);
		gbc_lblRojo.gridx = 0;
		gbc_lblRojo.gridy = 6;
		panel_2.add(lblRojo, gbc_lblRojo);
		
		redSlider = new JSlider();
		redSlider.setMaximum(255);
		redSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeColor(e);
			}
		});
		GridBagConstraints gbc_redSlider = new GridBagConstraints();
		gbc_redSlider.insets = new Insets(0, 0, 5, 0);
		gbc_redSlider.gridx = 1;
		gbc_redSlider.gridy = 6;
		panel_2.add(redSlider, gbc_redSlider);
		
		JLabel lblVerde = new JLabel("Verde:");
		GridBagConstraints gbc_lblVerde = new GridBagConstraints();
		gbc_lblVerde.anchor = GridBagConstraints.EAST;
		gbc_lblVerde.insets = new Insets(0, 0, 5, 5);
		gbc_lblVerde.gridx = 0;
		gbc_lblVerde.gridy = 7;
		panel_2.add(lblVerde, gbc_lblVerde);
		
		greenSlider = new JSlider();
		greenSlider.setMaximum(255);
		greenSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeColor(e);
			}
		});
		GridBagConstraints gbc_greenSlider = new GridBagConstraints();
		gbc_greenSlider.insets = new Insets(0, 0, 5, 0);
		gbc_greenSlider.gridx = 1;
		gbc_greenSlider.gridy = 7;
		panel_2.add(greenSlider, gbc_greenSlider);
		
		JLabel lblAzul = new JLabel("Azul:");
		GridBagConstraints gbc_lblAzul = new GridBagConstraints();
		gbc_lblAzul.anchor = GridBagConstraints.EAST;
		gbc_lblAzul.insets = new Insets(0, 0, 0, 5);
		gbc_lblAzul.gridx = 0;
		gbc_lblAzul.gridy = 8;
		panel_2.add(lblAzul, gbc_lblAzul);
		
		blueSlider = new JSlider();
		blueSlider.setMaximum(255);
		blueSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeColor(e);
			}
		});
		GridBagConstraints gbc_blueSlider = new GridBagConstraints();
		gbc_blueSlider.gridx = 1;
		gbc_blueSlider.gridy = 8;
		panel_2.add(blueSlider, gbc_blueSlider);
		
		
	}
	

	protected void changeColor(ChangeEvent e) {
		
		int red   = redSlider.getValue();
		int green = greenSlider.getValue();
		int blue  = blueSlider.getValue() ;
		
		Color color = new Color(red,green,blue);
		
		if ( newMovieRadio.isSelected() ){
			newMovieColor_1.setBackground(color);
			MainWindow.colorParaNuevaPelicula = color;
			//mainWindow.getAddEditFromPCMoviePanel().getHeaderTitleAddMoviePanel().repaint();
		}
		else if ( updateMovieRadio.isSelected()){
			updateMovieColor_1.setBackground(color);
			MainWindow.colorParaModificarPelicula = color;
			//mainWindow.getAddEditFromPCMoviePanel().getHeaderTitleAddMoviePanel().repaint();
		}
		else if ( databaseRadio.isSelected() ) {
			movieDataBaseColor_1.setBackground(color);
			MainWindow.colorParaMovieDataBase = color;
			//mainWindow.getSearchMoviePcOrCloudPanel().getTitleHeaderPanel().repaint();
		}
		else if ( rdbtnMisPelculas.isSelected()){
			myMoviesColorPanel.setBackground(color);
			MainWindow.colorParaMisPeliculas = color;
			//mainWindow.getSearchMoviePcOrCloudPanel().getTitleHeaderPanel().repaint();
		}
		
	}


	protected void onSaveColor(ActionEvent e) {
		
		
		
		
		
	}


	public Color getNewMovieColor() {
		return newMovieColor;
	}


	public void setNewMovieColor(Color newMovieColor) {
		this.newMovieColor = newMovieColor;
	}


	public Color getUpdateMovieColor() {
		return updateMovieColor;
	}


	public void setUpdateMovieColor(Color updateMovieColor) {
		this.updateMovieColor = updateMovieColor;
	}


	public Color getMovieDataBaseColor() {
		return movieDataBaseColor;
	}


	public void setMovieDataBaseColor(Color movieDataBaseColor) {
		this.movieDataBaseColor = movieDataBaseColor;
	}


	public Color getMyMoviesColor() {
		return myMoviesColor;
	}


	public void setMyMoviesColor(Color myMoviesColor) {
		this.myMoviesColor = myMoviesColor;
	}


	public JPanel getNewMovieColor_1() {
		return newMovieColor_1;
	}


	public void setNewMovieColor_1(JPanel newMovieColor_1) {
		this.newMovieColor_1 = newMovieColor_1;
	}


	public JPanel getUpdateMovieColor_1() {
		return updateMovieColor_1;
	}


	public void setUpdateMovieColor_1(JPanel updateMovieColor_1) {
		this.updateMovieColor_1 = updateMovieColor_1;
	}


	public JPanel getMovieDataBaseColor_1() {
		return movieDataBaseColor_1;
	}


	public void setMovieDataBaseColor_1(JPanel movieDataBaseColor_1) {
		this.movieDataBaseColor_1 = movieDataBaseColor_1;
	}


	public JRadioButton getNewMovieRadio() {
		return newMovieRadio;
	}


	public void setNewMovieRadio(JRadioButton newMovieRadio) {
		this.newMovieRadio = newMovieRadio;
	}


	public JRadioButton getUpdateMovieRadio() {
		return updateMovieRadio;
	}


	public void setUpdateMovieRadio(JRadioButton updateMovieRadio) {
		this.updateMovieRadio = updateMovieRadio;
	}


	public JRadioButton getDatabaseRadio() {
		return databaseRadio;
	}


	public void setDatabaseRadio(JRadioButton databaseRadio) {
		this.databaseRadio = databaseRadio;
	}


	public JSlider getRedSlider() {
		return redSlider;
	}


	public void setRedSlider(JSlider redSlider) {
		this.redSlider = redSlider;
	}


	public JSlider getGreenSlider() {
		return greenSlider;
	}


	public void setGreenSlider(JSlider greenSlider) {
		this.greenSlider = greenSlider;
	}


	public JSlider getBlueSlider() {
		return blueSlider;
	}


	public void setBlueSlider(JSlider blueSlider) {
		this.blueSlider = blueSlider;
	}


	public MainWindow getMainWindow() {
		return mainWindow;
	}


	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}


	public JPanel getMyMoviesColorPanel() {
		return myMoviesColorPanel;
	}


	public void setMyMoviesColorPanel(JPanel myMoviesColorPanel) {
		this.myMoviesColorPanel = myMoviesColorPanel;
	}


	public JRadioButton getRdbtnMisPelculas() {
		return rdbtnMisPelculas;
	}


	public void setRdbtnMisPelculas(JRadioButton rdbtnMisPelculas) {
		this.rdbtnMisPelculas = rdbtnMisPelculas;
	}


	public JButton getGuardarColores() {
		return guardarColores;
	}


	public void setGuardarColores(JButton guardarColores) {
		this.guardarColores = guardarColores;
	}


	public JButton getCerrarDialogo() {
		return cerrarDialogo;
	}


	public void setCerrarDialogo(JButton cerrarDialogo) {
		this.cerrarDialogo = cerrarDialogo;
	}

	
	
}