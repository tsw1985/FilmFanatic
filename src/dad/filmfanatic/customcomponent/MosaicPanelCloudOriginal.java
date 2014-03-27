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

public class MosaicPanelCloudOriginal extends JPanel{
	
	
	private MovieModel movieModel;
	private JLabel imageMovieLabel;

	private JButton addEditMovieButton;
	private JButton infoMovieButton;
	private JButton deleteMovieButton;

	


	protected void infoMovieActionPerformed(ActionEvent e) {
	}

	public MosaicPanelCloudOriginal( MovieModel movieModel ) {
		
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
		
		addEditMovieButton = new JButton("");
		addEditMovieButton.setBorder(null);
		actionInMoviePanel.add(addEditMovieButton);
		
		infoMovieButton = new JButton("");
		infoMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoMovieActionPerformed(e);
			}
		});
		infoMovieButton.setBorder(null);
		infoMovieButton.setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/detail20x20.png")));
		actionInMoviePanel.add(infoMovieButton);
		
		deleteMovieButton = new JButton("");
		deleteMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteMovieButton.setBorder(null);
		deleteMovieButton.setIcon(new ImageIcon(MosaicPanelCloud.class.getResource("/dad/filmfanatic/iconos/remove.png")));
		actionInMoviePanel.add(deleteMovieButton);
		
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

	
}