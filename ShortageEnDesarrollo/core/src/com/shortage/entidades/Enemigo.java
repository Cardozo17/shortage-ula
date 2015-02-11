package com.shortage.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.shortage.entidades.Personaje.ESTADO_ACTUAL;
import com.shortage.entidades.Personaje.VISTA_ACTUAL;

public class Enemigo {
	

	Texture skin;
	
	int vida;
	int vidamax;
	float posX, posY, alto, ancho;
	Sprite sprite;
	Body cuerpo;
	BodyDef bodydef;
	FixtureDef fixturedef;
	Fixture fixture;
	final float PPM=1;
	int movimiento;
	float inicioX, finalX, inicioY,  finalY;
	
	
	
	public TextureRegion skinRegion;
	public Animation skinAnimationDown,skinAnimationRight,skinAnimationUp,skinAnimationLeft; 
	public TextureRegion[] skinFramesDown, skinFramesRight, skinFramesUp, skinFramesLeft;
	public float duracion;
	
	
	public enum ESTADO_ACTUAL{Atacando, Moviendose, Quieto}
	public ESTADO_ACTUAL estado;
	public enum VISTA_ACTUAL{arriba, abajo, izquierda, derecha}
	public VISTA_ACTUAL vista;
	
    public Enemigo(float x,float y){
    skin = new Texture(Gdx.files.internal("enemigoTexture.png"));
    sprite = new Sprite(skin);
    alto=skin.getHeight();
    ancho=skin.getWidth();
    posX=x;
    posY=y;// puntos escojidos por nosotros
    inicioX=0;finalX=0;inicioY=0;finalY=0;//valor por defecto de patrulla de personaje
    movimiento=1;
    
    
    
    
    
    //Estado inicial
    estado=ESTADO_ACTUAL.Quieto; 
    vista=VISTA_ACTUAL.abajo;
    
    //Preparando Animaciones:
    skinRegion = new TextureRegion(skin, 120, 160);
	TextureRegion[][] temp = skinRegion.split(120 / 3, 160/4); 
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

	public void render(float delta, SpriteBatch batch){
    	
		patrulladeInicio(batch,delta);
	

    }

	public void quieto(){
		cuerpo.setLinearVelocity(0f,0f);
	}
	
	
    public void moverDerecha(float delta, double velocidad){
    	cuerpo.setLinearVelocity((float) (velocidad*delta),0f);
    	//	posX=posX+velocidad*delta;
    }
    
    public void moverIzquierda(float delta, double velocidad){
      	//posX=posX-velocidad*delta;
    	cuerpo.setLinearVelocity((float) (-velocidad*delta),0f);
    }
    
    public void moverArriba(float delta, double velocidad){
      	//posY=posY+velocidad*delta;
    	cuerpo.setLinearVelocity(0f,(float) (velocidad*delta));
    }
    
    public void moverAbajo(float delta, double velocidad){
      	//posY=posY-velocidad*delta;
    	cuerpo.setLinearVelocity(0f,(float) (-velocidad*delta));
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
	public Body getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(Body cuerpo) {
		this.cuerpo = cuerpo;
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

        shape.setAsBox(124 / 8/PPM, 144/9/PPM);// Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8
        
        fixturedef = new FixtureDef();
        fixturedef.shape = shape;
        fixturedef.density = 1f;
        fixturedef.friction=1f;
        fixturedef.isSensor=false;
        
        fixture = cuerpo.createFixture(fixturedef);
        fixture.setUserData("Enemigo");
        shape.dispose();
        cuerpo.setFixedRotation(true);
				return cuerpo;
	}
	

	public void patrulladeInicio(SpriteBatch batch, float delta){

		duracion += delta;
		TextureRegion frameDown = skinAnimationDown.getKeyFrame(duracion, true);
		TextureRegion frameRight = skinAnimationRight.getKeyFrame(duracion, true);
		TextureRegion frameUp = skinAnimationUp.getKeyFrame(duracion, true);
		TextureRegion frameLeft = skinAnimationLeft.getKeyFrame(duracion, true);
		
		
		
		batch.begin();
		if(inicioX==0 && inicioY==0 && finalX==0 && finalY==0){

			batch.draw(skinFramesDown[1],cuerpo.getPosition().x-ancho/6, cuerpo.getPosition().y-alto/9);
			cuerpo.setLinearVelocity(0, 0);
		}
		
		if(inicioX==finalX){
			
			
		if(inicioY<finalY){	
				if(cuerpo.getPosition().y < inicioY)
				{
					movimiento=1;
					mirarArriba();
					
				}
				else if(cuerpo.getPosition().y > finalY)
				{
					movimiento=-1;
					mirarAbajo();
				}
		}
		else
		{
			if(cuerpo.getPosition().y > inicioY)
			{
				movimiento=-1;
				mirarAbajo();
				
			}
			else if(cuerpo.getPosition().y < finalY)
			{
				movimiento=+1;
				mirarArriba();
				
			}
			
		}
				cuerpo.setLinearVelocity(0, finalY*movimiento);
				if(vista== VISTA_ACTUAL.arriba) 
					batch.draw(frameUp,cuerpo.getPosition().x-ancho/6, cuerpo.getPosition().y-alto/9);
				else
					batch.draw(frameDown,cuerpo.getPosition().x-ancho/6, cuerpo.getPosition().y-alto/9);
			
			
		}	
		
		
		else if(inicioY==finalY){
			if(inicioX<finalX){	
				if(cuerpo.getPosition().x < inicioX)
				{
					movimiento=1;
					mirarDerecha();
					
				}
				else if(cuerpo.getPosition().x > finalX)
				{
					movimiento=-1;
					mirarIzquierda();
				}
		}
			
			else
			{
				if(cuerpo.getPosition().x > inicioX)
				{
					movimiento=-1;
					mirarIzquierda();;
					
				}
				else if(cuerpo.getPosition().x < finalX)
				{
					movimiento=+1;
					mirarDerecha();
					
				}
				
			}
			cuerpo.setLinearVelocity(finalX*movimiento,0);
			if(vista== VISTA_ACTUAL.derecha) 
				batch.draw(frameRight,cuerpo.getPosition().x-ancho/6, cuerpo.getPosition().y-alto/9);
			else
				batch.draw(frameLeft,cuerpo.getPosition().x-ancho/6, cuerpo.getPosition().y-alto/9);
		}	
		
		batch.end();
		

			
		
		
		
		
//		batch.begin();
//		if(estado==ESTADO_ACTUAL.Moviendose){
//			if( vista == VISTA_ACTUAL.abajo ){
//				
//				batch.draw(frameDown,Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//			}
//			else if (vista == VISTA_ACTUAL.derecha)
//				batch.draw(frameRight,Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//				else if( vista == VISTA_ACTUAL.arriba)
//					batch.draw(frameUp, Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//					else if( vista == VISTA_ACTUAL.izquierda)
//		
//						batch.draw(frameLeft,Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//		}
//		else if(estado==ESTADO_ACTUAL.Quieto)
//		{
//			if( vista == VISTA_ACTUAL.abajo ){
//				batch.draw(skinFramesDown[1],Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//			}
//			else if (vista == VISTA_ACTUAL.derecha)
//				batch.draw(skinFramesRight[1],Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//				else if( vista == VISTA_ACTUAL.arriba)
//					batch.draw(skinFramesUp[1], Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//					else if( vista == VISTA_ACTUAL.izquierda)
//		
//						batch.draw(skinFramesLeft[1], Gdx.graphics.getWidth()/2 - 124 / 6, Gdx.graphics.getHeight()/2 - 144/8);
//		}
//		batch.end();
//		
//		
//		
}
	
	public void setRuta(float xi, float yi,float xf, float yf){
		inicioX=xi;
		inicioY=yi;
		finalX=xf;
		finalY=yf;
	}
}
