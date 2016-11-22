package com.gmail.youknowjoejoe.isoderp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener{
	private Vec2 pos;
	private boolean upDown;
	private boolean downDown;
	private boolean rightDown;
	private boolean leftDown;
	private float speed = 400.0f;
	
	public Player(Vec2 pos){
		this.pos = pos;
	}
	
	public Vec2 getPos(){
		return pos;
	}
	
	public void update(float dt){
		Vec2 movement = new Vec2(0,0);
		if(upDown) movement = movement.plus(new Vec2(0,-speed));
		if(rightDown) movement = movement.plus(new Vec2(speed,0));
		if(leftDown) movement = movement.plus(new Vec2(-speed,0));
		if(downDown) movement = movement.plus(new Vec2(0,speed));
		movement = movement.scaledBy(dt);
		
		this.pos = this.pos.plus(movement);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rightDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			leftDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			downDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upDown = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rightDown = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			leftDown = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			downDown = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
