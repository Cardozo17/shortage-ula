package com.shortage.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Shape;
import com.shortage.entidades.Personaje;
import com.shortage.game.Shortage;

public class PantallaPlay extends Pantalla implements InputProcessor {
	static final float velocidad=300;
	private TiledMap mapa;
	OrthographicCamera camara;
	TiledMapRenderer renderizarMapa;
	Personaje heroe;
	SpriteBatch batch;
		
	public PantallaPlay(Shortage game) {
		super(game);
		mapa= new TmxMapLoader().load("mapa1.2.tmx");
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camara.update();
		renderizarMapa= new OrthogonalTiledMapRenderer(mapa);
		heroe= new Personaje();
		//Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float delta){
        Gdx.gl.glClearColor(0,128,0, 8);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//heroe.draw(batch, delta);
		//batch.end();
		renderizarMapa.setView(camara);
		renderizarMapa.render();
		heroe.render(delta);
		manejoEntrada(delta);
		camara.update();
	}
	
	public void manejoEntrada(float delta){
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			heroe.mirarDerecha();
			heroe.estadoMoviendose();
			//heroe.moverDerecha(delta, velocidad);
			camara.position.set(camara.position.x+velocidad*delta, camara.position.y, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			heroe.mirarIzquierda();
			heroe.estadoMoviendose();
			//heroe.moverIzquierda(delta, velocidad);
			camara.position.set(camara.position.x-velocidad*delta, camara.position.y, camara.position.z);
			}
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			
			heroe.mirarArriba();
			heroe.estadoMoviendose();
			//heroe.moverArriba(delta, velocidad);
			camara.position.set(camara.position.x, camara.position.y+velocidad*delta, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			heroe.mirarAbajo();
			heroe.estadoMoviendose();
			//heroe.moverAbajo(delta, velocidad);
			camara.position.set(camara.position.x, camara.position.y-velocidad*delta, camara.position.z);
			}
	
		if(!Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) )
		heroe.estadoQuieto();

		
	}
	
		
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
		
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
