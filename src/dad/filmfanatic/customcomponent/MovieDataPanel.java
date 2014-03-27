package dad.filmfanatic.customcomponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dad.filmfanatic.model.MovieModel;



public class MovieDataPanel extends JPanel{
	
	private MovieModel movieModel;
	private JLabel titleMovieLabel;
	private JLabel originalTitleMovielLabel;
	private JLabel posterLabel;
	
	private int positionMovieInJTable;
	private JButton addMovieButton;
	private JButton detailButton;
	private JButton deleteButton;
	private GDate releaseDate;
	private JTextArea sinopsisTextArea;
	private StartsPanel startsPanel;

	public MovieDataPanel( MovieModel movieListModel) {
		this.movieModel = movieListModel;
		initComponent();
	}
	
	
	private void initComponent(){
		
		setBackground(Color.LIGHT_GRAY);
		setBorder( new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout( new BorderLayout(0, 0));
		
		JPanel movieDataPanel = new JPanel();
		movieDataPanel.setBackground(Color.LIGHT_GRAY);
		movieDataPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(movieDataPanel, BorderLayout.CENTER);
		GridBagLayout gbl_movieDataPanel = new GridBagLayout();
		gbl_movieDataPanel.columnWidths = new int[]{598, 83, 0};
		gbl_movieDataPanel.rowHeights = new int[]{0, 0, 37, 0, 57, 0};
		gbl_movieDataPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_movieDataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		movieDataPanel.setLayout(gbl_movieDataPanel);
		
		titleMovieLabel = new JLabel(movieModel.getTitle());
		GridBagConstraints gbc_titleMovieLabel = new GridBagConstraints();
		gbc_titleMovieLabel.anchor = GridBagConstraints.WEST;
		gbc_titleMovieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleMovieLabel.gridx = 0;
		gbc_titleMovieLabel.gridy = 0;
		movieDataPanel.add(titleMovieLabel, gbc_titleMovieLabel);
		
		JPanel watchedNoWatchedPanel = new JPanel();
		watchedNoWatchedPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_watchedNoWatchedPanel = new GridBagConstraints();
		gbc_watchedNoWatchedPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_watchedNoWatchedPanel.insets = new Insets(0, 0, 5, 0);
		gbc_watchedNoWatchedPanel.anchor = GridBagConstraints.NORTH;
		gbc_watchedNoWatchedPanel.gridx = 1;
		gbc_watchedNoWatchedPanel.gridy = 0;
		movieDataPanel.add(watchedNoWatchedPanel, gbc_watchedNoWatchedPanel);
		
		addMovieButton = new JButton("");
		addMovieButton.setBorder(null);
		addMovieButton.setIcon(new ImageIcon(MovieDataPanel.class.getResource("/dad/filmfanatic/iconos/addmovielikewatched.png")));
		
			watchedNoWatchedPanel.add(addMovieButton);
			
			detailButton = new JButton("");
			detailButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			watchedNoWatchedPanel.add(detailButton);
			detailButton.setBorder(null);
			detailButton.setIcon(new ImageIcon(MovieDataPanel.class.getResource("/dad/filmfanatic/iconos/detail20x20.png")));
			
			deleteButton = new JButton("");
			watchedNoWatchedPanel.add(deleteButton);
			deleteButton.setBorder(null);
			deleteButton.setIcon(new ImageIcon(MovieDataPanel.class.getResource("/dad/filmfanatic/iconos/remove20x20.png")));
		
		originalTitleMovielLabel = new JLabel(movieModel.getOriginalTitle());
		GridBagConstraints gbc_originalTitleMovielLabel = new GridBagConstraints();
		gbc_originalTitleMovielLabel.anchor = GridBagConstraints.WEST;
		gbc_originalTitleMovielLabel.insets = new Insets(0, 0, 5, 5);
		gbc_originalTitleMovielLabel.gridx = 0;
		gbc_originalTitleMovielLabel.gridy = 1;
		movieDataPanel.add(originalTitleMovielLabel, gbc_originalTitleMovielLabel);
		
		releaseDate = new GDate();
		releaseDate.setFecha(movieModel.getReleaseDate());
		releaseDate.getDiaCombo().setEnabled(false);
		releaseDate.getMesCombo().setEnabled(false);
		releaseDate.getAnyoCombo().setEnabled(false);
		GridBagConstraints gbc_releaseDate = new GridBagConstraints();
		gbc_releaseDate.anchor = GridBagConstraints.NORTHWEST;
		gbc_releaseDate.insets = new Insets(0, 0, 5, 5);
		gbc_releaseDate.gridx = 0;
		gbc_releaseDate.gridy = 2;
		movieDataPanel.add(releaseDate, gbc_releaseDate);
		
		startsPanel = new StartsPanel();
		GridBagConstraints gbc_startsPanel = new GridBagConstraints();
		gbc_startsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_startsPanel.fill = GridBagConstraints.BOTH;
		gbc_startsPanel.gridx = 1;
		gbc_startsPanel.gridy = 2;
		movieDataPanel.add(startsPanel, gbc_startsPanel);
		
		sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setRows(3);
		sinopsisTextArea.setLineWrap(true);
		GridBagConstraints gbc_sinopsisTextArea = new GridBagConstraints();
		gbc_sinopsisTextArea.gridwidth = 2;
		gbc_sinopsisTextArea.gridheight = 2;
		gbc_sinopsisTextArea.fill = GridBagConstraints.BOTH;
		gbc_sinopsisTextArea.gridx = 0;
		gbc_sinopsisTextArea.gridy = 3;
		movieDataPanel.add(new JScrollPane(sinopsisTextArea), gbc_sinopsisTextArea);
		
		JPanel imageMoviePanel = new JPanel();
		imageMoviePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(imageMoviePanel, BorderLayout.WEST);
		imageMoviePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		imageMoviePanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		posterLabel = new JLabel();
		panel.add(posterLabel, BorderLayout.CENTER);
		
	}
	

	public MovieModel getMovieModel() {
		return movieModel;
	}


	public void setMovieModel(MovieModel movieModel) {
		this.movieModel = movieModel;
	}


	public JLabel getLblTitulolabel() {
		return titleMovieLabel;
	}


	public void setLblTitulolabel(JLabel lblTitulolabel) {
		this.titleMovieLabel = lblTitulolabel;
	}


	public JLabel getLblDirectorlabel() {
		return originalTitleMovielLabel;
	}


	public void setLblDirectorlabel(JLabel lblDirectorlabel) {
		this.originalTitleMovielLabel = lblDirectorlabel;
	}

	public JLabel getPosterLabel() {
		return posterLabel;
	}


	public void setPosterLabel(JLabel posterLabel) {
		this.posterLabel = posterLabel;
	}


	public int getPositionMovieInJTable() {
		return positionMovieInJTable;
	}


	public void setPositionMovieInJTable(int positionMovieInJTable) {
		this.positionMovieInJTable = positionMovieInJTable;
	}


	public JButton getAddMovieButton() {
		return addMovieButton;
	}


	public void setAddMovieButton(JButton addMovieButton) {
		this.addMovieButton = addMovieButton;
	}


	public JButton getDetailButton() {
		return detailButton;
	}


	public void setDetailButton(JButton detailButton) {
		this.detailButton = detailButton;
	}


	public JButton getDeleteButton() {
		return deleteButton;
	}


	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}


	public JLabel getTitleMovieLabel() {
		return titleMovieLabel;
	}


	public void setTitleMovieLabel(JLabel titleMovieLabel) {
		this.titleMovieLabel = titleMovieLabel;
	}


	public JLabel getOriginalTitleMovielLabel() {
		return originalTitleMovielLabel;
	}


	public void setOriginalTitleMovielLabel(JLabel originalTitleMovielLabel) {
		this.originalTitleMovielLabel = originalTitleMovielLabel;
	}


	public GDate getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(GDate releaseDate) {
		this.releaseDate = releaseDate;
	}


	public JTextArea getSinopsisTextArea() {
		return sinopsisTextArea;
	}


	public void setSinopsisTextArea(JTextArea sinopsisTextArea) {
		this.sinopsisTextArea = sinopsisTextArea;
	}


	public StartsPanel getStartsPanel() {
		return startsPanel;
	}


	public void setStartsPanel(StartsPanel startsPanel) {
		this.startsPanel = startsPanel;
	}



	
}