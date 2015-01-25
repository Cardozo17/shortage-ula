package com.Shortage.game;

import com.Shorage.scenes.PantallaScene;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.TimeScaleAction;

public class Main extends Game{
	public SpriteBatch batch;
	PantallaScene scene1;
	float delta;
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		scene1= new PantallaScene(this);
		
		setScreen(scene1);
	}

}
