package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BotonSalir extends Boton{

	
	
	
	public BotonSalir(int x, int y) {
		super(x, y);
		textura = new Texture(Gdx.files.internal("botonsalir.png"));
		
	}

	@Override
	public void funcionamiento() {
		// TODO Auto-generated method stub
		Gdx.app.exit();
	}
}
