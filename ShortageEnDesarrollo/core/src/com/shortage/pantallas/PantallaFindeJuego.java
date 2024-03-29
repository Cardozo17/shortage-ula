package com.shortage.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shortage.entidades.BotonSalir;
import com.shortage.game.Shortage;

public class PantallaFindeJuego extends PantallaAbstracta  {
	
	private static SpriteBatch batch;
	private Texture fondo;
	private BotonSalir botonjugar;
	
	public PantallaFindeJuego(Shortage game) {

		super(game);
		fondo = new Texture(Gdx.files.internal("juegoterminadoTexture.png"));
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());	
		botonjugar.draw(batch);
		batch.end();
		saliralMenu();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch= Shortage.getBatch();
		botonjugar=new BotonSalir(300,100);
		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		
	}
	
public void saliralMenu(){
		
	
	}

	
	
	
	

}
