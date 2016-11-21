package com.gmail.youknowjoejoe.isoderp;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Color;

@SuppressWarnings("serial")
public class GraphicsPanel extends JPanel implements Runnable{
    
    private final int WIDTH = 1024;
    private final int HEIGHT = 768;
    
    private Color clearScreen = new Color(0,0,0);
    private BufferedImage frameRender = null;
    public float dt;
    private double oldTime;
    private double currentTime;
    private double accumulatedTime = 0;
    private Double timePassed = 0.0;
    private double timeScale;
    
    private boolean running = true;
    
    private Scene currentScene;
    
    public GraphicsPanel(float dt, float timeScale){
    	this.dt = dt;
    	this.timeScale = timeScale;
    	
    	this.setFocusable(true);
    	this.requestFocus();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        
        BufferedImage[] imgs = new BufferedImage[4];
        try {
        	imgs[0] = ImageIO.read(getClass().getResource("/resources/stoneTile.png"));
        	imgs[1] = ImageIO.read(getClass().getResource("/resources/stoneWall.png"));
        	imgs[2] = ImageIO.read(getClass().getResource("/resources/grassTile.png"));
        	imgs[3] = ImageIO.read(getClass().getResource("/resources/treeTile.png"));
        } catch (IOException e){
        	e.printStackTrace();
        }
        
        Player p = new Player(new Vec2(0,0));
        this.addKeyListener(p);
        int[][] tiles = new int[30][30];
        for(int rep = 0; rep < tiles.length * tiles[0].length; rep++){
        	tiles[rep/tiles.length][rep%tiles.length] = 2;
        }
        for(int rep = 0; rep < 30; rep++){
            tiles[rep][rep] = 1;
        }
        for(int rep = 30-1; rep >= 0; rep--){
            tiles[rep][29-rep] = 1;
        }
        Tile[] tileIDs = {new Tile(imgs[0]),new Tile(imgs[1]), new Tile(imgs[2]), new Tile(imgs[3])};
        currentScene = new Scene(tiles, 28, 16, tileIDs,p,WIDTH,HEIGHT);
        this.addMouseListener(currentScene);
        this.addMouseMotionListener(currentScene);
    }
    
    @Override
    public void run(){
    	currentTime = System.nanoTime()/1000000000.0;
    	oldTime = currentTime;
    	
        while(running){
            this.cycle();
        }
    }
    
    public void cycle(){
    	currentTime = System.nanoTime()/1000000000.0;
    	accumulatedTime += (currentTime-oldTime)*timeScale;
    	
    	this.updateInput();
    	
    	while(accumulatedTime > dt){
    		this.logic(dt);
    		accumulatedTime-=dt;
    		timePassed+=dt;
    	}
        this.repaint();
        
        this.oldTime = currentTime;
    }
    
    public void updateInput(){
    	this.requestFocus();
    }
    
    public void logic(float dt){
    	currentScene.update(dt);
    }
    
    @Override
    public void paintComponent(Graphics g){
    	
    	frameRender = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    	
    	//save a normal frame onto frameRender
    	{
	        Graphics2D g2d = frameRender.createGraphics();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        g2d.fillRect(0,0,WIDTH,HEIGHT);
	        
	        AffineTransform save = g2d.getTransform();
	        
	        currentScene.draw(g2d);
	        
	        g2d.setTransform(save);
	        
	        g2d.dispose();
    	}
    	
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(clearScreen);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        
        g2d.drawImage(frameRender, 0, 0, null);
    }
    
    public static void main(String[] args){
        JFrame window = new JFrame("Isometric Editor");
        GraphicsPanel pane = new GraphicsPanel(1.0f/60.0f,0.4f);
        window.add(pane);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.pack();
        (new Thread(pane)).start();
    }
}
