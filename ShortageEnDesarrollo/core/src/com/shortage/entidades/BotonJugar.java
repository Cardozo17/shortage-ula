package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.shortage.pantallas.Pantallas;

public class BotonJugar extends Boton {

	public BotonJugar(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		textura = new Texture(Gdx.files.internal("botonjugar.png"));
	}
	
	public void funcionamiento()
	{
		Pantallas.juego.setScreen(Pantallas.PANTALLAJUEGO);
	}

}
