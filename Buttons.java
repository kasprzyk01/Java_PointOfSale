package pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton buttonScann;
	private JButton buttonExit;
	private JButton buttonBill;
	private JButton buttonInfo;
	String product = "Hello!";
	String prizeString = " ";
	String warning1;
	String warning2;
	String[] tablica = new String[25];
	int counter = 0;
	int totalPrize = 0;
	int prize;
	

	public Buttons() {
		buttonScann = new JButton("Scann");
		buttonBill = new JButton("Print a bill");
		buttonExit = new JButton("Exit");
		buttonInfo = new JButton("Info");

		add(buttonScann);
		add(buttonBill);
		add(buttonInfo);
		add(buttonExit);

		buttonScann.addActionListener(this);
		buttonExit.addActionListener(this);
		buttonBill.addActionListener(this);
		buttonInfo.addActionListener(this);

		tablica1();

	}
//---------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
// Obsługa przycisków
		Object source = e.getSource();
		if (source == buttonScann) {

			wczytaj();
			if (product != "") {
				counter++;
				if (counter == 15) {
					JOptionPane.showMessageDialog(this, "Print the receipt!");
				}
				przypisz();
			}
			// repaint();
			// paintComponent(getGraphics());
		} else if (source == buttonExit) {
			int n = JOptionPane.showConfirmDialog(this, "Exit?", "Question", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				System.exit(0);
			}
		} else if (source == buttonBill) {

			bill();
			tablica1();
			warning1 = "";
			product = "";
			prizeString = "";
			warning2 = "";
			rysuj(getGraphics(), 2);
			totalPrize = 0;
			counter = 0;
		} else if (source == buttonInfo) {
			JOptionPane.showMessageDialog(this,
					"Press scann to simulate process of scanning the product. To print the recipt "
							+ "press Print the bill. ");
		}

	}
//---------------------------------------------------------------------------
	public void wczytaj() {
		// Funkcja obsługująca pobieranie kodów kreskowych z pliku barCodes.txt
		Scanner scanner = new Scanner();
		int a = scanner.scann();
		Boolean isFound = false;
		int b = scanner.countLines();
		try {
			File plik;
			plik = new File("barCodes.txt");
			FileReader reader = new FileReader(plik);
			BufferedReader bReader = new BufferedReader(reader);
			for (int i = 0; i < b; i++) {
				for (int j = 0; j < b; j++) {
					String line = bReader.readLine();
					if (isFound == false) {
						if (Integer.parseInt(line.substring(0, line.indexOf(" "))) == a) {
							product = line.substring(line.indexOf(" ") + 1, line.indexOf("$"));
							prize = Integer.parseInt(line.substring(line.indexOf("$") + 1, line.length()));
							totalPrize = totalPrize + prize;
							isFound = true;
							prizeString = (Integer.toString(prize) + "$");
							tablica[i] = product + "  " + prizeString;
							warning1 = "";
							warning2 = "";
							paintComponent(getGraphics());
							rysuj(getGraphics(), 1);
							break;
							
						} else if (a == 0) {
							isFound = true;
							warning1 = "Invalid bar-code";
							product = "";
							prizeString = "";
							warning2 = "";
							paintComponent(getGraphics());
							rysuj(getGraphics(), 1);

							break;

						}
					}
				}
				if (isFound == false) {
					// JOptionPane.showMessageDialog(this, "Product not found",
					// "Information",
					// JOptionPane.WARNING_MESSAGE);
					warning2 = "Product not found";
					product = "";
					prizeString = "";
					warning1 = "";
					paintComponent(getGraphics());
					rysuj(getGraphics(), 1);
					break;

				}
			}
			bReader.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "błąd");
			e.printStackTrace();
		}
	}
//----------------------------------------------------------------------------
	public void paintComponent(java.awt.Graphics g) {
		//Funkcja wyświetlająca kasę fiskalną
		super.paintComponent(g);
		// if(c == 1){
		java.awt.Image img = new ImageIcon("cash.png").getImage();
		g.drawImage(img, 150, 100, 520, 481, null);
		Font f = new Font("Times New Roman", Font.PLAIN, 30);
		g.setColor(Color.BLACK);
		g.setFont(f);
		g.drawString(product, 280, 165);

	}
//---------------------------------------------------------------------------
	public void rysuj(java.awt.Graphics g, int n) {

		//Funkcja wyświetlająca nazwę prodktu i cenę na wyświetlaczu kasy
		super.paint(g);
		Font f = new Font("Times New Roman", Font.PLAIN, 30);
		g.setColor(Color.BLACK);
		g.setFont(f);
		if (n == 1) {

			g.drawString(prizeString, 450, 165);
			g.drawString(warning1, 280, 165);
			g.drawString(warning2, 280, 165);
		}
		if (n == 2) {
			g.drawString("Total prize: ", 280, 165);
			String totalPrizee = (Integer.toString(totalPrize) + "$");
			g.drawString(totalPrizee, 450, 165);

		}

	}
//---------------------------------------------------------------------------
	public void bill() {
		//Funkcja drukująca paragon
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = dateFormat.format(currentDate);
		JOptionPane.showMessageDialog(this,
				"               SHOP \n " + "              " + dateString + "\n" + "\n" + tablica[1] + "\n"
						+ tablica[2] + "\n" + tablica[3] + "\n" + tablica[4] + "\n" + tablica[5] + "\n" + tablica[6]
						+ "\n" + tablica[7] + "\n" + tablica[8] + "\n" + tablica[9] + "\n" + tablica[10] + "\n"
						+ tablica[11] + "\n" + tablica[12] + "\n" + tablica[13] + "\n" + tablica[14] + "\n"
						+ "------------------------- \n" + "Total prize:       " + totalPrize + "$",
				"Bill", JOptionPane.INFORMATION_MESSAGE);
	}
//---------------------------------------------------------------------------
	public void przypisz() { 
		// Funkcja przypisuąca Stringi z nazwą produktu i ceną do tablicy
		int dlugosc = product.length();
		String space = "";
		for (int i = 0; i < (20 - dlugosc); i++) {
			space = space + " ";
		}

		tablica[counter] = product + prizeString;
	}
//---------------------------------------------------------------------------
	public void tablica1() {
		// Funkcja wpisująca wartość "" do tablicy by nie wyświetlać "null"
		for (int i = 0; i < 15; i++) {
			tablica[i] = "";
		}
	}
//---------------------------------------------------------------------------
}
