package com.Shorage.scenes;

import com.Shorage.scenes.ActorPersonaje;
import com.Shortage.game.Main;
import com.Shortage.game.Pantalla;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.utils.viewport.*;

public class PantallaScene extends Pantalla {
	
	private Stage escenario;
	private ActorPersonaje personaje;
	private ActorFondo fondo;
	private OrthographicCamera camara; 
	
	float Speed=100;
	
	
	public PantallaScene(Main game) {
		super(game);
		camara=new OrthographicCamera();
		camara.setToOrtho(false, 800, 600);
	
		escenario = new Stage(new ScreenViewport(camara));
		Gdx.input.setInputProcessor(escenario);
		
		fondo = new ActorFondo();
		//ondo.setSize(escenario.getWidth(), escenario.getHeight());
		//fondo.setPosition(escenario.getWidth()/2,escenario.getHeight()/2);
		fondo.setCamara(camara);
		escenario.addActor(fondo);
		
		personaje = new ActorPersonaje();
		personaje.setPosition(50,100);
		//personaje.setTouchable(Touchable.enabled);
		escenario.addActor(personaje);
		
//		personaje.addCaptureListener(new InputListener() {
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				personaje.addAction(Actions.color(Color.GREEN, 1));
//				return true;
//			}
//			
//			@Override
//			public void touchUp(InputEvent event, float x, float y,
//					int pointer, int button) {
//				personaje.addAction(Actions.color(Color.WHITE, 1));
//			}
//		});
		
	}
	
	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		escenario.draw();
		escenario.act();
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			//personaje.setPosition(personaje.getX()+Gdx.graphics.getDeltaTime()*Speed,personaje.getY());
			camara.position.set(camara.position.x+1, camara.position.y, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			//personaje.setPosition(personaje.getX()-Gdx.graphics.getDeltaTime()*Speed,personaje.getY());
				camara.position.set(camara.position.x-1, camara.position.y, camara.position.z);
			}
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			//personaje.setPosition(personaje.getX(),personaje.getY()+Gdx.graphics.getDeltaTime()*Speed);
			camara.position.set(camara.position.x, camara.position.y+1, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			//personaje.setPosition(personaje.getX(),personaje.getY()-Gdx.graphics.getDeltaTime()*Speed);
				camara.position.set(camara.position.x, camara.position.y-1, camara.position.z);
			}
		

	}
	
	@Override
	public void resize(int width, int height) {
		escenario.getViewport().update(width, height);
		fondo.setSize(width, height);
	}
}
