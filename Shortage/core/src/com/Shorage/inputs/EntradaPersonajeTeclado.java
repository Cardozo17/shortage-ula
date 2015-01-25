package com.Shorage.inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;


public class EntradaPersonajeTeclado extends InputAdapter {
	
	private ControladorVirtual controlador;
	
	public EntradaPersonajeTeclado(ControladorVirtual controlador) {
		this.controlador = controlador;
	}
	
	/** keyDown = al empezar a pulsar una tecla */

	public boolean keyLeft(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
		case Input.Keys.A:
			if (!controlador.moverDerecha)
				controlador.moverIzquierda = true;
			return true;
		case Input.Keys.RIGHT:
		case Input.Keys.D:
			if (!controlador.moverIzquierda)
				controlador.moverDerecha = true;
			return true;
		default:
			return false;
		}
	}
	
	/** keyUp = al dejar de pulsar una tecla */

	public boolean keyRight(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
		case Input.Keys.A:
			controlador.moverIzquierda = false;
			return true;
		case Input.Keys.RIGHT:
		case Input.Keys.D:
			controlador.moverDerecha = false;
			return true;
		default:
			return false;
		}
	}
	

	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.DOWN:
		case Input.Keys.S:
			if (!controlador.moverArriba)
				controlador.moverAbajo = true;
			return true;
		case Input.Keys.UP:
		case Input.Keys.W:
			if (!controlador.moverAbajo)
				controlador.moverArriba= true;
			return true;
		default:
			return false;
		}
	}
	
	/** keyUp = al dejar de pulsar una tecla */
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Input.Keys.DOWN:
		case Input.Keys.S:
			controlador.moverAbajo = false;
			return true;
		case Input.Keys.UP:
		case Input.Keys.W:
			controlador.moverArriba = false;
			return true;
		default:
			return false;
		}
	}
	
}
