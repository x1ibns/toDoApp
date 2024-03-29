package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADM
 */
public class ConnectionFactory {
    public static final String DRIVER = "com.mysql.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/todoApp" ;
    public static final String USER= "victor" ;
    public static final String PASS = "1234";
    
    public static Connection getConnection(){
        try{
           Class.forName(DRIVER);
           return DriverManager.getConnection(URL, USER, PASS);
        }
        catch(Exception ex){
              throw new RuntimeException("Erro na conex�o com o banco de dados ",ex);
        }
    }
    public static void closeConnection(Connection connection ){
        try{
            if(connection != null){
                connection.close();
            }
        }
        catch(Exception ex){
            throw new RuntimeException("Erro no fechar a conex�o com o banco de dados ");
        }
    }
    
    public static void closeConnection(Connection connection , PreparedStatement statement ){
        try{
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
        }
        catch(Exception ex){
            throw new RuntimeException("Erro no fechar a conex�o com o banco de dados ");
        }
    }
    
     public static void closeConnection(Connection connection , PreparedStatement statement , ResultSet resultado){
        try{
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            
            if (resultado != null){
                resultado = null ;
            }
        }
        catch(Exception ex){
            throw new RuntimeException("Erro no fechar a conex�o com o banco de dados ");
        }
    }
}
