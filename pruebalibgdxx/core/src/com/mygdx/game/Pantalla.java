package com.mygdx.game;

import com.badlogic.gdx.Screen;


public abstract class Pantalla implements Screen {
	
	/**
	 * Juego al que pertenece la pantalla. Es muy interesante conectar una
	 * pantalla con el juego sea de la forma que sea ya que de este modo
	 * el juego puede acceder a cosas como el contexto (el SpriteBatch que
	 * he dejado ahí) o simplemente llamar a setScreen.
	 */
	protected MyGdxGame game;
	
	public Pantalla(MyGdxGame game) {
		this.game = game;
	}
	
	// Para no tener que implementar tantos métodos por nuestra cuenta,
	// dejo los métodos vacíos. Si una pantalla quiere usar un método,
	// lo extiende. Pero si no quiere usarlo, no tiene que extenderlo.

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

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

	}

}