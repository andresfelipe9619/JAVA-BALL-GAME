import java.awt.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
public class Balloon extends Thread implements Serializable{

	private Color color;
	private String direccion;
	private int posX, posY, ancho, alto, rebotes, movimiento;
	private boolean atrapado;
	private Ellipse2D oval;

	public Balloon(Color color, /*int posx, int posy,*/ int ancho, int alto) {
		this.color = color;
		this.ancho = ancho;
		this.alto = alto;
		posX=(int) (Math.random() * 400);
		posY=(int) (Math.random() * 400);
		rebotes = 0;
		atrapado = false;
		
	}

	public void run() {

		long movimiento = (long) (Math.random() * (1000));
		int dir=(int) (Math.random() * 3);
		String[] direcciones={"abajo","arriba","derecha","izquierda"};
		direccion=direcciones[dir];
		while (!atrapado) {
			try {
				if (direccion.equals("abajo")) {
					posY += 30;
					if (posY >= 470) {
						direccion = "arriba";
						rebotes++;
						
					}
				}
				if (direccion.equals("arriba")) {
					posY -= 30;
					if (posY <=10) {
						direccion = "abajo";
						rebotes ++;
					}
				}
				
				if (direccion.equals("derecha")) {
					posX += 30;
					if (posX >=530) {
						direccion = "izquierda";
						rebotes ++;
					}
				}
				
				if (direccion.equals("izquierda")) {
					posX -= 30;
					if (posX <=30) {
						direccion = "derecha";
						rebotes ++;
					}
				}
			
				// System.out.println("( "+posX + "," + posY+" )");
				// System.out.println("Rebotes="+rebotes);
				this.sleep(movimiento);

			} catch (InterruptedException e) {

			}
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getRebotes() {
		return rebotes;
	}

	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}

	public boolean isAtrapado() {
		return atrapado;
	}

	public void setAtrapado(boolean atrapado) {
		this.atrapado = atrapado;
	}

	public Ellipse2D getOval() {
		return oval;
	}

	public void setOval(Ellipse2D oval) {
		this.oval = oval;
	}

}
