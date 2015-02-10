package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class EtiquetaTitulo extends Etiqueta {

	public EtiquetaTitulo(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		textura = new Texture(Gdx.files.internal("Shortage.jpg"));
	}
	
	

}
