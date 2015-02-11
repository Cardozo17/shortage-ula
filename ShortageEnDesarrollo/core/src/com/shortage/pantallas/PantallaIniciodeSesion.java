package com.shortage.pantallas;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.shortage.basededatos.UsuarioDAO;
import com.shortage.basededatos.UsuarioVO;
import com.shortage.entidades.Etiqueta;
import com.shortage.entidades.EtiquetaTitulo;
import com.shortage.game.Shortage;

public class PantallaIniciodeSesion  extends PantallaAbstracta{
	private Stage stage;
	private Skin skin;
	private TextButton aceptar, cancelar, registrar;
	private TextField nombre, clave;
	private BitmapFont texto;
	private SpriteBatch batch;
	private Texture fondo;
	private Etiqueta Titulo;
	
	public PantallaIniciodeSesion(Shortage game) {
		super(game);
		// TODO Auto-generated constructor stub
		stage = new Stage();
		skin=new Skin(Gdx.files.internal("uiskin.json"));
		fondo= new Texture(Gdx.files.internal("loginTexture.png"));
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
		clave.setPasswordMode(true);
		clave.setPasswordCharacter('*');
		stage.addActor(clave);
		
		batch= new SpriteBatch();
		texto=new BitmapFont();
		texto.setScale(1.5f);
		texto.setColor(Color.BLACK);
		
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Gdx.input.setInputProcessor(stage);
      

		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Titulo.draw(batch);
		texto.draw(batch, "Inicio de Sesion:",275, 425);
		texto.draw(batch, "Usuario:",150, 325);
		texto.draw(batch, "Contraseña:", 150, 275);
		batch.end();
		
		stage.act(delta);
		stage.draw();
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
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

	public void botonAceptarclicked(){
		 
		UsuarioVO usuario = new UsuarioVO();
		usuario.setNombre(nombre.getText());
		usuario.setClave(clave.getText());

		String login = usuario.getNombre();
		String clave = usuario.getClave();


		boolean validacion = false;
		UsuarioDAO usuariosbd = new UsuarioDAO();
		ArrayList<UsuarioVO> listadeusuarios = usuariosbd
				.listaDeUsuarios();

		for (UsuarioVO usuarioaux : listadeusuarios) {


			String loginaux = usuarioaux.getNombre();
			String claveaux = usuarioaux.getClave();

			if ((clave.contentEquals(claveaux) && (login
					.contentEquals(loginaux)))) {
				validacion = true;
				Pantallas.juego.setScreen(Pantallas.MENUPRINCIPAL);
			}
		}
		if (validacion != true){
			JOptionPane.showMessageDialog(null,
					"Error de Usuario O Clave", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			
			aceptar.addListener(new ClickListener() {
				public void touchUp(InputEvent e, float x, float y, int point, int button){
					botonAceptarclicked();
					
				}
			});
				
			
			}
	}


	
	public void botonCancelarclicked(){
		Gdx.app.exit();
	}
	
	
	public void botonRegistrarclicked(){
		Pantallas.juego.setScreen(Pantallas.PANTALLAREGISTRODEUSUARIO);
		
		registrar.addListener(new ClickListener(){
			public void touchUp(InputEvent e, float x, float y, int point, int button){
				botonRegistrarclicked();
			}
		});
		
	} 
	
	
	
}
