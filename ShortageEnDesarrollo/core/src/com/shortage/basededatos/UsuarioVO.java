package com.shortage.basededatos;

public class UsuarioVO {
	
	private int id;
	private String nombre;
	private String clave;
	private int puntajemayor;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getPuntajemayor() {
		return puntajemayor;
	}
	public void setPuntajemayor(int puntajemayor) {
		this.puntajemayor = puntajemayor;
	}

}
