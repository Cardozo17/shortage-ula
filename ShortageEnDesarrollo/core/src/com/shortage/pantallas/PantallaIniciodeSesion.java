package com.shortage.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.shortage.game.Shortage;

public class PantallaIniciodeSesion  extends PantallaAbstracta{
	private Stage stage;
	private Skin skin;
	private TextButton aceptar, cancelar, registrar;
	private TextField nombre, clave;
	private BitmapFont texto;
	private SpriteBatch batch;
	
	public PantallaIniciodeSesion(Shortage game) {
		super(game);
		// TODO Auto-generated constructor stub
		stage = new Stage();
		skin=new Skin(Gdx.files.internal("uiskin.json"));
		//Gdx.input.setInputProcessor(stage);
		aceptar=new TextButton("Aceptar", skin);
		aceptar.setPosition(220, 125);
		aceptar.setSize(150, 40);
		stage.addActor(aceptar);
		
		aceptar.addListener(new ClickListener() {
			public void touchUp(InputEvent e, float x, float y, int point, int button){
				botonAceptarclicked();
				
			}
		});
		
		cancelar=new TextButton("Cancelar", skin);
		cancelar.setPosition(420,125);
		cancelar.setSize(150, 40);
		stage.addActor(cancelar);
		cancelar.addListener(new ClickListener() {
			public void touchUp(InputEvent e, float x, float y, int point, int button){
				botonCancelarclicked();
			}
		});

		registrar=new TextButton("Registrar", skin);
		registrar.setPosition(620,45);
		registrar.setSize(150, 40);
		stage.addActor(registrar);
		registrar.addListener(new ClickListener(){
			public void touchUp(InputEvent e, float x, float y, int point, int button){
				botonRegistrarclicked();
			}
		});
		
		nombre= new TextField("", skin);
		nombre.setPosition(300, 300);
		nombre.setSize(250, 40);
		stage.addActor(nombre);
		
		clave= new TextField("", skin);
		clave.setPosition(300, 250);
		clave.setSize(250, 40);
		stage.addActor(clave);
		
		batch= new SpriteBatch();
		texto=new BitmapFont();
		texto.setScale(1.5f);
		texto.setColor(Color.WHITE);
		
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
      Gdx.input.setInputProcessor(stage);
        
		stage.act(delta);
		stage.draw();
		batch.begin();
		texto.draw(batch, "Inicio de Sesion:",275, 425);
		texto.draw(batch, "Usuario:",150, 325);
		texto.draw(batch, "Contraseña:", 150, 275);
		batch.end();
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		
		batch.dispose();
	}

	public void botonAceptarclicked(){
		
		Pantallas.juego.setScreen(Pantallas.MENUPRINCIPAL);
	}
	public void botonCancelarclicked(){
		Gdx.app.exit();
	}
	public void botonRegistrarclicked(){
		Pantallas.juego.setScreen(Pantallas.PANTALLAREGISTRODEUSUARIO);
	} 
	
	
	
}
