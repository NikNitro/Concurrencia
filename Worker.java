import java.util.*;

import javax.swing.SwingWorker;

import mensajes.*;

public class Worker extends SwingWorker<List<Elemento>, Elemento>{
	
	private int n;				//Elementos
	private int lista;			//Si es la primera o la segunda
	private Panel panel;
	private Channel<Elemento> ch;
	
	public Worker(int n, int lista, Panel panel, Channel<Elemento> c) {
		this.n = n;
		this.lista = lista;
		this.panel = panel;
		ch = c;
	}
	public int getElementos() {
		return n;
	}
	@Override
	protected synchronized List<Elemento> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		List<Elemento> list = new ArrayList<Elemento>();
		Random rnd = new Random();
		for(int i = 0; i < n; i++) {
			int aux = rnd.nextInt(30);
			if(i!=0)
				aux = list.get(i-1).getNum() + aux;
			Elemento el = new Elemento(i, aux);
			ch.send(el);
			list.add(el);
			publish(el);
//			panel.cambiaTexto(lista, panel.getListaN(lista) + toString(i,aux));
			wait(150);
		}
		return list;
	}

	/*
	public String toString(int index, int value) {
		String std = "";
		  std ="("+index+":"+value+"), ";
			if(index%5 == 4)
				std =  std + "\n";
		return std;
	}*/
	
	
	
	public void process(List<Elemento> chunks){
        for (Elemento pos: chunks) {
			if ((pos.getPos()%5)== 0)
        		panel.cambiaTexto(lista, "\n");
			panel.cambiaTexto(lista, pos.toString());
		}
	}
/*	public void done() {
		try {
			
			panel.cambiaTexto(lista, toString(get()));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
