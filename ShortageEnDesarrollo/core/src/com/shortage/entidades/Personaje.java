package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class Personaje  {
	
	Texture skin;
	SpriteBatch batch;
	int vida;
	int vidamax;
	float posX, posY;
	Sprite sprite;
	
	public enum ESTADO_ACTUAL{Atacando, Moviendose, Quieto}
	public ESTADO_ACTUAL estado;
	public enum VISTA_ACTUAL{Arriba, abajo, izquierda, derecha}
	public VISTA_ACTUAL vista;
	
    public Personaje(){
    skin = new Texture(Gdx.files.internal("Jimmy.png"));
    batch = new SpriteBatch();
    sprite = new Sprite(skin);
    posX=50;
    posY=50;
    
    }
	
    public void render(float delta){
    	batch.begin();
    	sprite.setPosition(posX, posY);
    	sprite.draw(batch);
    	batch.end();
    }

    public void moverDerecha(float delta, float velocidad){
      	posX=posX+velocidad*delta;
    }
    
    public void moverIzquierda(float delta, float velocidad){
      	posX=posX-velocidad*delta;
    }
    
    public void moverArriba(float delta, float velocidad){
      	posY=posY+velocidad*delta;
    }
    
    public void moverAbajo(float delta, float velocidad){
      	posY=posY-velocidad*delta;
    }
    
    public void actualizarPosicion(float x, float y){
    	posX=x;
    	posY=y;	
    }
    
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	

}




