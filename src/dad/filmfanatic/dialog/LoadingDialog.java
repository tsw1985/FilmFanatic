package dad.filmfanatic.dialog;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

import dad.filmfanatic.utils.Iconos;
import java.awt.Color;

public class LoadingDialog extends JDialog {
	
	public LoadingDialog(String text){
		getContentPane().setBackground(Color.CYAN);
		
		JLabel waiting = new JLabel();
		JLabel waitingText = new JLabel(text);
		waiting.setIcon(Iconos.WAITING);
		
    	setUndecorated(true);
    	setSize(150 , 45);
    	setLocationRelativeTo(null);
    	getContentPane().setLayout(new FlowLayout( FlowLayout.CENTER ));
    	
    	getContentPane().add(waiting);
    	getContentPane().add(waitingText);

	}
	
	
	

}
