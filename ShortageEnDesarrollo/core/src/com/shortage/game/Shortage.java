package com.shortage.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shortage.pantallas.PantallaPlay;

public class Shortage extends Game {
	
	@Override
	public void create () {
		PantallaPlay scenaactual;
		scenaactual=new PantallaPlay(this);
		setScreen(scenaactual);
	}

}


