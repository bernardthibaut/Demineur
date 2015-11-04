import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Horloge implements Runnable {

	JDialog f = new JDialog();
	JLabel text = new JLabel("0:0:0");

	int seconde = 0;
	int minute = 0;
	int heure = 0;

	public Horloge() {

		f.setPreferredSize(new Dimension(100, 75));
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		f.add(text);

		f.pack();
		f.setLocation(350, 450);
		f.setVisible(true);
	}

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		seconde++;

		text.setText(heure + " : " + minute + " : " + seconde);

	}

	public String heureString() {
		String result = "";

		return result;
	}

}
