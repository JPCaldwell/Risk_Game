package oldCode;

import java.io.*;

public class GameSave {
	private OldDriver game;
	
	public GameSave(OldDriver d) {
		game = d;
	}
	
	public void saveGame() {
		try{ 
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Save.ser"));
			os.writeObject(game);
			os.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
