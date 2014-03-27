package dad.filmfanatic.customcomponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dad.filmfanatic.dialog.InformationMovieDialog;
import dad.filmfanatic.model.MovieModel;
import dad.filmfanatic.services.MovieDataBaseService;
import dad.filmfanatic.tmdb.TMDb;
import dad.filmfanatic.utils.ListTableModel;
import dad.filmfanatic.window.MainWindow;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MosaicPanelCloud extends JPanel{
	
	
	private MovieModel movieModel;
	private JLabel imageMovieLabel;
	private JButton infoMovieButton;
	private JButton deleteMovieButton;
	private JButton addEditMovieButton;
	private StartsPanel startsPanel;
	
	public MosaicPanelCloud( MovieModel movieModel ) {
		
		this.movieModel = movieModel;
		setBackground(Color.GRAY);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.NORTH);
		
		JLabel titleMovieLabel = new JLabel(movieModel.getTitle());
		
		
		
		panel.add(titleMovieLabel);
		
		JPanel actionInMoviePanel = new JPanel();
		actionInMoviePanel.setBackground(Color.GRAY);
		actionInMoviePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(actionInMoviePanel, BorderLayout.SOUTH);
		GridBagLayout gbl_actionInMoviePanel = new GridBagLayout();
		gbl_actionInMoviePanel.columnWidths = new int[]{61, 1, 0};
		gbl_actionInMoviePanel.rowHeights = new int[]{0, 23, 0};
		gbl_actionInMoviePanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_actionInMoviePanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		actionInMoviePanel.setLayout(gbl_actionInMoviePanel);
		
		startsPanel = new StartsPanel();
		GridBagConstraints gbc_startsPanel = new GridBagConstraints();
		gbc_startsPanel.gridwidth = 2;
		gbc_startsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_startsPanel.fill = GridBagConstraints.BOTH;
		gbc_startsPanel.gridx = 0;
		gbc_startsPanel.gridy = 0;
		actionInMoviePanel.add(startsPanel, gbc_startsPanel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		actionInMoviePanel.add(panel_1, gbc_panel_1);
		
		addEditMovieButton = new JButton("");
		addEditMovieButton.setBorder(null);
		panel_1.add(addEditMovieButton);
		
		infoMovieButton = new JButton("");
		panel_1.add(infoMovieButton);
		
		infoMovieButton.setBorder(null);
		infoMovieButton.setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/detail20x20.png")));
		
		deleteMovieButton = new JButton("");
		panel_1.add(deleteMovieButton);
		deleteMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteMovieButton.setBorder(null);
		deleteMovieButton.setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/remove20x20.png")));
		
		JPanel centralPanel = new JPanel();
		centralPanel.setBackground(Color.GRAY);
		centralPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		add(centralPanel, BorderLayout.CENTER);
		centralPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		imageMovieLabel = new JLabel("");
		imageMovieLabel.setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/world_search.png")));
		centralPanel.add(imageMovieLabel);
	}
	
	
	



	public MovieModel getMovieModel() {
		return movieModel;
	}


	public void setMovieModel(MovieModel movieModel) {
		this.movieModel = movieModel;
	}


	public JLabel getImageMovieLabel() {
		return imageMovieLabel;
	}


	public void setImageMovieLabel(JLabel imageMovieLabel) {
		this.imageMovieLabel = imageMovieLabel;
	}


	public JButton getAddEditMovieButton() {
		return addEditMovieButton;
	}


	public void setAddEditMovieButton(JButton addEditMovieButton) {
		this.addEditMovieButton = addEditMovieButton;
	}


	public JButton getInfoMovieButton() {
		return infoMovieButton;
	}


	public void setInfoMovieButton(JButton infoMovieButton) {
		this.infoMovieButton = infoMovieButton;
	}
	
	public JButton getDeleteMovieButton() {
		return deleteMovieButton;
	}


	public void setDeleteMovieButton(JButton deleteMovieButton) {
		this.deleteMovieButton = deleteMovieButton;
	}

	public StartsPanel getStartsPanel() {
		return startsPanel;
	}

	public void setStartsPanel(StartsPanel startsPanel) {
		this.startsPanel = startsPanel;
	}

}