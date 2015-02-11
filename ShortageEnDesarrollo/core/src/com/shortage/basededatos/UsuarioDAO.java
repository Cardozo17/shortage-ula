package com.shortage.basededatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UsuarioDAO {
	
	/**
	 * Permite agregar un usuario
	 * 
	 * @param producto
	 */

	public boolean registrarUsuario(UsuarioVO usuario) {
		DBConnection conex = new DBConnection();
		try {
			Statement estatuto = (Statement) conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO  usuario (nombre, clave) VALUES ('"
					+ usuario.getNombre() + "', '" + usuario.getClave() +"')");
			JOptionPane.showMessageDialog(null,
					"Se ha registrado el usuario exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();
			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//JOptionPane.showMessageDialog(null,
			//		"No se pudo agregar el usuario, Nombre de Usuario No Disponible");
			return false;
		}
	}
	
	public void actualizarPuntajeMayor(String nombre){}

	/**
	 * Permite eliminar un producto
	 * 
	 * @param codigo
	 */

	public void eliminarUsuario(String nombre) {
		DBConnection conex = new DBConnection();
		try {
			PreparedStatement consulta = (PreparedStatement) conex
					.getConnection().prepareStatement(
							"DELETE FROM usuario WHERE nombre = ? ");
			consulta.setString(1, nombre);
			consulta.execute();

			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se pudo eliminar el usuario\n" + e);
		}

	}

	/**
	 * permite consultar un usuario asociado, el login es enviado como parametro
	 * 
	 * @param login
	 * @return
	 */
	public ArrayList<UsuarioVO> consultarUsuario(String nombre) {
		ArrayList<UsuarioVO> miUsuario = new ArrayList<UsuarioVO>();
		DBConnection conex = new DBConnection();

		try {
			PreparedStatement consulta = (PreparedStatement) conex.getConnection().prepareStatement(
							"SELECT * FROM usuario where nombre = ? ");
			consulta.setString(1, nombre);
			ResultSet res = consulta.executeQuery();

			if (res.next()) {
				UsuarioVO usuario = new UsuarioVO();
				usuario.setNombre(res.getString("nombre"));
				usuario.setClave(res.getString("clave"));
				usuario.setPuntajemayor(Integer.valueOf(res.getString("puntajemayor")));
				miUsuario.add(usuario);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se pudo consultar el usuario\n" + e);
		}
		return miUsuario;
	}

	/**
	 * permite consultar la lista de usuarios
	 * 
	 * @return
	 */
	public ArrayList<UsuarioVO> listaDeUsuarios() {
		ArrayList<UsuarioVO> miUsuario = new ArrayList<UsuarioVO>();
		DBConnection conex = new DBConnection();

		try {
			PreparedStatement consulta = (PreparedStatement) conex.getConnection().prepareStatement("SELECT * FROM usuario");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				UsuarioVO usuario = new UsuarioVO();
				usuario.setNombre(res.getString("nombre"));
				usuario.setClave(res.getString("clave"));
				usuario.setPuntajemayor(Integer.valueOf(res.getString("puntajemayor")));
				miUsuario.add(usuario);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se pudo consultar la lista de usuarios\n" + e);
		}
		return miUsuario;
	}

	/**
	 * permite actualizar un usuario asociado a un login enviado y un login
	 * enviado como parametro
	 * 
	 * @param idviejo
	 *            producto
	 * @return
	 */
	public boolean actualizarUsuario(UsuarioVO usuario) {
		DBConnection conex = new DBConnection();

		try {
			PreparedStatement consulta = (PreparedStatement) conex.getConnection().prepareStatement(
							"UPDATE producto SET nombre= ?,clave= ?");
			consulta.setString(1, usuario.getNombre());
			consulta.setString(2, usuario.getClave());
			//consulta.setString(3, Integer.toString(usuario.getPuntajemayor()));
			consulta.execute();

			consulta.close();
			conex.desconectar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se pudo eliminar el usuario\n" + e);
			return false;
		}
		return true;
	}
	
	
	public boolean buscarUsuario(String nombre){
		DBConnection conex = new DBConnection();
		try {
			PreparedStatement consulta = (PreparedStatement) conex.getConnection().prepareStatement(
							"SELECT * FROM usuario where nombre= ?");
			consulta.setString(1, nombre);	
			consulta.execute();
		
			consulta.close();
			conex.desconectar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No existe el usuario\n" + e);
			return false;
		}
		return true;
	}
		
	

}
