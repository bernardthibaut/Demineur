import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Drapeau extends JFrame {

	private Jeu jeu;

	public Drapeau(Jeu j) {
		this.jeu = j;

		setPreferredSize(new Dimension(200, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton drapeau = new JButton();
		drapeau.setBackground(Color.lightGray);
		drapeau.setFocusable(false);

		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ESCAPE) {
					System.exit(1);
				}

				if (key == KeyEvent.VK_SPACE) {
					if (!jeu.modeDrapeau)
						jeu.modeDrapeau = true;
					else
						jeu.modeDrapeau = false;
					jeu.updateFond();
				}

			}

		});

		drapeau.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!jeu.modeDrapeau) {
					jeu.modeDrapeau = true;
					drapeau.setBackground(Color.red);
				} else {
					jeu.modeDrapeau = false;
					drapeau.setBackground(Color.lightGray);
				}
				jeu.updateFond();
			}
		});

		getContentPane().add(drapeau);

		pack();
		setLocation(200, 400);
		setVisible(true);
	}

}
