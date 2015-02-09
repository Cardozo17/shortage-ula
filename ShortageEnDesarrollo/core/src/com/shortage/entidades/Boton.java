package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Boton {
	
	protected Texture textura;
	protected Rectangle bordes;
	float Xmin, Ymin, Xmax, Ymax;

	public Boton(int x, int y){
		
	 Texture texture= new Texture(Gdx.files.internal("botonjugar.png"));
	 bordes= new Rectangle(x,y, texture.getWidth(), texture.getHeight());
	 
	 Xmin= x;
	 Ymax= Gdx.graphics.getHeight()-y;
	 Xmax= x + bordes.width;
	 Ymin= Gdx.graphics.getHeight()-(y + bordes.height);
	 
				
	}
	
	public void draw(SpriteBatch batch){
		
		batch.draw(textura, bordes.x, bordes.y, bordes.width, bordes.height);
	
	}
	
	public void update(){
		
		if(sepulsaboton())
		{
			funcionamiento();
			
		}	
	}
	
	private boolean sepulsaboton()
	{
		return Gdx.input.isTouched() && Gdx.input.getX()>= Xmin && Gdx.input.getX()<=Xmax
				&& Gdx.input.getY()>=Ymin && Gdx.input.getY()<=Ymax;
		
	}
	
	public Rectangle getBordes()
	{
		return bordes;
	}
	
	public abstract void funcionamiento();
	

}
