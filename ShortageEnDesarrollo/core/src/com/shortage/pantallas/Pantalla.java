package com.shortage.pantallas;

import com.badlogic.gdx.Screen;
import com.shortage.game.Shortage;


/**
 * Pantalla abstracta. En la mayoría de los casos nos interesa construir una
 * pantalla abstracta no implementable pero que disponga de características
 * comunes que queremos que las distintas pantallas de nuestro juego hereden
 * (OOP, tíos).
 * 
 * @author danirod
 */
public abstract class Pantalla implements Screen {
	
	/**
	 * Juego al que pertenece la pantalla. Es muy interesante conectar una
	 * pantalla con el juego sea de la forma que sea ya que de este modo
	 * el juego puede acceder a cosas como el contexto (el SpriteBatch que
	 * he dejado ahí) o simplemente llamar a setScreen.
	 */
	protected Shortage game;
	
	
	public Pantalla(Shortage game) {
		this.game = game;
	}
	
	// Para no tener que implementar tantos métodos por nuestra cuenta,
	// dejo los métodos vacíos. Si una pantalla quiere usar un método,
	// lo extiende. Pero si no quiere usarlo, no tiene que extenderlo.

	@Override
	public abstract void render(float delta);

	@Override
	public abstract void resize(int width, int height) ;
	@Override
	public abstract void show();

	@Override
	public abstract void hide() ;

	@Override
	public abstract void pause();

	@Override
	public abstract void resume();
	@Override
	public abstract void dispose();
	

}
