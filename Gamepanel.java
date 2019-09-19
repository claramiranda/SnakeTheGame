import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = 800; //Dimensoes do jogo
	
	private Thread thread;
	
	private boolean running;
	private boolean right = true, left = false, up = false, down = false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Food apple;
	private ArrayList<Food> apples;
	private int placar;
	
	private Random r;
	
	private int xCoor = 10, yCoor = 10, size = 3;
	private int ticks = 0;
	
	

	//construtor do panel
	// monta tabuleiro, add listener, cria a cobra e começa a rodar
	public Gamepanel() {
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Food>();
		placar = 0;
		r = new Random();
		//System.out.println("Construtor Jpanel");
		start();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();

	}
	
	public void stop() {
		//metodo pra fechar a thread do jogo
		running = false;
		
		//opçoes de replay devem ser inseridas abaixo
		System.out.println("Comeu "+ placar +" frutinhas!");
			
	}
	
	public void tick() { //metodo de momentos
		if(snake.size() == 0) {
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		
		ticks++;
		
		if(ticks > 250000) {  //Aqui é onde a magia do movimento acontece, não mexe nesse valor pq ele controla a velocidade da cobra 
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			ticks = 0;
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			if (snake.size() > size) {
				snake.remove(0);
			}
		}
		
		
		//posiciona as comidinhas
		if(apples.size() == 0) {
			int x = r.nextInt(79);  //NAO MUDAR ESSES VALORES SENAO A COMIDA VAI APARECER FORA DO TABULEIRO
			int y = r.nextInt(79);
			
			apple = new Food(x, y, 10);
			apples.add(apple);
		}
		
		
		//colisao com as comidinhas
		for(int i = 0 ; i < apples.size() ; i++) {
			if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
				size++;
				placar++;
				apples.remove(i);
				i++;
			}
		}
		
		//colisao com o corpo da cobra
		for(int i = 0 ; i < snake.size(); i++) {
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if(i != snake.size()-1) {
					System.out.println();
					stop();
				}
			}
		}
		
		//colisao da cobra com as paredes
		if(xCoor < 0 || xCoor > 80 || yCoor < 0 || yCoor > 80) {
			System.out.println("Game Over");
			stop();
		}
		
		
	}
	
	public void paint(Graphics g) { //metodo onde ocorre todo o trabalho gráfico (vou mexer ainda)
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < WIDTH / 10; i++ ) {
			g.drawLine(i * 10, 0, i*10, HEIGHT);
		}
		
		for(int i = 0; i < HEIGHT; i++ ) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		for(int i = 0 ; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}

	@Override
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}

	@Override //metodos que setam o listener
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;
		}
		
		if(key == KeyEvent.VK_DOWN && !up) {
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
