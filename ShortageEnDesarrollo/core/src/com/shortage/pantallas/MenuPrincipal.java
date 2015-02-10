package com.shortage.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shortage.entidades.Boton;
import com.shortage.entidades.BotonJugar;
import com.shortage.entidades.BotonSalir;
import com.shortage.entidades.Etiqueta;
import com.shortage.entidades.EtiquetaTitulo;
import com.shortage.game.Shortage;

	public class MenuPrincipal  extends PantallaAbstracta {
		
		private static SpriteBatch batch;
		private Stage escenario;
		private Skin skin;
		private Table tabla;
		private Texture fondo;
		private Image fondoactor;
		private Boton jugar, salir;
		private TextButton botjugar, botsalir;
		private Label titulo;
		private Etiqueta Titulo;

	
		

		public MenuPrincipal(Shortage shortage) {
			// TODO Auto-generated constructor stub
			super(shortage);
			
			
			escenario = new Stage();
			skin = new Skin(Gdx.files.internal("uiskin.json"));
			
			tabla = new Table(skin);
			tabla.setFillParent(true);
			tabla.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			tabla.setColor(1, 0, 1, 0);
			tabla.add(botjugar).space( 10f ).fill().expand();
			tabla.row();
			tabla.add(botsalir).space( 10f ).fill().expand();
			tabla.row();
			
			titulo = new Label("Shortage", skin);
			titulo.setWidth(200f);
			titulo.setHeight(200f);
			titulo.setFontScale(5f, 5f);
			titulo.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/3);
			tabla.add(titulo).padBottom(400f); //also use padTop, padLeft, and padRight
			
			botjugar= new TextButton("Jugar", skin);
			botsalir= new TextButton("Salir", skin);
	
			
			fondo = new Texture(Gdx.files.internal("escasez.jpg"));
			fondoactor = new Image(fondo);
			fondoactor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			
			escenario.addActor(fondoactor);
			escenario.addActor(titulo);
			escenario.addActor(tabla);
			
			
		}

		@Override
		public void render(float delta) {
			// TODO Auto-generated method stub
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			salir.update();
			jugar.update();
			
			escenario.act(delta);
			
			batch.begin();
				batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				//escenario.draw();
				Titulo.draw(batch);
				salir.draw(batch);
				jugar.draw(batch);
			batch.end();
			
		}


		@Override
		public void resize(int width, int height) {
			// TODO Auto-generated method stub
			
			tabla.setBounds(width*0.05f, 30, width, height);
			tabla.setSize(width*0.2f,height*0.50f);
			tabla.invalidateHierarchy();
			
		}

		@Override
		public void show() {
			// TODO Auto-generated method stub
			batch= Shortage.getBatch();
			Texture textura= new Texture(Gdx.files.internal("botonsalir.png"));
			int CentroX= Gdx.graphics.getWidth()/2- textura.getWidth()/2;
			int CentroY= Gdx.graphics.getHeight()/2- textura.getHeight()/2;
			jugar = new BotonJugar(CentroX, CentroY-50);
			salir = new BotonSalir(CentroX, CentroY-150);
			Titulo = new EtiquetaTitulo(Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()-150);
			
		
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
	

