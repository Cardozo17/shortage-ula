package com.Shorage.scenes;

import com.Shortage.game.ShapeRenderer;
import com.Shortage.game.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class ActorFondo extends Actor {


    Texture img;
    TiledMap tiledMap;


	OrthographicCamera camara;
    TiledMapRenderer tiledMapRenderer;
    
	public ActorFondo() {
		  tiledMap = new TmxMapLoader().load("test.tmx");
	      tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	    
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		tiledMapRenderer.setView(camara);
        tiledMapRenderer.render();	
	}
	
	
    public OrthographicCamera getCamara() {
		return camara;
	}

	public void setCamara(OrthographicCamera camara) {
		this.camara = camara;
	}	
}


