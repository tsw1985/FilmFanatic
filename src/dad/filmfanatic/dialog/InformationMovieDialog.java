package dad.filmfanatic.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class InformationMovieDialog extends JDialog{
	
	private JTextField titleTextField;
	private JTextField originalTitleTextField;
	private JTextField directorTextField;
	private JTextField genreTextField;
	private JTextField releasedDateTextField;
	private JTextField lemaTextField;
	private JLabel imageLabel;
	
	private JList<String> productorList;
	private JList<String> countryList;
	private JList<String> castPeopleList;
	private JList<String> productionPeopleList;
	private JList<String> languagesList;
	
	private JTextArea sinopsisTextArea;
	

	public InformationMovieDialog() {
		
		setSize(800,594);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		GridBagLayout gbl_headerPanel = new GridBagLayout();
		gbl_headerPanel.columnWidths = new int[]{107, 0, 0, 0};
		gbl_headerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 110, 207, 0};
		gbl_headerPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_headerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		headerPanel.setLayout(gbl_headerPanel);
		
		JPanel imagePanel = new JPanel();
		GridBagConstraints gbc_imagePanel = new GridBagConstraints();
		gbc_imagePanel.gridheight = 6;
		gbc_imagePanel.insets = new Insets(0, 0, 5, 5);
		gbc_imagePanel.fill = GridBagConstraints.BOTH;
		gbc_imagePanel.gridx = 0;
		gbc_imagePanel.gridy = 0;
		headerPanel.add(imagePanel, gbc_imagePanel);
		imagePanel.setLayout(new BorderLayout(0, 0));
		
		imageLabel = new JLabel("");
		imageLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		imagePanel.add(imageLabel);
		
		JLabel tituloLabel = new JLabel("Titulo");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.anchor = GridBagConstraints.EAST;
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 1;
		gbc_tituloLabel.gridy = 0;
		headerPanel.add(tituloLabel, gbc_tituloLabel);
		
		titleTextField = new JTextField();
		titleTextField.setEnabled(false);
		titleTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.anchor = GridBagConstraints.NORTH;
		gbc_titleTextField.insets = new Insets(0, 0, 5, 0);
		gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleTextField.gridx = 2;
		gbc_titleTextField.gridy = 0;
		headerPanel.add(titleTextField, gbc_titleTextField);
		titleTextField.setColumns(10);
		
		JLabel yearLabel = new JLabel("Título original");
		GridBagConstraints gbc_yearLabel = new GridBagConstraints();
		gbc_yearLabel.anchor = GridBagConstraints.EAST;
		gbc_yearLabel.insets = new Insets(0, 0, 5, 5);
		gbc_yearLabel.gridx = 1;
		gbc_yearLabel.gridy = 1;
		headerPanel.add(yearLabel, gbc_yearLabel);
		
		originalTitleTextField = new JTextField();
		originalTitleTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		originalTitleTextField.setEnabled(false);
		GridBagConstraints gbc_originalTitleTextField = new GridBagConstraints();
		gbc_originalTitleTextField.anchor = GridBagConstraints.NORTH;
		gbc_originalTitleTextField.insets = new Insets(0, 0, 5, 0);
		gbc_originalTitleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_originalTitleTextField.gridx = 2;
		gbc_originalTitleTextField.gridy = 1;
		headerPanel.add(originalTitleTextField, gbc_originalTitleTextField);
		originalTitleTextField.setColumns(10);
		
		JLabel directorLabel = new JLabel("Director");
		GridBagConstraints gbc_directorLabel = new GridBagConstraints();
		gbc_directorLabel.anchor = GridBagConstraints.EAST;
		gbc_directorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_directorLabel.gridx = 1;
		gbc_directorLabel.gridy = 2;
		headerPanel.add(directorLabel, gbc_directorLabel);
		
		directorTextField = new JTextField();
		directorTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		directorTextField.setEnabled(false);
		GridBagConstraints gbc_directorTextField = new GridBagConstraints();
		gbc_directorTextField.anchor = GridBagConstraints.NORTH;
		gbc_directorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_directorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_directorTextField.gridx = 2;
		gbc_directorTextField.gridy = 2;
		headerPanel.add(directorTextField, gbc_directorTextField);
		directorTextField.setColumns(10);
		
		JLabel genreLabel = new JLabel("Genero");
		GridBagConstraints gbc_genreLabel = new GridBagConstraints();
		gbc_genreLabel.anchor = GridBagConstraints.EAST;
		gbc_genreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_genreLabel.gridx = 1;
		gbc_genreLabel.gridy = 3;
		headerPanel.add(genreLabel, gbc_genreLabel);
		
		genreTextField = new JTextField();
		genreTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		genreTextField.setEnabled(false);
		GridBagConstraints gbc_genreTextField = new GridBagConstraints();
		gbc_genreTextField.anchor = GridBagConstraints.NORTH;
		gbc_genreTextField.insets = new Insets(0, 0, 5, 0);
		gbc_genreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_genreTextField.gridx = 2;
		gbc_genreTextField.gridy = 3;
		headerPanel.add(genreTextField, gbc_genreTextField);
		genreTextField.setColumns(10);
		
		JLabel lblLema = new JLabel("Lema:");
		GridBagConstraints gbc_lblLema = new GridBagConstraints();
		gbc_lblLema.anchor = GridBagConstraints.EAST;
		gbc_lblLema.insets = new Insets(0, 0, 5, 5);
		gbc_lblLema.gridx = 1;
		gbc_lblLema.gridy = 4;
		headerPanel.add(lblLema, gbc_lblLema);
		
		lemaTextField = new JTextField();
		lemaTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		lemaTextField.setEnabled(false);
		GridBagConstraints gbc_lemaTextField = new GridBagConstraints();
		gbc_lemaTextField.anchor = GridBagConstraints.NORTH;
		gbc_lemaTextField.insets = new Insets(0, 0, 5, 0);
		gbc_lemaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lemaTextField.gridx = 2;
		gbc_lemaTextField.gridy = 4;
		headerPanel.add(lemaTextField, gbc_lemaTextField);
		lemaTextField.setColumns(10);
		
		JLabel lblHechaEl = new JLabel("Hecha el:");
		GridBagConstraints gbc_lblHechaEl = new GridBagConstraints();
		gbc_lblHechaEl.anchor = GridBagConstraints.EAST;
		gbc_lblHechaEl.insets = new Insets(0, 0, 5, 5);
		gbc_lblHechaEl.gridx = 1;
		gbc_lblHechaEl.gridy = 5;
		headerPanel.add(lblHechaEl, gbc_lblHechaEl);
		
		releasedDateTextField = new JTextField();
		releasedDateTextField.setFont(new Font("Dialog", Font.BOLD, 12));
		releasedDateTextField.setEnabled(false);
		GridBagConstraints gbc_releasedDateTextField = new GridBagConstraints();
		gbc_releasedDateTextField.anchor = GridBagConstraints.NORTH;
		gbc_releasedDateTextField.insets = new Insets(0, 0, 5, 0);
		gbc_releasedDateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_releasedDateTextField.gridx = 2;
		gbc_releasedDateTextField.gridy = 5;
		headerPanel.add(releasedDateTextField, gbc_releasedDateTextField);
		releasedDateTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 6;
		headerPanel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblProductoras = new JLabel("Productoras:");
		panel_2.add(lblProductoras, BorderLayout.NORTH);
		
		productorList = new JList<String>();
		panel_2.add(new JScrollPane(productorList), BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPaises_1 = new JLabel("Paises:");
		panel_3.add(lblPaises_1, BorderLayout.NORTH);
		
		countryList = new JList<String>();
		panel_3.add(new JScrollPane(countryList), BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblActores = new JLabel("Actores:");
		panel_4.add(lblActores, BorderLayout.NORTH);
		
		castPeopleList = new JList<String>();
		panel_4.add(new JScrollPane(castPeopleList), BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblProduccin = new JLabel("Producción:");
		panel_5.add(lblProduccin, BorderLayout.NORTH);
		
		productionPeopleList = new JList<String>();
		panel_5.add(new JScrollPane(productionPeopleList), BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblIdiomas = new JLabel("Idiomas:");
		panel_6.add(lblIdiomas, BorderLayout.NORTH);
		
		languagesList = new JList<String>();
		panel_6.add(new JScrollPane(languagesList), BorderLayout.CENTER);
		
		JLabel lblSinopsis = new JLabel("Sinopsis:");
		GridBagConstraints gbc_lblSinopsis = new GridBagConstraints();
		gbc_lblSinopsis.anchor = GridBagConstraints.NORTH;
		gbc_lblSinopsis.insets = new Insets(0, 0, 0, 5);
		gbc_lblSinopsis.gridx = 0;
		gbc_lblSinopsis.gridy = 7;
		headerPanel.add(lblSinopsis, gbc_lblSinopsis);
		
		sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setFont(new Font("Dialog", Font.BOLD, 12));
		sinopsisTextArea.setEnabled(false);
		sinopsisTextArea.setLineWrap(true);
		GridBagConstraints gbc_sinopsisTextArea = new GridBagConstraints();
		gbc_sinopsisTextArea.gridwidth = 2;
		gbc_sinopsisTextArea.fill = GridBagConstraints.BOTH;
		gbc_sinopsisTextArea.gridx = 1;
		gbc_sinopsisTextArea.gridy = 7;
		headerPanel.add(new JScrollPane(sinopsisTextArea) , gbc_sinopsisTextArea);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setActionCommand("Cerrar");
		panel.add(btnNewButton);
	}


	public JTextField getTitleTextField() {
		return titleTextField;
	}


	public void setTitleTextField(JTextField titleTextField) {
		this.titleTextField = titleTextField;
	}


	public JTextField getOriginalTitleTextField() {
		return originalTitleTextField;
	}


	public void setOriginalTitleTextField(JTextField originalTitleTextField) {
		this.originalTitleTextField = originalTitleTextField;
	}


	public JTextField getDirectorTextField() {
		return directorTextField;
	}


	public void setDirectorTextField(JTextField directorTextField) {
		this.directorTextField = directorTextField;
	}


	public JTextField getGenreTextField() {
		return genreTextField;
	}


	public void setGenreTextField(JTextField genreTextField) {
		this.genreTextField = genreTextField;
	}


	public JTextField getReleasedDateTextField() {
		return releasedDateTextField;
	}


	public void setReleasedDateTextField(JTextField releasedDateTextField) {
		this.releasedDateTextField = releasedDateTextField;
	}


	public JTextField getLemaTextField() {
		return lemaTextField;
	}


	public void setLemaTextField(JTextField lemaTextField) {
		this.lemaTextField = lemaTextField;
	}


	public JLabel getImageLabel() {
		return imageLabel;
	}


	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}


	public JList<String> getProductorList() {
		return productorList;
	}


	public void setProductorList(JList<String> productorList) {
		this.productorList = productorList;
	}


	public JList<String> getCountryList() {
		return countryList;
	}


	public void setCountryList(JList<String> countryList) {
		this.countryList = countryList;
	}


	public JList<String> getCastPeopleList() {
		return castPeopleList;
	}


	public void setCastPeopleList(JList<String> castPeopleList) {
		this.castPeopleList = castPeopleList;
	}


	public JTextArea getSinopsisTextArea() {
		return sinopsisTextArea;
	}


	public void setSinopsisTextArea(JTextArea sinopsisTextArea) {
		this.sinopsisTextArea = sinopsisTextArea;
	}


	public JList<String> getProductionPeopleList() {
		return productionPeopleList;
	}


	public void setProductionPeopleList(JList<String> productionPeopleList) {
		this.productionPeopleList = productionPeopleList;
	}


	public JList<String> getLanguagesList() {
		return languagesList;
	}


	public void setLanguagesList(JList<String> languagesList) {
		this.languagesList = languagesList;
	}
}