import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Panel extends  JPanel{
	private static final long serialVersionUID = -7791811179827965902L;
	
	private JPanel panel;
	private final JLabel longitud = new JLabel("Longitud de las listas: ");
	private final JLabel lista1 = new JLabel("Lista 1");
	private final JLabel lista2 = new JLabel("Lista 2");
	private final JLabel listaMezc = new JLabel("Listas Mezcladas");
	private JLabel estado;		//Preparado, mezclando, mezcla terminada
	private JTextField tamano;
	private JTextArea list1;
	private JScrollPane sp1, sp2, listMezc;
	
	private JTextArea list2;
	private JTextArea listMezcAux;
	private JProgressBar barraProg;
	
	
	
	public Panel(JFrame ventana) {
		
		barraProg = new JProgressBar(0, 100);
		estado = new JLabel();
		tamano = new JTextField();
		list1 = new JTextArea();
		list2 = new JTextArea();
		sp1 = new JScrollPane(list1);
		sp2 = new JScrollPane(list2);
		listMezcAux = new JTextArea();
		listMezc = new JScrollPane(listMezcAux);
		habilitarInicio();
		
		BorderLayout bl = new BorderLayout(10, 10);
		panel = new JPanel(bl);
		panel.setPreferredSize(new Dimension(800, 600));
		
		
		JPanel listas = new JPanel(new BorderLayout());
		JPanel aux1 = new JPanel(new BorderLayout()); 	// Dos textarea listas
		JPanel aux2 = new JPanel(new BorderLayout());	// Dos label listas
		JPanel aux = new JPanel(new GridLayout(1, 2, 5, 0));	// Dos jpanels anteriores
		aux1.add(sp1, BorderLayout.CENTER);
		aux2.add(sp2, BorderLayout.CENTER);
		aux1.add(lista1, BorderLayout.SOUTH);
		aux2.add(lista2, BorderLayout.SOUTH);
		aux.add(aux1);
		aux.add(aux2);
		listas.add(aux, BorderLayout.CENTER);
		listas.add(new JLabel(), BorderLayout.EAST);
		listas.add(new JLabel(), BorderLayout.WEST);
		
		
		JPanel longit = new JPanel(new GridLayout(1, 6, 0, 0));// Fila de longitud.
		longit.add(new JLabel());	// Para que salga más centrado.
		longit.add(new JLabel());	// Para que salga más centrado.
		longit.add(longitud);
		longit.add(tamano);
		longit.add(new JLabel());	// Para que salga más centrado.
		longit.add(new JLabel());	// Para que salga más centrado.
		
		JPanel barra = new JPanel(new GridLayout(1, 6));
		barra.add(new JLabel());
		barra.add(new JLabel());
		barra.add(estado);
		barra.add(barraProg);
		barra.add(new JLabel());
		barra.add(new JLabel());
		
		JPanel listaMezclada = new JPanel(new BorderLayout(1, 2));
		listaMezclada.add(listMezc, BorderLayout.CENTER);
		listaMezclada.add(listaMezc, BorderLayout.SOUTH);
		
		JPanel centro = new JPanel(new GridLayout(2, 1));
		centro.add(listas);
		centro.add(listaMezclada);
		
		panel.add(longit, BorderLayout.NORTH);
		panel.add(centro, BorderLayout.CENTER);
		panel.add(barra, BorderLayout.SOUTH);
		
		ventana.setContentPane(panel);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		ventana.pack();
	}
	
	public void habilitarInicio() {
		estado.setText("Preparado");
//		tamano.setText("0");
		list1.setText("");
		list2.setText("");
		listMezcAux.setText("");
		barraProg.setValue(0);
		barraProg.setString(barraProg.getValue()+"%");
		barraProg.setEnabled(true);
		barraProg.setStringPainted(true);
	}

	public int getTamano() {
		return Integer.parseInt(tamano.getText());
	}

	public void cambiaEtiqueta(String std) {
		estado.setText(std);
	}
	
	// 0 Para la primera lista, 1 para la segunda y 2 para la de mezcla
	public void cambiaTexto(int i, String std) {
		if(i==0)
			list1.append(std);
		else if (i==1)
			list2.append(std); 
		else if (i==2)
			listMezcAux.append(std); 
		else 
			throw new NullPointerException("Lista inexistente.\nPor favor elija la 0, la 1 o la 2") ;

	}
	public void controlador(ActionListener ctr) {
		tamano.addActionListener(ctr);
		
		tamano.setActionCommand("Intro");
		
		
	}

	public void setBarra(int i) {

		barraProg.setValue(i);
		barraProg.setString(barraProg.getValue()+"%");
	}
	
	// si n es 0 devuelve la 1ª, si es 1 devuelve la 2º
	public String getListaN(int n) {
		if(n==0) 
			return list1.getText();
		else if(n==1) 
			return list2.getText();
		else 
			return null;
	}
}
//JProccesBar (value y string que esta dentro de la barra) tambien tiene un true para que se escriba dentro de la barra el porcentaje