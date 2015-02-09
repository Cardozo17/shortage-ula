package com.shortage.pantallas;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.shortage.entidades.Coleccionable;
import com.shortage.entidades.Personaje;
import com.shortage.game.Shortage;
import com.shortage.hud.Hud;
import com.shortage.manejadores.ContactBodies;

public class PantallaPlay extends PantallaAbstracta implements InputProcessor {
	
	static final double velocidad=2000000000;
	private TiledMap mapa;
	OrthographicCamera camara, camaraHud;
	OrthogonalTiledMapRenderer renderizarMapa;
	Personaje heroe;
	SpriteBatch batch;
	World mundo;
	Body obstaculo;
	BodyDef bodydefObstaculo;
	final float PPM=1;
	Music musica;
	Coleccionable harina,leche,papel;

	ContactBodies gestorColiciones;
	private Box2DDebugRenderer b2dr;
	Array<Body> cuerposABorrar=new Array<Body>();
	boolean harinaViva=true,lecheViva=true,papelVivo=true;
	
	Hud hud;
	
	public PantallaPlay(Shortage game) {
		super(game);
		batch= new SpriteBatch();
		mapa= new TmxMapLoader().load("mapa1.3.tmx");
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth()/PPM, Gdx.graphics.getHeight()/PPM);
		camara.update();
		renderizarMapa= new OrthogonalTiledMapRenderer(mapa);
		heroe= new Personaje(camara);
		harina=new Coleccionable(50*32,(149-94)*32,"harinaTextura.png");
		papel=new Coleccionable(2941,859,"papelTextura.png");
		leche=new Coleccionable(2876,2202,"lecheTextura.png");
		
		Gdx.input.setInputProcessor(this);
		mundo=new World(new Vector2(0, 0), true);
		
		//creando los cuerpos de las entidades
		heroe.crearCuerpo(mundo);
		harina.crearCuerpo(mundo);
		papel.crearCuerpo(mundo);
		leche.crearCuerpo(mundo);
		
        //paredes de la capa
		createWalls();
        //dibuja los cuadros
		b2dr = new Box2DDebugRenderer();
		//Musica de pantalla play
		musica= Gdx.audio.newMusic(Gdx.files.internal("Britney Spears - 3 (Doctor P Dubstep Remix) .mp3"));
		// Gestor de coliciones 
		gestorColiciones= new ContactBodies();
		mundo.setContactListener(gestorColiciones);
		gestorColiciones.setbody(heroe.getCuerpo(), harina.getCuerpo());
		
		hud= new Hud();
		camaraHud= new OrthographicCamera();
		}
	
	@Override
	public void render(float delta){
        Gdx.gl.glClearColor(0,0,0, 8);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        mundo.step(delta, 6, 2);
        batch.setProjectionMatrix(camara.combined);
        camara.update();
                
		renderizarMapa.render();
		//render de entidades
		
		heroe.render(delta);
		//harina.render(delta);
		manejoEntrada(delta);
		
		// draw box2d world
		b2dr.render(mundo, camara.combined);
		//seguir con la camara
		camara.position.set(heroe.getCuerpo().getPosition().x,heroe.getCuerpo().getPosition().y,camara.position.z);
		
	  
        batch.begin();
        if (harinaViva){
        	harina.draw(batch);
        }
        if(papelVivo){
        	papel.draw(batch);
        }
        if(lecheViva){
        	leche.draw(batch);
        }
		batch.end();
		
		renderizarMapa.setView(camara);
		//batch.setProjectionMatrix(camaraHud.combined);
	//	batch.begin();
		hud.render(delta);
		//batch.end();
		
		borrarCuerpos();
		
		musica.play();
		saliralMenu();
		findejuego();
		
		
		//System.out.println("x"+heroe.getCuerpo().getPosition().x+"  y"+heroe.getCuerpo().getPosition().y);
		
	}
	
	public void findejuego(){
		if(!lecheViva && !papelVivo && !harinaViva){
			Pantallas.juego.setScreen(Pantallas.FINDEJUEGO);
			musica.pause();
		}
	}
	
	
	public void borrarCuerpos(){
       Array<Body> cuerposABorrar = gestorColiciones.getBodiesDelete();
        for(int i=0;i<cuerposABorrar.size;i++){
        	
        	Body b=cuerposABorrar.get(i);
        	if(harina.getCuerpo() == b){
        		harinaViva=false;
        		mundo.destroyBody(b);
        		harina.dispose();
        		hud.setObjetosRestantes(hud.getObjetosRestantes()-1);
        	}
        		if(papel.getCuerpo() == b){
            		papelVivo=false;
            		mundo.destroyBody(b);
            		papel.dispose();
            		hud.setObjetosRestantes(hud.getObjetosRestantes()-1);
        		}
            		if(leche.getCuerpo() == b){
                		lecheViva=false;
                		mundo.destroyBody(b);
                		leche.dispose();
                		hud.setObjetosRestantes(hud.getObjetosRestantes()-1);
            		}
        	
        	
        }
        
        cuerposABorrar.clear();
		
	}
	
	public void saliralMenu(){
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)||Gdx.input.isKeyPressed(Keys.BACK)){
			Pantallas.juego.setScreen(Pantallas.MENUPRINCIPAL);
			musica.pause();
		}
	}
	
	public void manejoEntrada(float delta){
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			heroe.mirarDerecha();
			heroe.estadoMoviendose();
			heroe.moverDerecha(delta, velocidad);
			//camara.translate(10f, 0);
			//camara.position.set(camara.position.x+velocidad*delta, camara.position.y, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			heroe.mirarIzquierda();
			heroe.estadoMoviendose();
			heroe.moverIzquierda(delta, velocidad);
			//camara.translate(-10f, 0);
			//camara.position.set(camara.position.x-velocidad*delta, camara.position.y, camara.position.z);
			}
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			
			heroe.mirarArriba();
			heroe.estadoMoviendose();
			heroe.moverArriba(delta, velocidad);
			//camara.translate(0, 10f);
			//camara.position.set(camara.position.x, camara.position.y+velocidad*delta, camara.position.z);
		}else
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			heroe.mirarAbajo();
			heroe.estadoMoviendose();
			heroe.moverAbajo(delta, velocidad);
			//camara.translate(0, -10f);
			//camara.position.set(camara.position.x, camara.position.y-velocidad*delta, camara.position.z);
			
			}
	
		if(!Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) )
		{
		heroe.estadoQuieto();
		heroe.quieto();
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
		batch.dispose();
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
	private void createWalls() {
		
		/*anchomapa =  (int) mapa.getProperties().get("width");
		altomapa =  (int) mapa.getProperties().get("height");
		tileSize =  (int) mapa.getProperties().get("tilewidth");*/
		
		// read each of the "red" "green" and "blue" layers
		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer)mapa.getLayers().get(15);
		createBlocks(layer);

		
	}
	
	/// creando bloques de colisiones desde el mapa
	
		private void createBlocks(TiledMapTileLayer layer) {
		
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
		
	
	
		}
}
