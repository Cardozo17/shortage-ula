package com.Shorage.scenes;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Disposable;

public class ActorPersonaje extends Actor implements Disposable {
	
	private Texture pjSprite;
	private TextureRegion mipjSprite;
	public boolean started;
	float miX,miY;


	public ActorPersonaje() {
		pjSprite = new Texture("Cyclops.png");
		mipjSprite = new TextureRegion(pjSprite, 300, 300);
		//setSize(215, 83);
		//SetBounds(getX(),getY(),pjSprite.getWidth(),pjSprite.getHeight());
		
/*		addListener(new InputListener() {
			@Override
			*//** Called when a key goes down. When true is returned, the event is {@link Event#handle() handled}. *//*
			public boolean keyDown (InputEvent event, int keycode) {
					if(keycode== Keys.D){
						started = true;
						return true;
                   }
				return false;	
			}

			*//** Called when a key goes up. When true is returned, the event is {@link Event#handle() handled}. *//*
			public boolean keyUp (InputEvent event, int keycode) {
				 ((ActorPersonaje)event.getTarget()).started = false;
				return false;
			}
		});*/
		
	}
	
	@Override
	public void dispose() {
		pjSprite.dispose();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();
		batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
		batch.draw(mipjSprite,getX(), getY(),
				getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
	
	@Override
    public void act(float delta){
		super.act(delta);
    }
	
}
