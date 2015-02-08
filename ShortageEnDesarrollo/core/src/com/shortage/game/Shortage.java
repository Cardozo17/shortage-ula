package com.shortage.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shortage.pantallas.MenuPrincipal;
import com.shortage.pantallas.PantallaPlay;
import com.shortage.pantallas.Pantallas;

public class Shortage extends Game {
	
	private static SpriteBatch batch;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		
		Pantallas.juego = this;
		Pantallas.PANTALLAJUEGO= new PantallaPlay(this);
		Pantallas.MENUPRINCIPAL= new MenuPrincipal(this); 
		
		
		setScreen(Pantallas.MENUPRINCIPAL);
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Pantallas.PANTALLAJUEGO.dispose();
	}
	
	
	public static SpriteBatch getBatch(){
		
		return batch;
	}

}


