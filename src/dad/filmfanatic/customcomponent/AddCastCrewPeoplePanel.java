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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dad.filmfanatic.items.PersonItem;
import dad.filmfanatic.utils.ListTableModel;

public class AddCastCrewPeoplePanel extends JPanel {
	
	private GTextField itemTextField;
	private JTable table;
	
	
	private DefaultTableModel defaultListTableModel ;
	private ListTableModel<PersonItem> listTableModel ;
	private JScrollPane scroll;



	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public AddCastCrewPeoplePanel( String borderTitle , String titleFirstColumn , String titleSecondColumn , String firstField , String secondField ) {
		
		listTableModel = new ListTableModel<PersonItem>();
		listTableModel.addColumn(titleFirstColumn  , String.class, true , firstField );
		listTableModel.addColumn(titleSecondColumn , String.class, true , secondField);
		
		
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, borderTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		itemTextField = new GTextField(250, 80 , false);
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
				onAddItemActionPerformed(e);
			}
		});
		addButton.setIcon(new ImageIcon(AddCastCrewPeoplePanel.class.getResource("/dad/filmfanatic/iconos/addmovielikewatched.png")));
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 0);
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 0;
		panel.add(addButton, gbc_addButton);
		
		table = new JTable(listTableModel);
		table.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e){
				onKeyTableKeyReleased(e);
			}
			
		});
		
		
		table.setFillsViewportHeight(true);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 4;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		
		scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll, gbc_table);
		
		JLabel removeButton = new JLabel("");
		removeButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				onRemoveActionPerformed(e);
			}

		});
		
		removeButton.setIcon(new ImageIcon(AddCastCrewPeoplePanel.class.getResource("/dad/filmfanatic/iconos/remove20x20.png")));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.gridx = 1;
		gbc_removeButton.gridy = 4;
		panel.add(removeButton, gbc_removeButton);
	}

	protected void onKeyTableKeyReleased(KeyEvent e) {
		if ( e.getKeyCode() == KeyEvent.VK_ENTER){
			itemTextField.requestFocus();
		}
	}

	protected void onKeyReleased(KeyEvent e) {
		
		if ( e.getKeyCode() == KeyEvent.VK_ENTER){
			addItem();
		}
		
	}

	protected void onRemoveActionPerformed(MouseEvent e) {
		if( table.getSelectedRow() != -1  && table.getRowCount() > 0  ){
			  listTableModel.getValues().remove(table.getSelectedRow());
			  table.updateUI();
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila","Seleccione una fila" , JOptionPane.ERROR_MESSAGE);
		}
		
	}

	protected void onAddItemActionPerformed(MouseEvent e) {
		addItem();
	}
	
	
	private void addItem(){
		
		if ( ! itemTextField.getText().isEmpty() ){
			PersonItem person = new PersonItem();
			person.setName(itemTextField.getText());
			person.setJob("");
			
			listTableModel.getValues().add(person);
			itemTextField.setText("");
			
			table.requestFocus();
			table.setRowSelectionInterval( table.getRowCount() -1  , table.getRowCount() -1 );
			table.editCellAt(table.getRowCount() -1 , 1);
			table.updateUI();	
		}
		
	}
	
	public DefaultTableModel getDefaultListTableModel() {
		return defaultListTableModel;
	}

	public void setDefaultListTableModel(DefaultTableModel defaultListTableModel) {
		this.defaultListTableModel = defaultListTableModel;
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ListTableModel<PersonItem> getListTableModel() {
		return listTableModel;
	}

	public void setListTableModel(ListTableModel<PersonItem> listTableModel) {
		this.listTableModel = listTableModel;
	}
	


}
