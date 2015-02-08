package com.shortage.pantallas;


import Manejadores.ContactBodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.shortage.entidades.Personaje;
import com.shortage.game.Shortage;

public class PantallaPlay extends PantallaAbstracta implements InputProcessor {
	
	static final float velocidad=300;
	private TiledMap mapa;
	OrthographicCamera camara;
	TiledMapRenderer renderizarMapa;
	Personaje heroe;
	SpriteBatch batch;
	World mundo;
	Body obstaculo;
	BodyDef bodydefObstaculo;
	final float PPM=100;
	float altomapa;
	float anchomapa;
	float tileSize;
	private ContactBodies cb;
	
	private Box2DDebugRenderer b2dr;
	
	
	
	public PantallaPlay(Shortage game) {
		super(game);
		mapa= new TmxMapLoader().load("mapa1.3.tmx");
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camara.update();
		renderizarMapa= new OrthogonalTiledMapRenderer(mapa);
		heroe= new Personaje();
		//Gdx.input.setInputProcessor(this);
		mundo=new World(new Vector2(0, 0), true);
		heroe.crearCuerpo(mundo);
        
       // createWalls();
		b2dr = new Box2DDebugRenderer();
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 , 120 );
		bdef.type = BodyType.StaticBody;
		Body body = mundo.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 , 5 );
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		
		// create falling box
		bdef.position.set(160 , 200 );
		bdef.type = BodyType.DynamicBody;
		body = mundo.createBody(bdef);
		
		shape.setAsBox(5 , 5 );
		fdef.shape = shape;
		body.createFixture(fdef);

	}
	
	@Override
	public void render(float delta){
        Gdx.gl.glClearColor(0,128,0, 8);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mundo.step(delta, 6, 2);
		//batch.begin();
		//heroe.draw(batch, delta);
		//batch.end();
		renderizarMapa.setView(camara);
		renderizarMapa.render();
		heroe.render(delta);
		manejoEntrada(delta);
		camara.update();
		// draw box2d world
		b2dr.render(mundo, camara.combined);
		
	}
	
	public void manejoEntrada(float delta){
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			heroe.mirarDerecha();
			heroe.estadoMoviendose();
			heroe.moverDerecha(delta, velocidad);
			//camara.position.set(camara.position.x+velocidad*delta, camara.position.y, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			heroe.mirarIzquierda();
			heroe.estadoMoviendose();
			heroe.moverIzquierda(delta, velocidad);
			//camara.position.set(camara.position.x-velocidad*delta, camara.position.y, camara.position.z);
			}
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			
			heroe.mirarArriba();
			heroe.estadoMoviendose();
			heroe.moverArriba(delta, velocidad);
			//camara.position.set(camara.position.x, camara.position.y+velocidad*delta, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			heroe.mirarAbajo();
			heroe.estadoMoviendose();
			heroe.moverAbajo(delta, velocidad);
			//camara.position.set(camara.position.x, camara.position.y-velocidad*delta, camara.position.z);
			
			}
	
		if(!Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) )
		{
		heroe.estadoQuieto();
		//heroe.quieto();
		}
		
	}
	
		
	@Override
	public void show() {
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
		
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	//creando las paredes
	/*private void createWalls() {
		
		anchomapa =  (Float) mapa.getProperties().get("width");
		altomapa =  (Float) mapa.getProperties().get("height");
		tileSize =  (Float) mapa.getProperties().get("tilewidth");
		
		// read each of the "red" "green" and "blue" layers
		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer) mapa.getLayers().get("colisiones");
		createBlocks(layer);

		
	}*/
	
	/// creando bloques de colisiones desde el mapa
	
		/*private void createBlocks(TiledMapTileLayer layer) {
		
		// tile size
		float ts = layer.getTileWidth();
		
		// go through all cells in layer
		for(int row = 0; row < layer.getHeight(); row++) {
			for(int col = 0; col < layer.getWidth(); col++) {
				
				// get cell
				Cell cell = layer.getCell(col, row);
				
				// check that there is a cell
				if(cell == null) continue;
				if(cell.getTile() == null) continue;
				
				// create body from cell
				BodyDef bdef = new BodyDef();
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * ts / PPM, (row + 0.5f) * ts / PPM);
				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-ts / 2 / PPM, -ts / 2 / PPM);
				v[1] = new Vector2(-ts / 2 / PPM, ts / 2 / PPM);
				v[2] = new Vector2(ts / 2 / PPM, ts / 2 / PPM);
				cs.createChain(v);
				FixtureDef fd = new FixtureDef();
				fd.friction = 0;
				fd.shape = cs;
				
				mundo.createBody(bdef).createFixture(fd);
				cs.dispose();
				
			}
		}
		
	
	
		}*/
}
