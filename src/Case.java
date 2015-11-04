import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Case extends JButton {

	int x, y;

	Jeu jeu;

	int minesProximite = 0;

	boolean estMine = false;

	int tailleCase;

	boolean estVisible = false;

	boolean estMarque = false;

	public Case(int x, int y, Jeu j) {
		this.x = x;
		this.y = y;
		this.jeu = j;

		setBackground(Color.lightGray);
		setFocusable(false);

		addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (jeu.jeuFini == 0) {
					if (!jeu.modeDrapeau) {
						updateCase();
						estVisible = true;
						notifier(jeu);
					} else if (!estVisible) {
						if (!estMarque) {
							estMarque = true;
							setBackground(Color.yellow);
						} else {
							estMarque = false;
							setBackground(new Color(255, 230, 230));
						}
					}

					int casesRestantes = 0;
					for (int i = 0; i < jeu.taille; i++) {
						for (int j = 0; j < jeu.taille; j++) {
							if (!jeu.cases[i][j].estMine && !jeu.cases[i][j].estVisible)
								casesRestantes++;
						}
					}
					if (casesRestantes == 0) {
						jeu.jeuFini = 1;
						jeu.updateAll();
					}

				} else {
					jeu.f.rejouer();
				}
			}
		});
	}

	public void poserMine() {
		estMine = true;
	}

	public void updateCase() {
		if (estMine) {
			if (jeu.jeuFini == -1)
				setBackground(Color.red);
			else if (jeu.jeuFini == 1)
				setBackground(Color.green);
		} else {
			setBackground(Color.white);
			setForeground(new Color(minesProximite * 30, 255 - minesProximite * 30, 0));
			setFont(new Font("arial", 1, 55));
			if (minesProximite != 0)
				setText("" + minesProximite);
		}

	}

	public void notifier(Jeu jeu) {
		if (minesProximite == 0)
			jeu.afficherVides(this);
		if (estMine) {
			jeu.jeuFini = -1;
			jeu.updateAll();
		}
	}

}
