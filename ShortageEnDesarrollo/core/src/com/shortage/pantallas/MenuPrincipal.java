package com.shortage.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shortage.entidades.Boton;
import com.shortage.entidades.BotonJugar;
import com.shortage.entidades.BotonSalir;
import com.shortage.game.Shortage;

	public class MenuPrincipal  extends PantallaAbstracta {
		
		private static SpriteBatch batch;
		//private Texture fondo;
		private Boton jugar, salir;

		public MenuPrincipal(Shortage shortage) {
			// TODO Auto-generated constructor stub
			super(shortage);
			
		//	fondo = new Texture(Gdx.files.internal(""));
		}

		@Override
		public void render(float delta) {
			// TODO Auto-generated method stub
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			salir.update();
			jugar.update();
			
			batch.begin();
			salir.draw(batch);
			jugar.draw(batch);
			
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
			Texture textura= new Texture(Gdx.files.internal("botonsalir.png"));
			int CentroY= Gdx.graphics.getHeight()/2- textura.getHeight()/2;
			int CentroX= Gdx.graphics.getWidth()/2- textura.getWidth()/2;
			salir = new BotonSalir(CentroX, CentroY-50);
			jugar = new BotonJugar(CentroX, CentroY+50);
			
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
		
		

		
		
	    
	}
	

