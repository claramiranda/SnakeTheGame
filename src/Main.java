import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hey!");
		
		JFrame frame = new JFrame();
		Gamepanel gamepanel = new Gamepanel();
		
		frame.add(gamepanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Jogo da Cobrinha");
		
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

}
