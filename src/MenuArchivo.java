
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class MenuArchivo extends JMenuBar {

	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem Guardar, Cargar;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private InterfazJuegoBalones interfaz;

	public MenuArchivo(InterfazJuegoBalones in) {
		this.interfaz = in;
		menu = new JMenu("Archivo");
		menuBar = new JMenuBar();
		Guardar = new JMenuItem("Guardar Partida");
		Cargar = new JMenuItem("Cargar Partida");

		menuBar.add(menu);
		menu.add(Cargar);
		menu.add(Guardar);

		Cargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirArchivo();
				if (entrada != null) {
					leerRegistro();

				}
			}

		});

		Guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				guardarArchivo();
				if (salida != null) {
					registro();

				}
			}

		});

	}

	public void abrirArchivo() {

		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resultado = selectorArchivo.showOpenDialog(this);

		if (resultado == JFileChooser.CANCEL_OPTION)
			return;
		File nombreArchivo = selectorArchivo.getSelectedFile();

		if (nombreArchivo == null || nombreArchivo.getName().equals(""))
			JOptionPane.showMessageDialog(this, "Nombre de archivo incorrecto", "Nombre de archivo incorrecto",
					JOptionPane.ERROR_MESSAGE);
		else {
			try {
				entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
			}

			catch (IOException excepcionES) {
				JOptionPane.showMessageDialog(this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void leerRegistro(){
		cancha saveField;
	
		
		try {
			saveField = ( cancha ) entrada.readObject();
			interfaz.setCanchaBalones(saveField);
			entrada.close();
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void guardarArchivo() {

		JFileChooser selectorArchivo = new JFileChooser();
		selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resultado = selectorArchivo.showSaveDialog(this);

		if (resultado == JFileChooser.CANCEL_OPTION)
			return;

		File nombreArchivo = selectorArchivo.getSelectedFile();

		if (nombreArchivo == null || nombreArchivo.getName().equals(""))
			JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Nombre de archivo invalido",
					JOptionPane.ERROR_MESSAGE);

		else {

			try {
				salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
			} catch (IOException excepcionES) {
				JOptionPane.showMessageDialog(this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void registro() {
		cancha saveField;
		saveField = interfaz.getCanchaBalones();

		try {
			salida.writeObject(saveField);
			salida.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public JMenu getMenu() {
		return menu;
	}

	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenuItem getGuardar() {
		return Guardar;
	}

	public void setGuardar(JMenuItem Guardar) {
		this.Guardar = Guardar;
	}

	public JMenuItem getCargar() {
		return Cargar;
	}

	public void setCargar(JMenuItem Cargar) {
		this.Cargar = Cargar;
	}

}