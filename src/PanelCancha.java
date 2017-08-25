
import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;


public class PanelCancha extends JPanel implements Runnable {
    private InterfazJuegoBalones principal;
    private int total;
    public PanelCancha( InterfazJuegoBalones v){
        principal=v;
        
    }
    
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
                for (int i = 0; i < cancha.MAX_BALONES; i++) {
			Balloon b = principal.getCanchaBalones().getBalones()[i];
			g.setColor(b.getColor());
			b.setOval(new Ellipse2D.Double(b.getPosX(), b.getPosY(), b.getAncho(),b.getAlto()));
		
			Graphics2D g2d=(Graphics2D)g;
			g2d.fill(b.getOval());
			
			total=principal.getCanchaBalones().getBalones()[0].getRebotes()+principal.getCanchaBalones().getBalones()[1].getRebotes()
					+principal.getCanchaBalones().getBalones()[2].getRebotes()+principal.getCanchaBalones().getBalones()[3].getRebotes()
					+principal.getCanchaBalones().getBalones()[4].getRebotes()+principal.getCanchaBalones().getBalones()[5].getRebotes();
			principal.getScore().setText("Rebotes: "+ total);
                }	
                
}
    public void run() {
		while(!principal.getCanchaBalones().llego()){
		this.repaint();
		}
		System.out.println("Ganaste!!!!!");
	}
}
