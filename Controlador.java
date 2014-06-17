import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import mensajes.*;

public class Controlador implements ActionListener{
	
	private Panel panel;
	private Channel<Elemento> c1;
	private Channel<Elemento> c2;

	public Controlador(Panel pn) {
		panel = pn;

		 c1 = new Channel<Elemento>();
		 c2 = new Channel<Elemento>();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		// TODO Auto-generated method stub
		String mensaje = e.getActionCommand();
		 
		if(mensaje.equals("Intro")) {
			panel.habilitarInicio();
			panel.cambiaEtiqueta("Mezclando");
//			inicializacionIntro();
			int tam = panel.getTamano();
			if(tam>0) {
				
				// 0 Para la primera lista y 1 para la segunda
				Worker wok = new Worker(tam, 0, panel, c1);
				Worker wok2 = new Worker(tam, 1, panel, c2);
				
				WorkerMezcla wm = new WorkerMezcla(panel, c1, c2, tam);
//				System.out.println("Ejecutando");
				wok.execute();
				wok2.execute();
				
				wm.execute();
//				System.out.println("Tras Ejecutar");
			}

		}
		
	}
	
	/*private void inicializacionIntro() {
		panel.cambiaEtiqueta("Creando Listas");
		panel.setBarra(0);
		panel.cambiaTexto(0, "");
		panel.cambiaTexto(1, "");
		panel.cambiaTexto(2, "");
	}*/

}
