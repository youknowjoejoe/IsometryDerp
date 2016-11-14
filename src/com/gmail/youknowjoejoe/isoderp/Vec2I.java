package com.gmail.youknowjoejoe.isoderp;

public class Vec2I {
	public final int x;
	public final int y;
	
	public Vec2I(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Vec2I(Vec2I a, Vec2I b){
		x = b.x-a.x;
		y = b.y-a.y;
	}
	
	public Vec2I plus(Vec2I v){
		return new Vec2I(x+v.x,y+v.y);
	}
	
	public Vec2I minus(Vec2I v){
		return new Vec2I(x-v.x,y-v.y);
	}
	
	public float dot(Vec2I v){
		return x*v.x+y*v.y;
	}
	
	public Vec2I scaledBy(int f){
		return new Vec2I(x*f,y*f);
	}
}
