import javax.swing.*;


public class Principal {
	
	public static void crearGUI(JFrame ventana){
		Panel panel = new Panel(ventana);
		Controlador ctr = new Controlador(panel);
		panel.controlador(ctr);
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final JFrame ventana = new JFrame("Aplicaci�n");
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				crearGUI(ventana);
			}
			});
		}
}
