package pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;


public class Scanner {
	
	public Scanner(){
		
	};
	

	public int scann(){
		//Funkcja losująca kod kreskowy 
		Random generator;
		generator = new Random();
		return generator.nextInt(13);
	}
	public int countLines() {
		// Funkcja zliczająca liczbę lini w pliku barCodes.txt
		int numberOfLines = 0;
		try {
			File plik1 = new File("barCodes.txt");
			FileReader reader1 = new FileReader(plik1);
			BufferedReader bReader1 = new BufferedReader(reader1);
			while (bReader1.readLine() != null) {
			numberOfLines++;
			}
			bReader1.close();

		} catch (Exception e) {
			
			//JOptionPane.showMessageDialog(Frame frame = new Frame(), "Error", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return numberOfLines;
	}
}
 
