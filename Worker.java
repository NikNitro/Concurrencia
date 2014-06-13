import java.util.*;
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
		
		List<Integer> list = new ArrayList<Integer>();
		Random rnd = new Random();
		for(int i = 0; i < n; i++) {
			int aux = rnd.nextInt(30);
			if(i!=0)
				aux = list.get(i-1) + aux;
			
			list.add(aux);
			panel.cambiaTexto(lista, panel.getListaN(lista) + toString(i,aux));
		}
		return list;
	}

	
	public String toString(int index, int value) {
		String std = "";
		  std ="("+index+":"+value+"), ";
			if(index%5 == 4)
				std =  std + "\n";
		return std;
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
