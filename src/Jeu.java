import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Jeu extends JPanel {

	Random r = new Random();

	int largeur = 900;
	int hauteur = 900;

	Fenetre f;

	int taille = 10;

	Case[][] cases = new Case[taille][taille];

	int txMines = 15;
	int nbMines = (txMines * taille * taille) / 100;

	boolean modeDrapeau = false;

	int jeuFini = 0;

	public Jeu(Fenetre f, int taille, int txMines) {
		new Drapeau(this);
		this.f = f;
		this.taille = taille;
		this.txMines = txMines;
		nbMines = (txMines * taille * taille) / 100;

		f.requestFocus();

		setBackground(Color.black);
		setLayout(new GridLayout(taille, taille));

		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				Case caseTmp = new Case(i, j, this);
				cases[i][j] = caseTmp;
				this.add(caseTmp);
			}
		}

		while (nbMines > 0) {
			int xAlea = r.nextInt(taille);
			int yAlea = r.nextInt(taille);

			if (!cases[xAlea][yAlea].estMine) {
				cases[xAlea][yAlea].poserMine();
				updateProximite(cases[xAlea][yAlea]);
				nbMines--;
			}
		}

	}

	public void updateAll() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				cases[i][j].updateCase();
			}
		}
	}

	public void updateProximite(Case c) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (coordValides(c.x + i, c.y + j))
					cases[c.x + i][c.y + j].minesProximite++;
			}
		}
	}

	public void afficherVides(Case c) {

		if (coordValides(c.x - 1, c.y) && !cases[c.x - 1][c.y].estVisible)
			interact(cases[c.x - 1][c.y]);
		if (coordValides(c.x, c.y - 1) && !cases[c.x][c.y - 1].estVisible)
			interact(cases[c.x][c.y - 1]);

		if (coordValides(c.x + 1, c.y) && !cases[c.x + 1][c.y].estVisible)
			interact(cases[c.x + 1][c.y]);
		if (coordValides(c.x, c.y + 1) && !cases[c.x][c.y + 1].estVisible)
			interact(cases[c.x][c.y + 1]);

		if (coordValides(c.x - 1, c.y + 1) && !cases[c.x - 1][c.y + 1].estVisible)
			interact(cases[c.x - 1][c.y + 1]);
		if (coordValides(c.x - 1, c.y - 1) && !cases[c.x - 1][c.y - 1].estVisible)
			interact(cases[c.x - 1][c.y - 1]);

		if (coordValides(c.x + 1, c.y + 1) && !cases[c.x + 1][c.y + 1].estVisible)
			interact(cases[c.x + 1][c.y + 1]);
		if (coordValides(c.x + 1, c.y - 1) && !cases[c.x + 1][c.y - 1].estVisible)
			interact(cases[c.x + 1][c.y - 1]);

	}

	public boolean coordValides(int x, int y) {
		if (x >= 0 && x < taille && y >= 0 && y < taille)
			return true;
		return false;
	}

	public void interact(Case c) {
		c.updateCase();
		c.estVisible = true;
		c.notifier(this);
	}

	public void updateFond() {
		if (modeDrapeau) {
			setBackground(Color.red);
			for (int i = 0; i < taille; i++) {
				for (int j = 0; j < taille; j++) {
					if (!cases[i][j].estVisible && !cases[i][j].estMarque)
						cases[i][j].setBackground(new Color(255, 230, 230));
				}
			}
		} else {
			setBackground(Color.black);
			for (int i = 0; i < taille; i++) {
				for (int j = 0; j < taille; j++) {
					if (!cases[i][j].estVisible && !cases[i][j].estMarque)
						cases[i][j].setBackground(Color.lightGray);
				}
			}
		}
	}
}
