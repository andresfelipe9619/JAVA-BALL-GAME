import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class InterfazJuegoBalones extends JFrame implements ActionListener{

	private cancha canchaBalones;
	private PanelCancha panelCancha;
	private JLabel score;
	private JButton butEmpezar;
	private MenuArchivo theMenu;
	public final static String COMENZAR = "comenzar";
	Thread hiloPanel;
	
	public InterfazJuegoBalones() {

		setTitle("Atrapala!!!");

		setLayout(new BorderLayout(5, 5));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelCancha = new PanelCancha(this);
		hiloPanel = new Thread(panelCancha);
		canchaBalones = new cancha();
		score= new JLabel("Rebotes: "+ 0);
		butEmpezar = new JButton("Empezar");
		butEmpezar.setActionCommand(COMENZAR);
		butEmpezar.addActionListener(this);
		theMenu=new MenuArchivo(this);
		setJMenuBar(theMenu); 
		theMenu.add(theMenu.getMenuBar());
		add(butEmpezar, BorderLayout.SOUTH);
		add(score,BorderLayout.NORTH);
		add(panelCancha, BorderLayout.CENTER);
		MouseHandler handler = new MouseHandler();
		panelCancha.addMouseListener(handler);
		
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new InterfazJuegoBalones().setVisible(true);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		if (evento.equals(COMENZAR)) {
			canchaBalones.start();
			hiloPanel.start();
		}
	}

	public class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			//System.out.println("(" + e.getX() + "," + e.getY() + ")");
			for (int i = 0; i < cancha.MAX_BALONES; i++) {

			 if ((e.getButton() == 1)&&canchaBalones.getBalones()[i].getOval().contains(e.getX(), e.getY()) )
			
				{
					try {

						System.out.println("(" + e.getX() + "," + e.getY() + ")");
						canchaBalones.getBalones()[i].setAtrapado(true);

					} catch (Exception ex) {
						System.out.println(" Error");
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// JOptionPane.showMessageDialog(null,
			// String.format("Pressed",e.getX(),e.getY()),
			// "Mensaje",JOptionPane.INFORMATION_MESSAGE);

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// JOptionPane.showMessageDialog(null, String.format("Released
			// at",e.getX(),e.getY()),
			// "Mensaje",JOptionPane.INFORMATION_MESSAGE);

		}

	}

	public cancha getCanchaBalones() {
		return canchaBalones;
	}

	public void setCanchaBalones(cancha canchaBalones) {
		this.canchaBalones = canchaBalones;
	}

	public PanelCancha getPanelCancha() {
		return panelCancha;
	}

	public void setPanelCancha(PanelCancha panelCancha) {
		this.panelCancha = panelCancha;
	}

	public JLabel getScore() {
		return score;
	}

	public void setScore(JLabel score) {
		this.score = score;
	}

	
}
