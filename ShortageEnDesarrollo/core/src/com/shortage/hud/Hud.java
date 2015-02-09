package com.shortage.hud;

import java.util.Date;

import sun.rmi.runtime.NewThreadAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Hud {

private Date tiempo;
private int objetosRestantes;
private BitmapFont objetosRestantesText;
private SpriteBatch batch;
private int time=0;

	public Hud(){
	tiempo=new Date();
	objetosRestantes=3;
	objetosRestantesText= new BitmapFont();
	objetosRestantesText.setColor(Color.BLUE);
	batch= new SpriteBatch();
	}
	
	
	 
	
	
	public void render(float delta){
		time+=1;
		batch.begin();
		objetosRestantesText.draw(batch, "Productos por recoger:   "+Integer.toString(objetosRestantes), 50,50);
		objetosRestantesText.draw(batch, "Tiempo :   "+tiempo.toLocaleString(), 250,50);
		batch.end();
	}
	
	 public Date getTiempo() {
		return tiempo;
	}

	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}

	public int getObjetosRestantes() {
		return objetosRestantes;
	}

	public void setObjetosRestantes(int objetosRestantes) {
		this.objetosRestantes = objetosRestantes;
	}

	public BitmapFont getObjetosRestantesText() {
		return objetosRestantesText;
	}

	public void setObjetosRestantesText(BitmapFont objetosRestantesText) {
		this.objetosRestantesText = objetosRestantesText;
	}

	public void dispose() {
	       
	        objetosRestantesText.dispose();
	    }
	
	

	
	
	
}
