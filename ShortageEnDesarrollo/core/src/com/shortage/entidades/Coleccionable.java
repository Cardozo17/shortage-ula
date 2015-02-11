package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Coleccionable {
	Body cuerpo;
	BodyDef bodydef;
	FixtureDef fixturedef;
	Fixture fixture;
	Texture skin;
	SpriteBatch batch;
	float posX, posY;
	float ancho, alto;
	Sprite sprite; 
	
	
	
	public Coleccionable(float x, float y, String nombre) {
		super();
		skin=new Texture(Gdx.files.internal(nombre));
		sprite = new Sprite(skin);
	
		alto=skin.getHeight();
		ancho=skin.getWidth();
		batch = new SpriteBatch();
		posX=x;
		posY=y;
		// Colisiones
		bodydef = new BodyDef();
		bodydef.type=BodyType.DynamicBody;
		bodydef.position.set(posX, posY);
		
	}
	
	public Body crearCuerpo(World world){
		
		cuerpo=world.createBody(bodydef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(ancho/2, alto/2);
        
        fixturedef = new FixtureDef();
        fixturedef.shape = shape;
        fixturedef.density = 1f;
        fixturedef.friction=1f;
        fixturedef.isSensor=false;
       
        fixture = cuerpo.createFixture(fixturedef);
        fixture.setUserData("Coleccionable");
        shape.dispose();
        cuerpo.setFixedRotation(true);
        
		return cuerpo;
	}
	
	public void draw(Batch batch){
		batch.draw(skin, cuerpo.getPosition().x-ancho/2, cuerpo.getPosition().y-alto/2);
		
	}
	public void dispose(){
		skin.dispose();		
	}
	
	public Body getCuerpo() {
		return cuerpo;
	}
	
	public void setCuerpo(Body cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public BodyDef getBodydef() {
		return bodydef;
	}
	
	public void setBodydef(BodyDef bodydef) {
		this.bodydef = bodydef;
	}
	
	public FixtureDef getFixturedef() {
		return fixturedef;
	}
	
	public void setFixturedef(FixtureDef fixturedef) {
		this.fixturedef = fixturedef;
	}
	public Fixture getFixture() {
		return fixture;
	}
	
	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}
	
	public Texture getSkin() {
		return skin;
	}
	
	public void setSkin(Texture skin) {
		this.skin = skin;
	}
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public float getAncho() {
		return ancho;
	}
	
	public void setAncho(float ancho) {
		this.ancho = ancho;
	}
	
	public float getAlto() {
		return alto;
	}
	
	public void setAlto(float alto) {
		this.alto = alto;
	}
	
}
