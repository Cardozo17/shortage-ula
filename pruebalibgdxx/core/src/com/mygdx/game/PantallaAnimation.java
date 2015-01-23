package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PantallaAnimation extends Pantalla {
	public Texture dude;
	public TextureRegion dudeRegion;
	public static final int ALTO=144;
	public static final int ANCHO=124;
	public Animation dudeAnimation; 
	public TextureRegion[] dudeFrames;
	public float duracion;
	
	
	public PantallaAnimation(MyGdxGame game) {
		super(game);
	}

	public void show() {
		// Si quisiese cargar mi dude usando Texture har�a �sto:
		 dude = new Texture("personaje.png");
		 dudeRegion = new TextureRegion(dude, ANCHO, ALTO);
		// Construyo la animaci�n.
		// (1) Divido la regi�n en un array bidimensional de regiones.
		// (2) Convierto el array de un array 2D [][] a un array 1D [].
		// (3) Construyo la animaci�n a partir de mi array 2D.
		TextureRegion[][] temp = dudeRegion.split(ANCHO / 3, ALTO/4); // (1)
		dudeFrames = new TextureRegion[temp.length * temp[0].length]; // (2)
		int indice = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				dudeFrames[indice++] = temp[i][j];
			}
		}
		dudeAnimation = new Animation(0.1f, dudeFrames); // (3)
	}
	
	
	/*
	/* * Atlas que guarda los recursos. 
	private TextureAtlas atlas;
	
	public TextureRegion dudeRegion;
	
	/** Fotogramas de la animaci�n. 
	private TextureRegion[] dudeFrames;
	
	/** Estructura de datos de la animaci�n.
	private Animation dudeAnimation;
	
	/** Contador de tiempo. Usado para la animaci�n. 
	private float duracion = 0;
	
	/** Ancho real del dude. 
	private static final int ANCHO = 252;
	
	/** Alto real del dude. 
	private static final int ALTO = 49;*/

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		
		// Obtengo el fotograma. Notar c�mo primero incremento la variable
		// duracion. Cuando obtengo mi fotograma, lo hago diciendole a 
		// Animation cu�ntos segundos han pasado desde que empez� la animaci�n.
		// �l hace sus c�lculos por su cuenta y devuelve el fotograma que
		// deber�a ir ahora.
		duracion += delta;
		TextureRegion frame = dudeAnimation.getKeyFrame(duracion, true);
		
		game.batch.begin();
		game.batch.draw(frame, 100, 100);
		game.batch.end();
	}
}