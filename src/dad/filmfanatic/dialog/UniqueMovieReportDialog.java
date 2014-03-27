package dad.filmfanatic.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import net.sf.jasperreports.engine.JRException;
import dad.filmfanatic.model.MovieModel;
import dad.filmfanatic.reports.MovieMain;
import dad.filmfanatic.services.MovieDataBaseService;
import dad.filmfanatic.utils.ListTableModel;

public class UniqueMovieReportDialog extends JDialog{
	
	private JTable table;
	private JButton cancelButton;
	private JButton reporteButton;
	private ListTableModel<MovieModel> listTableModelMovie;
	private JPanel titlePanel;
	private JLabel lblNewLabel;
	
	public UniqueMovieReportDialog(){
		
		setTitle("Informe de una película");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setModal(true);
		
		listTableModelMovie = new ListTableModel<MovieModel>();
		listTableModelMovie.addColumn("TÍTULO"     , String.class , false , "title");
		listTableModelMovie.addColumn("LEMA"       , String.class , false , "tagLine");
		listTableModelMovie.addColumn("PUNTUACIÓN" , String.class , false , "voteAverage");
		
		
		listTableModelMovie.setValues(MovieDataBaseService.getMovieService().getAllMoviesFromMyPC());
		
		
		
		table = new JTable(listTableModelMovie);
		getContentPane().add(new JScrollPane(table) , BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		reporteButton = new JButton("Generar informe");
		reporteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onGetReporteActionPerformed(e);
			}
		});
		panel.add(reporteButton);
		
		cancelButton = new JButton("Cerrar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(cancelButton);
		
		titlePanel = new JPanel();
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Informe de una película específica");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		titlePanel.add(lblNewLabel);
	}

	protected void onGetReporteActionPerformed(ActionEvent e) {
		
		
			
			if ( table.getSelectedRow() != -1 ){
				
				 SwingUtilities.invokeLater(new Runnable(){

					@Override
					public void run() {

						try{
							int fila = table.getSelectedRow();
							//LoadingDialog loading = new LoadingDialog("Generando informe ...");
							//loading.setVisible(true);
							//loading.setAlwaysOnTop(true);
							MovieMain.reportLikeOneMovie( listTableModelMovie.getValues().get(fila).getId()  );
							//loading.dispose();
							
							dispose();
						}catch(Exception ex){
							message();
						}
						
					}  
				});
				 
				
			}else{
				JOptionPane.showMessageDialog(this, "Seleccione una película para poder crear el informe" , "Seleccione una fila" , JOptionPane.ERROR_MESSAGE );
			}
	}
	
	private void message(){
		JOptionPane.showMessageDialog(this, "No se ha podido generar el informe" , "Error" , JOptionPane.ERROR_MESSAGE );
	}
	
	
	
	
	
	
	
	

}
