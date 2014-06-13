import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;


public class WorkerMezcla extends SwingWorker<List<Integer>, Integer>{

	private List<Integer> lista1;
	private List<Integer> lista2;
	private String l1;
	private String l2;
	private Panel panel;
	
	public WorkerMezcla(List<Integer> l1, List<Integer> l2, Panel panel) {
		this.panel = panel;
		lista1 = l1;
		lista2 = l2;
	}
	
	
	@Override
	protected List<Integer> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return mezclarLista(lista1, lista2);
	}

	
	private List<Integer> mezclarLista(List<Integer> lista1, List<Integer> lista2) {
		List<Integer> listaMezc = new ArrayList<Integer>();
		int index1 = 0;
		int index2 = 0;
		for(int i = 0; i < lista1.size()+lista2.size(); i++) {
			if(index1<lista1.size() && index2<lista2.size()){
				
				if(lista1.get(index1)>lista2.get(index2)){
					listaMezc.add(lista2.get(index2));
					index2++;
				} else {
					listaMezc.add(lista1.get(index1));
					index1++;
				}
			} else if (index1==lista1.size()) {
				listaMezc.add(lista2.get(index2));
				index2++;
			} else {
				listaMezc.add(lista1.get(index1));
				index1++;
				
			}
			
		}
		return listaMezc;
	}
	
	public String toString(List<Integer> list) {
		String std = "";
		int j = 0;
		for(int i = 0; i < list.size(); i++) {
			std = std + "("+i+":"+list.get(i)+"), ";
			j++;
			if(j==10) {
				j = 0;
				std = std + "\n";
			}
		}
		return std;
	}
	
	public void done() {
		try {
			panel.cambiaTexto(2, toString(get()));

			panel.cambiaEtiqueta("Mezcla completada");
			panel.setBarra(100);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
