package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Personaje  {
	
	Texture skin;
	SpriteBatch batch;
	int vida;
	int vidamax;
	float posX, posY, alto, ancho;
	Sprite sprite;
	
	Body cuerpo;
	BodyDef bodydef;
	FixtureDef fixturedef;
	Fixture fixture;
	
	
	public TextureRegion skinRegion;
	public Animation skinAnimationDown,skinAnimationRight,skinAnimationUp,skinAnimationLeft; 
	public TextureRegion[] skinFramesDown, skinFramesRight, skinFramesUp, skinFramesLeft;
	public float duracion;
	
	
	public enum ESTADO_ACTUAL{Atacando, Moviendose, Quieto}
	public ESTADO_ACTUAL estado;
	public enum VISTA_ACTUAL{arriba, abajo, izquierda, derecha}
	public VISTA_ACTUAL vista;
	
    public Personaje(){
    skin = new Texture(Gdx.files.internal("personajee.png"));
    batch = new SpriteBatch();
    sprite = new Sprite(skin);
    alto=skin.getHeight();
    ancho=skin.getWidth();
    posX=(Gdx.graphics.getWidth()/2)-ancho/2;
    posY=(Gdx.graphics.getHeight()/2)-alto/2;
    
    //Estado inicial
    estado=ESTADO_ACTUAL.Quieto; 
    vista=VISTA_ACTUAL.abajo;
    
    //Preparando Animaciones:
    skinRegion = new TextureRegion(skin, 124, 144);
	TextureRegion[][] temp = skinRegion.split(124 / 3, 144/4); 
	skinFramesDown = new TextureRegion[3];
	skinFramesRight = new TextureRegion[3];
	skinFramesUp = new TextureRegion[3];
	skinFramesLeft = new TextureRegion[3];
		
		for (int j = 0; j < 3; j++) {
			skinFramesDown[j] = temp[0][j];
		}
		for (int j = 0; j < 3; j++) {
			skinFramesRight[j] = temp[1][j];
		}
		for (int j = 0; j < 3; j++) {
			skinFramesUp[j] = temp[2][j];
		}
		for (int j = 0; j < 3; j++) {
			skinFramesLeft[j] = temp[3][j];
		}
	
	skinAnimationDown = new Animation(0.1f, skinFramesDown);
	skinAnimationRight = new Animation(0.1f, skinFramesRight);
	skinAnimationUp = new Animation(0.1f, skinFramesUp);
	skinAnimationLeft = new Animation(0.1f, skinFramesLeft);
    
	// Colisiones
	bodydef = new BodyDef();
	bodydef.type=BodyType.DynamicBody;
	bodydef.position.set(posX, posY);
	
    }
	
    public float getAlto() {
		return alto;
	}

	public void setAlto(float alto) {
		this.alto = alto;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public void render(float delta){
    	
		duracion += delta;
		TextureRegion frameDown = skinAnimationDown.getKeyFrame(duracion, true);
		TextureRegion frameRight = skinAnimationRight.getKeyFrame(duracion, true);
		TextureRegion frameUp = skinAnimationUp.getKeyFrame(duracion, true);
		TextureRegion frameLeft = skinAnimationLeft.getKeyFrame(duracion, true);
		
		posX=cuerpo.getPosition().x;
		posY=cuerpo.getPosition().y;

		batch.begin();
		if(estado==ESTADO_ACTUAL.Moviendose){
			if( vista == VISTA_ACTUAL.abajo ){
				
				batch.draw(frameDown, posX, posY);
			}
			else if (vista == VISTA_ACTUAL.derecha)
				batch.draw(frameRight, posX, posY);
				else if( vista == VISTA_ACTUAL.arriba)
					batch.draw(frameUp, posX, posY);
					else if( vista == VISTA_ACTUAL.izquierda)
		
						batch.draw(frameLeft, posX, posY);
		}
		else if(estado==ESTADO_ACTUAL.Quieto)
		{
			if( vista == VISTA_ACTUAL.abajo ){
				batch.draw(skinFramesDown[1], posX, posY);
			}
			else if (vista == VISTA_ACTUAL.derecha)
				batch.draw(skinFramesRight[1], posX, posY);
				else if( vista == VISTA_ACTUAL.arriba)
					batch.draw(skinFramesUp[1], posX, posY);
					else if( vista == VISTA_ACTUAL.izquierda)
		
						batch.draw(skinFramesLeft[1], posX, posY);
		}
		batch.end();
       
    	//cuerpo.getPosition().set(posX, posY);
    }

	public void quieto(){
		//cuerpo.setLinearVelocity(0f,0f);
	}
	
	
    public void moverDerecha(float delta, float velocidad){
    	cuerpo.setLinearVelocity(velocidad*delta,0f);
    	//	posX=posX+velocidad*delta;
    }
    
    public void moverIzquierda(float delta, float velocidad){
      	//posX=posX-velocidad*delta;
    	cuerpo.setLinearVelocity(-velocidad*delta,0f);
    }
    
    public void moverArriba(float delta, float velocidad){
      	posY=posY+velocidad*delta;
    }
    
    public void moverAbajo(float delta, float velocidad){
      	posY=posY-velocidad*delta;
    }
    
    public void actualizarPosicion(float x, float y){
    	posX=x;
    	posY=y;	
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
	
	//Modificando Vistas
	public void mirarDerecha(){
		vista = VISTA_ACTUAL.derecha;
	}
	
	public void mirarIzquierda(){
		vista = VISTA_ACTUAL.izquierda;
		
	}
	
	public void mirarArriba(){
		vista = VISTA_ACTUAL.arriba;
	}
	
	public void mirarAbajo(){
		vista = VISTA_ACTUAL.abajo;
	}
	
	//Modificando Estados
	public void estadoQuieto(){
		estado=ESTADO_ACTUAL.Quieto;
	}
	public void estadoMoviendose(){
		estado=ESTADO_ACTUAL.Moviendose;
	}
	public void estadoAtacando(){
		estado=ESTADO_ACTUAL.Atacando;
	}

	public Body crearCuerpo(World world){
		cuerpo=world.createBody(bodydef);
	
		PolygonShape shape = new PolygonShape();

        shape.setAsBox(124 / 4, 144/5);
        
        fixturedef = new FixtureDef();
        fixturedef.shape = shape;
        fixturedef.density = 1f;
        fixturedef.isSensor=false;
        
        fixture = cuerpo.createFixture(fixturedef);
       
        shape.dispose();
		
		return cuerpo;
	}
	
}





