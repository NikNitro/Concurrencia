import java.util.*;
import javax.swing.SwingWorker;

import mensajes.*;


public class WorkerMezcla extends SwingWorker<List<Elemento>, Elemento>{

	private int tam;	
	private List<Elemento> list;
	private Channel<Elemento> ch1;
	private Channel<Elemento> ch2;
	private	Panel panel;
	private	int j = 0;

	public	WorkerMezcla(Panel p, Channel<Elemento> c1, Channel<Elemento> c2, int n){	
			panel = p;
			ch1 = c1;
			ch2 = c2;
			tam = n;
	}
	
	
	@Override
	protected List<Elemento> doInBackground(){
//		System.out.println("En background");
	//	return mezclarLista(lista1, lista2);
		List<Elemento> aux = mezclarLista();
		return aux;
	}

	// ESTO ERA PARA EL PRIMER APARTADO
/*	private List<Integer> mezclarLista(List<Integer> lista1, List<Integer> lista2) {
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
			
			// El porcentaje es por 100 y partido el doble del tamaño porque los dos son iguales.
			// Esto es lo mismo simplificado.
			int porcentaje = (i*50)/(lista1.size());
			panel.setBarra(porcentaje);
			
			// El wait es por si quiero ver más detenidamente como aumenta la barra. 
			// Para poder usarlo es necesario añadir synchronized entre el private y el List<Integer>
/*			try {
				wait(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaMezc;
	}
*/	private List<Elemento> mezclarLista() {
//	System.out.println("GG");
		try{
			list =new ArrayList<Elemento>();
			int n1 = 0;
			int n2 = 0;
			int i = 0;
//			System.out.println("Recibiran?");
			Elemento l1 = ch1.receive(), l2 = ch2.receive();
//			System.out.println("Antes del while");
			while(n1 < tam || n2 < tam){
				Elemento pos = null;
				if(l1.getNum() >= l2.getNum()){
					n2++;
					pos = new Elemento(i, l2.getNum());
					if(n2 < tam){
						l2 = ch2.receive();
					}else{
						l2 = new Elemento(i, Integer.MAX_VALUE);
					}
				}else{
					n1++;
					pos = new Elemento(i, l1.getNum());
					if(n1 < tam){
						l1 = ch1.receive();
					}else{
						l1 = new Elemento(i, Integer.MAX_VALUE);
					}
				}
//				System.out.println("After del while");

				list.add(pos);
				publish(pos);
				i++;
			
				panel.setBarra(i*50/tam);	
			}
		} catch (InterruptedException e) { e.printStackTrace(); }
		return list;
	}
		// ESTO ERA PARA EL APARTADO A
	/*
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
	}*/
	protected void process(List<Elemento> chunks) {

//		System.out.println("Heyy");

        for (Elemento pos: chunks) {

        	System.out.println(j + " " + pos.getPos());
        	if(pos.getPos()%10==0) {
				panel.cambiaTexto(2, "\n");
			}
            panel.cambiaTexto(2, pos.toString()+", ");
           
        }
    }

	public void done() {
//			panel.cambiaTexto(2, toString(get()));
			
			panel.cambiaEtiqueta("Mezcla completada");
			//Si la división no es exacta, la barra no quedaría totalmente llena.
			panel.setBarra(100);
	}

}
