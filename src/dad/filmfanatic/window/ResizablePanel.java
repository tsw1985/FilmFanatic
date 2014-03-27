package dad.filmfanatic.window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.BevelBorder;


@SuppressWarnings("serial")
public class ResizablePanel extends JPanel {

    private boolean drag = false;
    private Point dragLocation  = new Point();

    public  ResizablePanel() {
    	
    	setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    	setLocation(20, 20);
    	setPreferredSize(new Dimension(200, 200));
    	setSize(new Dimension(200, 200));
    	
    	
//    	final JFrame f = new JFrame("Test");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	drag = true;
            	dragLocation = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	drag = false;
            }
    	});
        addMouseMotionListener(new MouseMotionAdapter() {
        	
            @Override
            public void mouseDragged(MouseEvent e) {
            	
            	if (drag) {
            		if (dragLocation.getX() > getWidth()-20 && dragLocation.getY()>getHeight()-20) {
            			System.err.println("ancho Ventana-> " + getWidth() );
            			setSize((int)(getWidth()+(e.getPoint().getX()-dragLocation.getX())),
            					(int)(getHeight()+(e.getPoint().getY()-dragLocation.getY())));
            			dragLocation = e.getPoint();
            			
            		}
            	}
            }
    	});
        
    }
    

    public static void main(String[] args) {
    	
    	JFrame jframe = new JFrame();
    	jframe.setSize(440, 480);
    	jframe.setLocationRelativeTo(null);
    	jframe.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE  );
    	jframe.setLayout(new BorderLayout() );
    	
    	JPanel panelRojo = new JPanel();
    	panelRojo.setSize(100, 200);
    	panelRojo.setLayout(null);
    	panelRojo.add(  new ResizablePanel() );
    	
    	jframe.getContentPane().add( panelRojo);
    	jframe.setVisible(true);
    	
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}
