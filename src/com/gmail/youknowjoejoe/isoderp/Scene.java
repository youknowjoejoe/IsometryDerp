package com.gmail.youknowjoejoe.isoderp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Scene implements MouseMotionListener, MouseListener {
    
    private int[][] tiles;
    private Tile[] tileIDs;
    private int width;
    private int height;
    private Vec2 mousePos = new Vec2(0,0);
    private boolean mouseDown;
    private Player p;
    
    private Vec2 tx;
    private int WIDTH;
    private int HEIGHT;
    
    public Scene(int[][] tiles, int width, int height, Tile[] tileIDs, Player p, int WIDTH, int HEIGHT){
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.tileIDs = tileIDs;
        this.p = p;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }
    
    public void update(float dt){
        p.update(dt);
    }
    
    public void draw(Graphics2D g2d){
        tx = new Vec2(-p.getPos().x + WIDTH/2,-p.getPos().y+14);
        g2d.translate(tx.x,tx.y);
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                Vec2 pos = new Vec2((x*width-y*width)/2,(x*height+y*height)/2);
                tileIDs[tiles[x][y]].draw(g2d, pos);
                Vec2I tmp = mousePos.minus(tx).plus(new Vec2(0,0)).getVec2I();
                if(Math.floor((tmp.x*height+tmp.y*width)/(width*height)) == x && Math.floor((tmp.y*width-tmp.x*height)/(width*height)) == y){
                    g2d.setColor(Color.red);
                    g2d.fillRect((x*width-y*width)/2-width/2,(x*height+y*height)/2-height/2, width, height);
                    if(mouseDown){
                        //tiles[x][y] = (-(tiles[x][y]*2-1)+1)/2;
                        //System.out.println(tiles[x][y]);
                        tiles[x][y] ^= 1;
                        //0,1
                        //0,2
                        //-1,1
                        //1,-1
                        //2,0
                        //1,0
                        mouseDown = false;
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	mousePos = new Vec2(e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = new Vec2(e.getX(),e.getY());
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

}
