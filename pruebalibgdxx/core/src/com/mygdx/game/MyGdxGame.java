package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imagen;
	Sprite personaje;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		imagen = new Texture("golf.png");
		personaje = new Sprite(imagen, 0, 0, 64, 64);
		personaje.setPosition(50, 50);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		personaje.draw(batch);
		batch.end();
		ProcesarEntrada();
	}
	
	public void ProcesarEntrada(){
	  boolean arriba =Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP);
	  boolean izquierda= Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT);
	  boolean derecha=Gdx.input.isKeyPressed(Input.Keys.D)|| Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) ;
	  boolean abajo=Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN);
	float x= personaje.getX();
	float y = personaje.getY();
	  
	  if(arriba && !abajo)
		  y=y+4;
	  else if(abajo && !arriba)
		  y=y-4;
	  if(izquierda && !derecha)
		  x=x-4;
	  else if(derecha && !izquierda)
		  x=x+4;
		  
	personaje.setPosition(x, y);
	
	}
}
