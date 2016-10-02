package ClasesUsuario;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ComentarioNota extends JDialog {

	public ComentarioNota(String c) {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/user.png"));
		setLocationRelativeTo(null);
		setBounds(100, 100, 435, 255);
		getContentPane().setLayout(null);
		setModal(true);
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setActionCommand("OK");
		btnCerrar.setIcon(new ImageIcon("src/images/return.png"));
		btnCerrar.setBounds(159, 212, 99, 34);
		getContentPane().add(btnCerrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 190);
		getContentPane().add(scrollPane);
		
		JTextPane textPaneComentario = new JTextPane();
		scrollPane.setViewportView(textPaneComentario);
		textPaneComentario.setEditable(false);
		textPaneComentario.setText(c);
		textPaneComentario.setEditable(false);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/images/comentarioNota.jpg"));
		label.setBounds(0, 0, 434, 255);
		getContentPane().add(label);
	}
	
}
