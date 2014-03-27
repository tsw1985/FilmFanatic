package dad.filmfanatic.customcomponent;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AddDataPanel extends JPanel {
	
	private GTextField itemTextField;
	private JList<String> dataJList;
	
	

	private DefaultListModel<String> dataJListModel;
	private boolean autocomplete;

	public DefaultListModel<String> getDataJListModel() {
		return dataJListModel;
	}


	public void setDataJListModel(DefaultListModel<String> dataJListModel) {
		this.dataJListModel = dataJListModel;
	}


	public AddDataPanel(String title , boolean autocomplete) {
		this.autocomplete = autocomplete;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		dataJListModel = new DefaultListModel<String>();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		itemTextField = new GTextField(250,80, autocomplete );
		itemTextField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e){
				onKeyReleased(e);
			}
		});
		
		
		GridBagConstraints gbc_itemTextField = new GridBagConstraints();
		gbc_itemTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemTextField.insets = new Insets(0, 0, 5, 5);
		gbc_itemTextField.gridx = 0;
		gbc_itemTextField.gridy = 0;
		panel.add(itemTextField, gbc_itemTextField);
		itemTextField.setColumns(10);
		
		JLabel addButton = new JLabel("");
		addButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				addItemToJListActionPerformed(e);
			}
		});

		addButton.setIcon(new ImageIcon(AddDataPanel.class.getResource("/dad/filmfanatic/iconos/addmovielikewatched.png")));
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 0);
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 0;
		panel.add(addButton, gbc_addButton);
		
		dataJList = new JList<String>(dataJListModel);
		dataJList.setBorder(new EmptyBorder(5, 5, 0, 5));
		GridBagConstraints gbc_dataJList = new GridBagConstraints();
		gbc_dataJList.gridheight = 3;
		gbc_dataJList.insets = new Insets(0, 0, 0, 5);
		gbc_dataJList.fill = GridBagConstraints.BOTH;
		gbc_dataJList.gridx = 0;
		gbc_dataJList.gridy = 1;
		panel.add(new JScrollPane(dataJList), gbc_dataJList);
		
		JLabel removeButton = new JLabel("");
		removeButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				deleteItemActionPerformed(e);
			}
			
		});

		removeButton.setIcon(new ImageIcon(AddDataPanel.class.getResource("/dad/filmfanatic/iconos/remove20x20.png")));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.gridx = 1;
		gbc_removeButton.gridy = 3;
		panel.add(removeButton, gbc_removeButton);
	}

	
	protected void onKeyReleased(KeyEvent e) {
		if ( e.getKeyCode() == KeyEvent.VK_ENTER){
			addItem();
		}
	}


	private void addItem() {
		String item = itemTextField.getText();
		dataJListModel.addElement(item);
		itemTextField.setText("");
	}


	protected void deleteItemActionPerformed(MouseEvent e) {
		if ( dataJList.getSelectedIndex() != -1 ){
			dataJListModel.remove( dataJList.getSelectedIndex() );
		}
	}


	protected void addItemToJListActionPerformed(MouseEvent e) {
		addItem();
	}


	public GTextField getTextField() {
		return itemTextField;
	}

	public void setTextField(GTextField textField) {
		this.itemTextField = textField;
	}
	
	public JList<String> getDataJList() {
		return dataJList;
	}


	public void setDataJList(JList<String> dataJList) {
		this.dataJList = dataJList;
	}
	
	
}