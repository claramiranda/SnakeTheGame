import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 
 * @author Clara Anna, Osvaldo Neto
 * 
 */

public class Gamepanel extends JPanel implements Runnable, KeyListener {

	//Vari�vel obrigat�ria
	private static final long serialVersionUID = 1L;

	// Dimens�es da tela do jogo
	public static final int WIDTH = 500, HEIGHT = 500;

	//Variavel que guarda o tempo de atualiza��o da tela, ou seja, a velocidade da
	//cobrinha. Valores em milisegundos
	private int updateScreenInterval = 100;

	// Dire��es de movimenta��o da cobrinha
	private boolean right = true, left = false, up = false, down = false;

	// Corpo da cobrinha. Composto por uma lista de 'Bodypart'
	private ArrayList<BodyPart> snake;

	// Comida dispon�vel na tela
	private Food apple;

	// Placar do jogo
	private int score;

	//variavel auxiliar para gerar numeros aleat�rios
	private Random randomNumber;
	
	//objeto para manipular os efeitos sonoros
	private SoundEffects sons = new SoundEffects();

	// Coordenadas iniciais e tamanho do corpo da cobrinha
	private int xCoor = 10, yCoor = 10, bodySize = 3;

	/**
	 * Construtor do Panel
	 * cria o jpannel e inicia o jogo
	 */
	public Gamepanel() {
		StartGame();
	}

	/**
	 * Fun��o respons�vel por inicializar os par�metros do jogo
	 */
	private void StartGame() {
		// Define as coordenadas iniciais da cobrinha
		xCoor = 10;
		yCoor = 10;

		// Define o sentido inicial (direita)
		right = true;
		left = false;
		up = false;
		down = false;

		// Define o tamanho do corpo inicial da cobrinha (3 quadrados)
		bodySize = 3;

		// Define a velocidade inicial da cobrinha
		updateScreenInterval = 100;

		// Define o corpo da cobrinha como uma lista vazia
		snake = new ArrayList<BodyPart>();

		// Define o placar iniciando em 0
		score = 0;

		// Vari�vel para n�meros aleat�rios
		randomNumber = new Random();

		// Posiciona a primeira comida na tela
		apple = new Food(randomNumber.nextInt(49), randomNumber.nextInt(49), 10);

		//M�todos de manipula��o do pannel
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	
		//inclui o listener do teclado
		addKeyListener(this);
		
		//Inicia a thread do jogo
		new Thread(this).start();
		
		//executa som de �nicio da partida
		sons.newGameSound();
	}

	/**
	 * M�todo de manipula��o de momento do jogo
	 * @return false quando a partida termina
	 */
	public boolean Tick() {
		boolean retorno = true;

		// Caso a lista de corpo da cobra esteja vazia, ent�o adiciona um elemento
		if (snake.size() == 0)
			snake.add(new BodyPart(xCoor, yCoor, 10));

		if (right)
			xCoor++;
		if (left)
			xCoor--;
		if (up)
			yCoor--;
		if (down)
			yCoor++;

		snake.add(new BodyPart(xCoor, yCoor, 10));

		if (snake.size() > bodySize) {
			snake.remove(0);
		}

		// Colis�o da cobra com a comida. Aumenta um elemento no corpo da cobra, um
		// ponto e diminui o tempo
		if (xCoor == apple.getxCoor() && yCoor == apple.getyCoor()) {
			bodySize++;
			score++;

			if (updateScreenInterval > 10)
				updateScreenInterval -= 2;

			apple = new Food(randomNumber.nextInt(49), randomNumber.nextInt(49), 10);
			sons.eatSound();
		}

		// colisao com o corpo da cobra
		for (int i = 0; i < snake.size(); i++) {
			if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {	
				if (i != snake.size() - 1) {
					sons.gameOverSound();
					int j = JOptionPane.showConfirmDialog(null,"Neste jogo n�o � permitido canibalismo. Infelizmente voc� comeu "
							+ score	+ " frutinhas e perdeu. Deseja jogar de novo?","TextEditor", 0); 
					if(j == 0){ 
						System.out.println("[LOG] Nova partida");
						retorno = false;
					}
					else if (j == 1){ 
						System.out.println("[LOG] Sair do jogo"); 
						System.exit(0);
					}
				}				
			}
		}

		// colisao da cobra com as paredes
		if (xCoor < 0 || xCoor > 50 || yCoor < 0 || yCoor > 50) {
			sons.gameOverSound();
			int i = JOptionPane.showConfirmDialog(null, "\"Nossa cobrinha ainda n�o atravessa paredes. "
					+ "Infelizmente voc� comeu apenas " +  score + " frutinhas e perdeu. Deseja jogar de novo?","TextEditor", 0); 
			if(i == 0){ 
				System.out.println("[LOG] Nova partida");
				retorno = false;
			}
			else if (i == 1){ 
				System.out.println("[LOG] Sair do jogo"); 
				System.exit(0);
			}
		}

		try {
			// Aqui � o controle de atualiza��o tempo de tela, logo � o controle da velocidade da cobrinha
			Thread.sleep(updateScreenInterval);
		} catch (InterruptedException e) {

		}

		repaint();

		return retorno;
	}

	
	/**
	 * M�todo de manipula��o dos gr�ficos
	 */
	public void paint(Graphics g) { 
		
		//Cor do tabuleiro
		g.setColor(Color.GRAY); 
		
		//inicializa��o do tabuleiro
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}

		for (int i = 0; i < HEIGHT; i++) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}
		
		//desenha a cobrinha
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		//Desenha a comida na tela
		apple.draw(g);
		
	}

	@Override
	public void run() {
		while (Tick()) {
		}
		StartGame();
	}

	/**
	 * M�todos que configuram as a��es dos bot�es
	 */
	@Override 
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}

		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;
		}

		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			left = false;
			right = false;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
}
