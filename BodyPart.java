import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe que compõe os objetos que formam o corpo da cobrinha
 * 
 * @author Clara Anna
 *
 */
public class BodyPart {
	
	private int xCoor, yCoor, width, height;
	
	/**
	 * Construtor da classe
	 * @param xCoor - valor da posição X
	 * @param yCoor - valor da posição Y
	 * @param tileSize - tamanho do bloco
	 */
	public BodyPart(int xCoor, int yCoor, int titleSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = titleSize;
		height = titleSize;		
	}
		
	/**
	 * método que desenha o corpo da cobrinha
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width, yCoor * height, width, height);	
	}
	
	
	//GETTERS AND SETTERS
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	

	

}
