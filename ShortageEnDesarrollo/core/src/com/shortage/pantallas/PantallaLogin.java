package com.shortage.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.shortage.entidades.Etiqueta;
import com.shortage.entidades.EtiquetaTitulo;
import com.shortage.entidades.MyTextInputListener;
import com.shortage.game.Shortage;

public class PantallaLogin extends PantallaAbstracta {
	
	private static SpriteBatch batch;
	private Texture fondo;
	private Etiqueta Titulo, Usuario, Clave;
	private TextField tfusuario, tfclave;
	private MyTextInputListener listener;
	private Skin skin;

	public PantallaLogin(Shortage game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Titulo.draw(batch);
		tfusuario.draw(batch, 1);
		tfclave.draw(batch, 1);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch= Shortage.getBatch();
		fondo = new Texture(Gdx.files.internal("escasez.jpg"));
		Titulo= new EtiquetaTitulo(Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()-150);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		tfusuario= new TextField("",skin );
		tfusuario.setSize(300, 50);
		tfusuario.setPosition(400, 400);
		tfusuario.setMessageText("Usuario");
		
	
		tfclave= new TextField("",skin);
		tfclave.setSize(300,50);
		tfclave.setMessageText("Clave");
		tfclave.setPosition(400, 200);
		tfclave.setPasswordMode(true);

		
		
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
	
	

}
