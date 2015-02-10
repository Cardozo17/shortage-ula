package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Etiqueta {
	
	protected Texture textura;
	protected Rectangle bordes;
	
	public Etiqueta(int x, int y) {
		// TODO Auto-generated constructor stub
		
		Texture texture= new Texture(Gdx.files.internal("Shortage.jpg"));
		bordes= new Rectangle(x,y, texture.getWidth(), texture.getHeight());
	}
	
	
	public void draw(SpriteBatch batch){
		
		batch.draw(textura, bordes.x, bordes.y, bordes.width, bordes.height);
	
	}
	
	public Rectangle getBordes()
	{
		return bordes;
	}
	
	

}
