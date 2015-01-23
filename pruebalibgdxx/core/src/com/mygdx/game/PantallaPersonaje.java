package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class PantallaPersonaje extends Pantalla {
	
	public Texture muneco;
	public Sprite personaje;
	
	/**
	 * Un Sprite sirve para guardar lo mismo que guardaríamos en un TextureRegion
	 * pero también para guardar más datos de la imagen, como su posición, su
	 * tamaño... así no tenemos que indicarlo cada vez que queramos dibujarla :D
	 */
	
	
	public PantallaPersonaje(MyGdxGame game) {
		super(game);
	}
	
	@Override
	
	public void show() {
		// Para cargar una textura indicamos su ubicación en la carpeta assets/
		muneco = new Texture("golf.png");
		// Con un Sprite podemos hacer más cosas que con un TextureRegion...
		personaje = new Sprite(muneco, 0, 0, 64, 64);
		personaje.setPosition(50, 50); // le damos una posición en la pantalla
	}
	
	
	
	
	
	
	@Override
	public void render(float delta) {
		
		renderizarJuego();
		entradaTeclado();
	}
	
	/**
	 * Este método es llamado cuando el juego se cierre y con este método podemos
	 * liberar recursos que no estemos utilizando. Debemos liberar manualmente
	 * todo aquello que no pueda liberar el recolector de basura de Java,
	 * por ejemplo, las texturas.
	 */
	@Override
	public void dispose() {
		// Liberar una textura es tan fácil como llamar a dispose().
		// ¡OJO! Cuando liberamos una textura los datos desaparecen de la memoria
		// así que no deberíamos intentar dibujarla otra vez. Las consecuencias
		// pueden ser impredecibles.
		muneco.dispose();
	}
	
	/** Se encarga de renderizar el juego como vimos en el episodio 3. */

	private void renderizarJuego() {
		// Con glClearColor y glClear podemos limpiar la pantalla y cambiarla por
		// un fondo del color que le digamos. El color se lo decimos con los tres
		// primeros parámetros, que son valores flotantes de tipo RGB entre 0 y 1
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Empezamos a mostrar cosas usando nuestro batch.
		game.batch.begin();
		
		personaje.draw(game.batch);
		game.batch.end();
	}
	
	/** Se encarga de procesar la entrada como vimos em el episodio 4. */

	
	
	/** Procesa entrada por teclado. */

	private void entradaTeclado() {
		  boolean arriba =Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP);
		  boolean izquierda= Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT);
		  boolean derecha=Gdx.input.isKeyPressed(Input.Keys.D)|| Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) ;
		  boolean abajo=Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN);
		float x= personaje.getX();
		float y = personaje.getY();
		  
		  if(arriba && !abajo)
			  y=y+4;
		  else if(abajo && !arriba)
			  y=y-4;
		  if(izquierda && !derecha)
			  x=x-4;
		  else if(derecha && !izquierda)
			  x=x+4;
			  
		personaje.setPosition(x, y);
	}
	

	
}