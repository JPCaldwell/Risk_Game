package oldCode;

import java.io.*;

public class LoadSave {
	private static OldDriver game;
	public static void main(String[] args) {
		try {
			ObjectInputStream os = new ObjectInputStream(new FileInputStream("Save.ser"));
			Object one = os.readObject();
			game = (OldDriver) one;
			os.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		game.startGame();
	}
	
}
