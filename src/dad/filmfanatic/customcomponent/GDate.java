package dad.filmfanatic.customcomponent;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JComboBox;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class GDate extends JPanel {
	
	private JComboBox<Integer> diaCombo;
	private JComboBox<String> mesCombo;
	private JComboBox<Integer> anyoCombo;
	
	private static final int [] DIAS_MES = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	/**
	 * Create the panel.
	 */
	public GDate() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		diaCombo = new JComboBox<Integer>();
		GridBagConstraints gbc_diaCombo = new GridBagConstraints();
		gbc_diaCombo.insets = new Insets(0, 0, 0, 5);
		gbc_diaCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_diaCombo.gridx = 0;
		gbc_diaCombo.gridy = 0;
		add(diaCombo, gbc_diaCombo);
		
		mesCombo = new JComboBox<String>();
		mesCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				onMesComboStateChanged(e);
			}
		});
		GridBagConstraints gbc_mesCombo = new GridBagConstraints();
		gbc_mesCombo.insets = new Insets(0, 0, 0, 5);
		gbc_mesCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_mesCombo.gridx = 1;
		gbc_mesCombo.gridy = 0;
		add(mesCombo, gbc_mesCombo);
		
		anyoCombo = new JComboBox<Integer>();
		anyoCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				onAnyoComboStateChanged(e);
			}
		});
		GridBagConstraints gbc_anyoCombo = new GridBagConstraints();
		gbc_anyoCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_anyoCombo.gridx = 2;
		gbc_anyoCombo.gridy = 0;
		add(anyoCombo, gbc_anyoCombo);

		cargarMeses();
		cargarAnyos();
		cargarDias();
	}
	
	protected void onAnyoComboStateChanged(ItemEvent e) {
		cargarDias();
	}

	protected void onMesComboStateChanged(ItemEvent e) {
		cargarDias();
	}

	private void cargarDias() {
		int diaActual = 1;
		if (diaCombo.getItemCount() > 0) {
			diaActual = (Integer) diaCombo.getSelectedItem(); 
		}
		int mes = mesCombo.getSelectedIndex() + 1;
		int ultimoDia = DIAS_MES[mes - 1];
		if (mes == 2) {
			int year = (Integer) anyoCombo.getSelectedItem();
			if (bisiesto(year)) {
				ultimoDia = 29;
			}
		}
		diaCombo.removeAllItems();
		for (int i = 1; i <= ultimoDia; i++) {
			diaCombo.addItem(i);
		}
		if (diaActual > ultimoDia) diaActual = ultimoDia;
		diaCombo.setSelectedItem(diaActual);
	}
	
	private boolean bisiesto(int anyo) {
		return new GregorianCalendar().isLeapYear(anyo);
	}
	
	private void cargarMeses() {
		mesCombo.addItem("Enero");
		mesCombo.addItem("Febrero");
		mesCombo.addItem("Marzo");
		mesCombo.addItem("Abril");
		mesCombo.addItem("Mayo");
		mesCombo.addItem("Junio");
		mesCombo.addItem("Julio");
		mesCombo.addItem("Agosto");
		mesCombo.addItem("Septiembre");
		mesCombo.addItem("Octubre");
		mesCombo.addItem("Noviembre");
		mesCombo.addItem("Diciembre");
	}
	
	private void cargarAnyos() {
		int anyo = new GregorianCalendar().get(Calendar.YEAR);
		for (int i = anyo; i >= 1900; i--) {
			anyoCombo.addItem(i);
		}
	}
	
	public Date getFecha() {
		int dia = (Integer)diaCombo.getSelectedItem();
		int mes = mesCombo.getSelectedIndex() + 1;
		int anyo = (Integer) anyoCombo.getSelectedItem();
		GregorianCalendar calendar = new GregorianCalendar(anyo, mes - 1, dia);
		return calendar.getTime();
	}
	
	public void setFecha(Date fecha) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int anyo = calendar.get(Calendar.YEAR);		
		diaCombo.setSelectedItem(dia);
		mesCombo.setSelectedIndex(mes - 1);
		anyoCombo.setSelectedItem(anyo);
	}

	public JComboBox<Integer> getDiaCombo() {
		return diaCombo;
	}

	public void setDiaCombo(JComboBox<Integer> diaCombo) {
		this.diaCombo = diaCombo;
	}

	public JComboBox<String> getMesCombo() {
		return mesCombo;
	}

	public void setMesCombo(JComboBox<String> mesCombo) {
		this.mesCombo = mesCombo;
	}

	public JComboBox<Integer> getAnyoCombo() {
		return anyoCombo;
	}

	public void setAnyoCombo(JComboBox<Integer> anyoCombo) {
		this.anyoCombo = anyoCombo;
	}
	
	
	
	
	
}
