import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Menu extends JPanel {

	int largeur = 400;
	int hauteur = 500;

	int txMines = 15;

	Fenetre f;

	public Menu(Fenetre f) {
		f.setPreferredSize(new Dimension(largeur, hauteur));
		f.setResizable(false);

		setBackground(Color.white);
		setLayout(null);

		JLabel titre = new JLabel("Démineur", SwingConstants.CENTER);
		titre.setForeground(Color.red);
		titre.setFont(new Font("arial", 1, 45));
		titre.setBounds(0, 10, 400, 50);

		JLabel textMines = new JLabel("Taux de mines", SwingConstants.CENTER);
		textMines.setBounds(0, 75, 400, 30);
		textMines.setFont(new Font("arial", 1, 20));

		JTextField mines = new JTextField("15");
		mines.setBounds(170, 115, 50, 25);

		JLabel pourcent = new JLabel("%");
		pourcent.setFont(new Font("arial", 0, 20));
		pourcent.setBounds(225, 118, 30, 20);

		JButton jouer = new JButton("JOUER");
		jouer.setFont(new Font("arial", 1, 30));
		jouer.setBackground(new Color(150, 255, 150));
		jouer.setForeground(new Color(50, 150, 50));
		jouer.setBounds(0, 400, 400, 50);
		jouer.setFocusable(false);

		mines.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				if ((int) key == KeyEvent.VK_ESCAPE) {
					System.exit(1);
				} else if ((int) key == KeyEvent.VK_ENTER) {
					jouer.doClick();
				} else if (key < '0' || key > '9') {
					e.consume();
				} else {
					int total = Integer.parseInt(mines.getText() + Integer.parseInt(key + ""));
					if (total > 100) {
						e.consume();
					}
				}

			}

		});

		jouer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int total = Integer.parseInt(mines.getText());
					f.txMines = total;
				} catch (Exception e2) {}
				f.rejouer();
			}
		});
		
		jouer.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				jouer.setForeground(new Color(150, 255, 150));
				jouer.setBackground(new Color(25, 125, 25));
			}

			public void mouseExited(MouseEvent e) {
				jouer.setBackground(new Color(150, 255, 150));
				jouer.setForeground(new Color(50, 150, 50));
			}

		});

		add(titre);
		add(textMines);
		add(mines);
		add(pourcent);
		add(jouer);
	}
}
