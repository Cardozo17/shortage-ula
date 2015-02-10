package com.shortage.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hud {

	//Para el Cronometro
	float counter, timeCounter;
	int min, sec;

	private int objetosRestantes;
	private BitmapFont objetosRestantesText;
	private SpriteBatch batch;
	

	public Hud() {
		
		objetosRestantes = 3;
		objetosRestantesText = new BitmapFont();
		objetosRestantesText.setScale(1.5f);
		objetosRestantesText.setColor(Color.BLUE);
		batch = new SpriteBatch();
	}

	public void render(float delta) {
		
		//Cronometro
		timeCounter += Gdx.graphics.getDeltaTime();
		if(timeCounter >= 1.0f){
		timeCounter = 0;
		counter++;
		}
		min= (int) (counter/60);
		sec= (int)(counter%60);	
		
		//Si quisieramos que se descontara el tiempo int totaltime= 6 *60 ej 6 min
		// totaltime -= timecounter;
		
		
		batch.begin();
		objetosRestantesText.draw(
				batch,
				"Productos por Recoger:   "
						+ Integer.toString(objetosRestantes), 50, 50);
		objetosRestantesText.draw(batch,
				"Tiempo :   " +min +":"+ sec, 350, 50);
		batch.end();
	}



	public int getObjetosRestantes() {
		return objetosRestantes;
	}

	public void setObjetosRestantes(int objetosRestantes) {
		this.objetosRestantes = objetosRestantes;
	}

	public BitmapFont getObjetosRestantesText() {
		return objetosRestantesText;
	}

	public void setObjetosRestantesText(BitmapFont objetosRestantesText) {
		this.objetosRestantesText = objetosRestantesText;
	}

	public void dispose() {

		objetosRestantesText.dispose();
	}

}
