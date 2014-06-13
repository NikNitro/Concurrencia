import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			int tam = panel.getTamano();
			panel.cambiaEtiqueta("Creando Listas");
			panel.setBarra(0);
			// 0 Para la primera lista y 1 para la segunda
			Worker wok = new Worker(tam, 0, panel);
			Worker wok2 = new Worker(tam, 1, panel);
			wok.execute();
			wok2.execute();
			while(!(wok.isDone() && wok2.isDone()));
 
			panel.cambiaEtiqueta("Mezclando Listas");
			panel.setBarra(50);
			
			WorkerMezcla wm;
			try {
				wm = new WorkerMezcla(wok.get(), wok2.get(), panel);
				wm.execute();
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
