import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *  Classe Food, responsável por criar os objetos de comidinha 
 * @author Clara Anna
 *
 */

public class Food {

	 private int xCoor, yCoor, width, height;
	 		 Color color;
	 /**
	  * 
	  * @param xCoor - valor da posição X
	  * @param yCoor - valor da posição Y
	  * @param tileSize - tamanho do bloco
	  */
	 public Food(int xCoor, int yCoor, int tileSize) {
		 this.xCoor = xCoor;
		 this.yCoor = yCoor;
		 width = tileSize;
		 height = tileSize;
		 
		 //gerando cor aleatória para a comidinha
		 Random randomNumber = new Random();
		 int color_R, color_G, color_B;
		 color_R = randomNumber.nextInt(255);
		 color_G = randomNumber.nextInt(255);
		 color_B = randomNumber.nextInt(255);
		 color = new Color(color_R, color_G, color_B);
		 
		 System.out.println("[LOG] Posicionando comidinha. X: "+this.xCoor+", Y: "+this.yCoor);
	 }
	 
	/**
	 *  Método que desenha a comidinha
	 * @param g
	 */
	 public void draw(Graphics g) {
		 g.setColor(this.color); 
		 g.fillRect(xCoor * width, yCoor * height, width, height);
	 }

	 //Getters e Setters
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
