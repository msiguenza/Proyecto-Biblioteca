package ClasesInterfaz;



import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
 

public class Inicio extends JFrame {

	JProgressBar current;
 
	JTextArea out;
 
	JButton find;
 
	Thread runner;
 
	int num = 0;

	public Inicio() {
 
		super("Progress");
		setLocation(new Point(55, 700));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Book.png"));
 
		setUndecorated(true);
  
 
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
		JPanel pane = new JPanel();
  
		setContentPane(pane);
		pane.setLayout(null);
		
  
 
		JLabel lblCargando = new JLabel("Cargando ...");
		lblCargando.setBounds(55, 222, 74, 14);
		lblCargando.setForeground(Color.WHITE);
 
		lblCargando.setFont(new Font("Antipasto", Font.BOLD | Font.ITALIC, 12));
  
		lblCargando.setHorizontalAlignment(SwingConstants.CENTER);
  
		lblCargando.setHorizontalTextPosition(SwingConstants.CENTER);
 
		pane.add(lblCargando);
  
 
		JLabel lblBienvenidoABooknotes = new JLabel("Bienvenido a BookNotes");
		lblBienvenidoABooknotes.setBounds(15, 59, 147, 14);
		lblBienvenidoABooknotes.setForeground(Color.WHITE);
 
		lblBienvenidoABooknotes.setFont(new Font("Antipasto", Font.BOLD | Font.ITALIC, 12));
 
		pane.add(lblBienvenidoABooknotes);
  
 
		JLabel lblEnBrevesInstantes = new JLabel("En breves instantes iniciar\u00E1 el servicio. ");
		lblEnBrevesInstantes.setBounds(15, 84, 243, 14);
		lblEnBrevesInstantes.setForeground(Color.WHITE);
 
		lblEnBrevesInstantes.setFont(new Font("Antipasto", Font.BOLD | Font.ITALIC, 12));
 
		pane.add(lblEnBrevesInstantes);
		
		current = new JProgressBar(0, 2000);
		current.setBounds(15, 247, 146, 17);
		
		current.setValue(0);
		
		current.setStringPainted(true);
		
		pane.add(current);
  
  
		JLabel lblGraciasPorUtilizar = new JLabel("Gracias por utilizar nuestro servicio.");
		lblGraciasPorUtilizar.setBounds(15, 126, 226, 14);
		lblGraciasPorUtilizar.setForeground(Color.WHITE);
  
		lblGraciasPorUtilizar.setFont(new Font("Antipasto", Font.BOLD | Font.ITALIC, 12));
  
		pane.add(lblGraciasPorUtilizar);
		
		JLabel label_1 = new JLabel("");
		label_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		label_1.setBounds(0, 0, 546, 418);
		label_1.setIcon(new ImageIcon("src/images/fondoabstractomateriales.jpg"));
		pane.add(label_1);
		setResizable(false);
 
	}


	public void iterate() {
	
		
	
 
		while (num < 2000) {
 
			current.setValue(num);
  
			try {
 
				Thread.sleep(1000);
 
			} catch (InterruptedException e) { }
  
			num += 95;
 
		}
  
  
		if (num>2000){
	 
			dispose();
		
			Validacion v = new Validacion();
		
			v.setVisible(true);
  
		}

	}


	public static void main(String[] arguments) {
  
		Inicio frame = new Inicio();
  
		frame.pack();
  
		frame.setBounds(800, 100,500,400);
  
		frame.setVisible(true);
  
		frame.setLocationRelativeTo(null);
		
		frame.iterate();
		
 
	}
  
		}
	  
