package com.shortage.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hud {

	//Para el Cronometro
	float counter, timeCounter;
	int min, sec;

	private int objetosRestantes;
	private BitmapFont objetosRestantesText;
	private SpriteBatch batch;
	private Texture harina, leche, papel, harinatach, lechetach, papeltach;
	private boolean harinarecoj, lecherecoj, papelrecoj;
	
	

	public Hud() {
		
		objetosRestantes = 3;
		objetosRestantesText = new BitmapFont();
		objetosRestantesText.setScale(1.5f);
		objetosRestantesText.setColor(Color.WHITE);
		batch = new SpriteBatch();
		harina = new Texture(Gdx.files.internal("harinaTextura.png"));
		leche = new Texture(Gdx.files.internal("lecheTextura.png"));
		papel = new Texture(Gdx.files.internal("papelTextura.png"));
		harinatach= new Texture(Gdx.files.internal("harinaTexturaTachada.png"));
		lechetach = new Texture(Gdx.files.internal("lecheTexturaTachada.png"));
		papeltach= new Texture(Gdx.files.internal("papelTexturaTachada.png"));
		
		harinarecoj= false;
		lecherecoj=false;
		papelrecoj=false;
		
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
						+ Integer.toString(objetosRestantes), 50, 30);
		objetosRestantesText.draw(batch,
				"Tiempo :   " +min +":"+ sec, 650, 30);
		if(!isHarinarecoj())
		batch.draw(harina, 50,50);
		else
			batch.draw(harinatach, 50,50);
		
		if(!isLecherecoj())
		batch.draw(leche, 100,50);
		else
			batch.draw(lechetach, 100, 50);
		
		if(!isPapelrecoj())
		batch.draw(papel, 150,50);
		else
			batch.draw(papeltach, 150, 50);
		
		batch.end();
	}

	public boolean isHarinarecoj() {
		return harinarecoj;
	}

	public void setHarinarecoj(boolean harinarecoj) {
		this.harinarecoj = harinarecoj;
	}

	public boolean isLecherecoj() {
		return lecherecoj;
	}

	public void setLecherecoj(boolean lecherecoj) {
		this.lecherecoj = lecherecoj;
	}

	public boolean isPapelrecoj() {
		return papelrecoj;
	}

	public void setPapelrecoj(boolean papelrecoj) {
		this.papelrecoj = papelrecoj;
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
