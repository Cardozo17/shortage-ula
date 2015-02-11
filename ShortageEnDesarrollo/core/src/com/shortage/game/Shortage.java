package com.shortage.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shortage.pantallas.MenuPrincipal;
import com.shortage.pantallas.PantallaFindeJuego;
import com.shortage.pantallas.PantallaIniciodeSesion;
import com.shortage.pantallas.PantallaPlay;
import com.shortage.pantallas.PantallaRegistrodeUsuario;
import com.shortage.pantallas.PantallaVictoria;
import com.shortage.pantallas.Pantallas;

public class Shortage extends Game {
	
	private static SpriteBatch batch;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		
		Pantallas.juego = this;
		Pantallas.PANTALLAJUEGO= new PantallaPlay(this);
		Pantallas.MENUPRINCIPAL= new MenuPrincipal(this); 
		Pantallas.FINDEJUEGO= new PantallaFindeJuego(this);
		Pantallas.PANTALLAINICIODESESION = new PantallaIniciodeSesion(this);
		Pantallas.PANTALLAREGISTRODEUSUARIO = new PantallaRegistrodeUsuario(this);
		Pantallas.PANTALLAVICTORIA= new PantallaVictoria(this);
			
	 	setScreen(Pantallas.PANTALLAINICIODESESION);
	 	//setScreen(Pantallas.PANTALLAJUEGO);	
		//setScreen(Pantallas.FINDEJUEGO);
		
	}
	
	
	@Override
	public void dispose() {
		
//		Pantallas.PANTALLAJUEGO.dispose();
//		Pantallas.MENUPRINCIPAL.dispose();
//		Pantallas.FINDEJUEGO.dispose();
		Pantallas.PANTALLAINICIODESESION.dispose();
//		Pantallas.PANTALLAREGISTRODEUSUARIO.dispose();
		batch.dispose();

	}
	
	
	public static SpriteBatch getBatch(){
		return batch;
	}

}


