
package ClasesAdministrador;

import java.awt.BorderLayout;  
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class VerComentarioNotas extends JDialog {

	public VerComentarioNotas(String c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 278);
		getContentPane().setLayout(null);
		setModal(true);
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setMnemonic('c');
		btnCerrar.setIcon(new ImageIcon("src/images/adiooos.png"));
		
		btnCerrar.setActionCommand("OK");
		btnCerrar.setBounds(159, 212, 99, 34);
		getContentPane().add(btnCerrar);
		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 430, 190);
		getContentPane().add(scrollPane);
		
		JTextPane textPaneComentario = new JTextPane();
		textPaneComentario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setViewportView(textPaneComentario);
		textPaneComentario.setEditable(false);
		textPaneComentario.setText(c);
		textPaneComentario.setEditable(false);
		
		JLabel label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		label.setIcon(new ImageIcon("src/images/fondogrande.jpg"));
		label.setBounds(0, 0, 450, 278);
		getContentPane().add(label);
	}
}