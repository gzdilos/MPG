import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class HotkeyHandler implements KeyListener{

	private GUI gui;
	private MasterMindGame g;
	
	public HotkeyHandler(GUI gui) {
		this.gui = gui;
		g = gui.getGame();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int x = arg0.getKeyCode();
		
		if (x == KeyEvent.VK_R) {
			this.gui.setInputIndicator("red");
			this.gui.setInputToUse(g.RED);
		}
		
		if (x == KeyEvent.VK_B) {
			this.gui.setInputIndicator("blue");
			this.gui.setInputToUse(g.BLUE);
		}
		
		if (x == KeyEvent.VK_G) {
			this.gui.setInputIndicator("green");
			this.gui.setInputToUse(g.GREEN);
		}
		
		if (x == KeyEvent.VK_W) {
			this.gui.setInputIndicator("white");
			this.gui.setInputToUse(g.WHITE);
		}
		
		if (x == KeyEvent.VK_Y) {
			this.gui.setInputIndicator("yellow");
			this.gui.setInputToUse(g.YELLOW);
		}
		
		if (x == KeyEvent.VK_P) {
			this.gui.setInputIndicator("pink");
			this.gui.setInputToUse(g.PINK);
		}
		
//		if (c == "r".toCharArray()[0]) {
//			this.gui.setInputToUse(g.RED);
//		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
//		int x = arg0.getKeyCode();
//		char c = arg0.getKeyChar();
//		
//		
//		
//		if (c == "r".toCharArray()[0]) {
//			this.gui.setInputToUse(g.RED);
//		}
	}
	
	

}
