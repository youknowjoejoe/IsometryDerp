package com.gmail.youknowjoejoe.isoderp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage img;
	
	public Tile(BufferedImage img){
		this.img = img;
	}
	
	public void draw(Graphics2D g2d, Vec2 tx){
		g2d.drawImage(img, -img.getWidth()/2 + (int) tx.x, -img.getHeight()/2 + (int) tx.y, null);
	}
}
