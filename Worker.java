import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;


public class Worker extends SwingWorker<List<Integer>, Integer>{
	
	private int n;				//Elementos
	private int lista;			//Si es la primera o la segunda
	private Panel panel;
	
	public Worker(int n, int lista, Panel panel) {
		this.n = n;
		this.lista = lista;
		this.panel = panel;
	}
	@Override
	protected List<Integer> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		return crearLista();
	}
	
	private List<Integer> crearLista() {
		List<Integer> list = new ArrayList<Integer>();
		Random rnd = new Random();
		for(int i = 0; i < n; i++) {
			if(i==0)
				list.add(rnd.nextInt(30));
			else
				list.add(list.get(i-1) +rnd.nextInt(25));
		}
		return list;
	}
	
	public String toString(List<Integer> list) {
		String std = "";
		int j = 0;
		for(int i = 0; i < list.size(); i++) {
			std = std + "("+i+":"+list.get(i)+"), ";
			j++;
			if(j==5) {
				j=0;
				std = std + "\n";
			}
		}
		return std;
	}
	
	public void done() {
		try {
			
			panel.cambiaTexto(lista, toString(get()));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
