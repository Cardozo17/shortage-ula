package com.shortage.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite conectar con la base de datos
 *
 **/

public class DBConnection {
	
		   /**Parametros de conexion**/
		   static String bd = "shortage";
		   static String login = "root";
		   static String password = "runescape";
		   static String url = "jdbc:mysql://localhost:3306/"+bd;
	
	Connection connection = null;
	 
	   /** Constructor de DbConnection */
	   public DBConnection() {
	      try{
	         //obtenemos el driver para mysql
	         Class.forName("com.mysql.jdbc.Driver");
	         //obtenemos la conexi�n
	         connection = DriverManager.getConnection(url,login,password);
	 
	         if (connection!=null){
	            System.out.println("Conexi�n a base de datos "+bd+" OK\n");
	         }
	      }
	      catch(SQLException e){
	         System.out.println(e);
	      }catch(ClassNotFoundException e){
	         System.out.println(e);
	      }catch(Exception e){
	         System.out.println(e);
	      }
	   }
	   /**Permite retornar la conexi�n*/
	   public Connection getConnection(){
	      return connection;
	   }
	 
	   public void desconectar(){
	      connection = null;
	   }
	   
	}

