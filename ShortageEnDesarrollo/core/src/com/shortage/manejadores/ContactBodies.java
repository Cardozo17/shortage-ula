package com.shortage.manejadores;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class ContactBodies implements ContactListener{


	private Body body1, body2;
	private boolean estado;
	private Array<Body> bodiesDelete;
	private boolean muerte;

	
	public ContactBodies(){
		super();
		bodiesDelete= new Array<Body>();
		muerte=false;
	}
	
	public boolean getestado(){return estado;}
	
	public void beginContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if((contact.getFixtureA().getBody()==getbody1() && contact.getFixtureB().getBody()==getbody2()) ||
			(contact.getFixtureA().getBody()==getbody2() && contact.getFixtureB().getBody()==getbody1())){
			
			estado=true;
			
		}	
		
		if(fa == null || fb == null) return;
		
	
		
		if(fa.getUserData() != null && fa.getUserData().equals("Coleccionable")){
			
			bodiesDelete.add(fa.getBody());
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("Coleccionable")) {
			
			bodiesDelete.add(fb.getBody());
			
		}
		
		
		if(fa.getUserData() != null && fa.getUserData().equals("Enemigo")){
			
			muerte=true;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("Enemigo")) {
			
			muerte=true;
		}
		
		
	}


	public void endContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if((contact.getFixtureA().getBody()==getbody1() && contact.getFixtureB().getBody()==getbody2()) ||
				(contact.getFixtureA().getBody()==getbody2() && contact.getFixtureB().getBody()==getbody1())){
				
				estado=false;
				//System.out.println("coliion");
			}
		
		
		if(fa == null || fb == null) return;
		
	}
	
	public void setbody(Body b1, Body b2){
		body1=b1;
		body2=b2;
	}
	
	public Body getbody1(){
		return body1;
	}
	
	public Body getbody2(){
		return body2;
	}
	
	public Array<Body> getBodiesDelete(){
		return bodiesDelete;
	}
	
	public void postSolve(Contact arg0,
			ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void preSolve(Contact arg0,
			Manifold arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getMuerte() {
		return muerte;
	}

	public void setMuerte(boolean muerte) {
		this.muerte = muerte;
	}

}
