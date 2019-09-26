import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.util.Random;

import javazoom.jl.player.Player;

public class Food {

	 private int xCoor, yCoor, width, height;
	 		 Color color;
	 
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
	 
	 public void tick() {
	  
	 }
	
	 
	 public void draw(Graphics g) {
		 g.setColor(this.color); 
		 g.fillRect(xCoor * width, yCoor * height, width, height);
	 }

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
