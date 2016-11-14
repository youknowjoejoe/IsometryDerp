package com.gmail.youknowjoejoe.isoderp;

public class Vec2 {
	public final float x;
	public final float y;
	
	public Vec2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Vec2(Vec2 a, Vec2 b){
		x = b.x-a.x;
		y = b.y-a.y;
	}
	
	public Vec2 plus(Vec2 v){
		return new Vec2(x+v.x,y+v.y);
	}
	
	public Vec2 minus(Vec2 v){
		return new Vec2(x-v.x,y-v.y);
	}
	
	public float dot(Vec2 v){
		return x*v.x+y*v.y;
	}
	
	public Vec2 scaledBy(float f){
		return new Vec2(x*f,y*f);
	}
	
	public Vec2I getVec2I(){
		return new Vec2I((int)this.x,(int)this.y);
	}
	@Override
	public String toString(){
		return "Vec2:["+x+","+y+"]";
	}
}
