package dad.filmfanatic.customcomponent;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class StartsPanel extends JPanel{
	
	private JLabel start1;
	private JLabel start2;
	private JLabel start3;
	private JLabel start4;
	private JLabel start5;
	private Long points;

	Icon grayStart = new ImageIcon(StartsPanel.class.getResource("/dad/filmfanatic/iconos/starGray.png")); 
	Icon yellowStart = new ImageIcon(StartsPanel.class.getResource("/dad/filmfanatic/iconos/star.png")); 
	
	public StartsPanel() {
		setBackground(SystemColor.window);
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		
		start1 = new JLabel("");
		start1.setName("E1");

		start1.setBorder(null);
		start1.setIcon(grayStart);
		add(start1);
		
		start2 = new JLabel("");
		start2.setName("E2");

		start2.setBorder(null);
		start2.setIcon(new ImageIcon(StartsPanel.class.getResource("/dad/filmfanatic/iconos/starGray.png")));
		add(start2);
		
		start3 = new JLabel("");
		start3.setName("E3");

		start3.setBorder(null);
		start3.setIcon(new ImageIcon(StartsPanel.class.getResource("/dad/filmfanatic/iconos/starGray.png")));
		add(start3);
		
		start4 = new JLabel("");
		start4.setName("E4");

		start4.setBorder(null);
		start4.setIcon(new ImageIcon(StartsPanel.class.getResource("/dad/filmfanatic/iconos/starGray.png")));
		add(start4);
		
		start5 = new JLabel("");
		start5.setName("E5");

		start5.setBorder(null);
		start5.setIcon(new ImageIcon(StartsPanel.class.getResource("/dad/filmfanatic/iconos/starGray.png")));
		add(start5);
		
		//disableButtons();
		
	}
	
	
	public Long getPoints(){
		return points;
	}
	
	public void setPoints(Long limit){
		
		disableButtons();
		
		if ( limit > 0 ){
		
			if (limit.equals(1L)) {
					start1.setIcon(yellowStart);
					start2.setIcon(grayStart);
					start3.setIcon(grayStart);
					start4.setIcon(grayStart);
					start5.setIcon(grayStart);
					points = 1L;
			}
			else if (limit.equals(2L)){
					start1.setIcon(yellowStart);
					start2.setIcon(yellowStart);
					start3.setIcon(grayStart);
					start4.setIcon(grayStart);
					start5.setIcon(grayStart);
					points = 2L;
			}
			else if (limit.equals(3L)){
				 	start1.setIcon(yellowStart);
					start2.setIcon(yellowStart);
					start3.setIcon(yellowStart);
					start4.setIcon(grayStart);
					start5.setIcon(grayStart);
					points = 3L;
			}
			else if (limit.equals(4L)){
					start1.setIcon(yellowStart);
					start2.setIcon(yellowStart);
					start3.setIcon(yellowStart);
					start4.setIcon(yellowStart);
					start5.setIcon(grayStart);
					points = 4L;
			}
			else if(limit.equals(5L)){
					start1.setIcon(yellowStart);
					start2.setIcon(yellowStart);
					start3.setIcon(yellowStart);
					start4.setIcon(yellowStart);
					start5.setIcon(yellowStart);
					points = 5L;
			}
		}
	}
	
	private void disableButtons(){
		start1.setIcon(grayStart);
		start2.setIcon(grayStart);
		start3.setIcon(grayStart);
		start4.setIcon(grayStart);
		start5.setIcon(grayStart);
		points = 0L;
	}


	public JLabel getStart1() {
		return start1;
	}


	public void setStart1(JLabel start1) {
		this.start1 = start1;
	}


	public JLabel getStart2() {
		return start2;
	}


	public void setStart2(JLabel start2) {
		this.start2 = start2;
	}


	public JLabel getStart3() {
		return start3;
	}


	public void setStart3(JLabel start3) {
		this.start3 = start3;
	}


	public JLabel getStart4() {
		return start4;
	}


	public void setStart4(JLabel start4) {
		this.start4 = start4;
	}


	public JLabel getStart5() {
		return start5;
	}


	public void setStart5(JLabel start5) {
		this.start5 = start5;
	}


	public Icon getGrayStart() {
		return grayStart;
	}


	public void setGrayStart(Icon grayStart) {
		this.grayStart = grayStart;
	}


	public Icon getYellowStart() {
		return yellowStart;
	}


	public void setYellowStart(Icon yellowStart) {
		this.yellowStart = yellowStart;
	}

	


	
	
	
}