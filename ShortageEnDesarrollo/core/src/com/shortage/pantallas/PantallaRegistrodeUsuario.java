package com.shortage.pantallas;

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

public class PantallaRegistrodeUsuario extends PantallaAbstracta {
	private Stage stage;
	private Skin skin;
	private TextButton crearCuenta, volver;
	private TextField nombre, clave;
	private BitmapFont texto;
	private SpriteBatch batch;
	private Texture fondo;
	private Etiqueta Titulo;
	
	
	public PantallaRegistrodeUsuario(Shortage game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		stage = new Stage();
		skin=new Skin(Gdx.files.internal("uiskin.json"));
		
		fondo = new Texture(Gdx.files.internal("registroTexture.png"));
		
		crearCuenta=new TextButton("Crear Cuenta", skin);
		crearCuenta.setPosition(220, 125);
		crearCuenta.setSize(150, 40);
		stage.addActor(crearCuenta);
		crearCuenta.addListener(new ClickListener(){
			public void touchUp(InputEvent e, float x, float y, int point, int button){
			botonCrearCuentaclicked();
		}
			
		});
		
		volver=new TextButton("Volver", skin);
		volver.setPosition(420,125);
		volver.setSize(150, 40);
		stage.addActor(volver);
		volver.addListener(new ClickListener(){
			public void touchUp(InputEvent e, float x, float y, int point, int button){
			botonVolverclicked();
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
		texto.setColor(Color.BLACK);
	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0,0,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Gdx.input.setInputProcessor(stage);
        
		
		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Titulo.draw(batch);
		texto.draw(batch, "Registro de Jugadores",275, 425);
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
	
	public void botonCrearCuentaclicked(){
		
		UsuarioVO usuario = new UsuarioVO();
		usuario.setNombre(nombre.getText());
		usuario.setClave(clave.getText());
		
		//String nombreaux= nombre.getText();
		
		UsuarioDAO usuariobd = new UsuarioDAO();
		
		if(!usuariobd.registrarUsuario(usuario))
		{	
			JOptionPane.showMessageDialog(null,
					"El Nombre de Usuario No Esta Disponible", "Información",JOptionPane.INFORMATION_MESSAGE);
			
			crearCuenta.addListener(new ClickListener(){
				public void touchUp(InputEvent e, float x, float y, int point, int button){
				botonCrearCuentaclicked();
			}	
			});
				
		}else{
			
			Pantallas.juego.setScreen(Pantallas.PANTALLAINICIODESESION);
		}						
			
		
		}
	
	public void botonVolverclicked(){
		Pantallas.juego.setScreen(Pantallas.PANTALLAINICIODESESION);
		
		volver.addListener(new ClickListener(){
			public void touchUp(InputEvent e, float x, float y, int point, int button){
			botonVolverclicked();
			}
		});
		
	}
	
	}	

