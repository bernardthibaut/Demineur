import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	int taille = 10;
	int txMines = 15;

	Jeu jeu = new Jeu(this, taille, txMines);

	boolean enJeu = false;

	public Fenetre() {

		setPreferredSize(new Dimension(jeu.largeur, jeu.hauteur));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

				if (jeu.jeuFini != 0) {
					rejouer();
				}

			}

		});

		getContentPane().add(new Menu(this));

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Fenetre();
			}
		});
	}

	public void rejouer() {
		getContentPane().removeAll();
		jeu = new Jeu(this, taille, txMines);
		getContentPane().add(jeu);
		getContentPane().revalidate();
		getContentPane().repaint();

		if (!enJeu) {
			setMinimumSize(new Dimension(jeu.largeur, jeu.hauteur));
			setResizable(true);
			setLocationRelativeTo(null);
		}

		enJeu = true;
	}

}
