import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Controlador implements ActionListener{
	
	private Panel panel;

	public Controlador(Panel pn) {
		panel = pn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		// TODO Auto-generated method stub
		String mensaje = e.getActionCommand();
		 
		if(mensaje.equals("Intro")) {
			inicializacionIntro();
			int tam = panel.getTamano();
			// 0 Para la primera lista y 1 para la segunda
			Worker wok = new Worker(tam, 0, panel);
			Worker wok2 = new Worker(tam, 1, panel);
			wok.execute();
			wok2.execute();

			try {
				List<Integer> l1 = wok.get();
				List<Integer> l2 = wok2.get();
				panel.cambiaEtiqueta("Mezclando Listas");
				
				WorkerMezcla wm = new WorkerMezcla(l1, l2, panel);
				wm.execute();
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
	private void inicializacionIntro() {
		panel.cambiaEtiqueta("Creando Listas");
		panel.setBarra(0);
		panel.cambiaTexto(0, "");
		panel.cambiaTexto(1, "");
		panel.cambiaTexto(2, "");
	}

}
