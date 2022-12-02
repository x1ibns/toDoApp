/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;



/**
 *
 * @author ADM
 */
public class TaskController {
    public void save(Task task) throws SQLException{
        String sql = "INSERT INTO tasks (idProject,"
                + "name,"
                + "descrition,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?,?,?,?,?,?,?,?) ";
        
        //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setInt(5,task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir os dados ");
        } finally{
             ConnectionFactory.closeConnection(conn, statement);
        }
    }
    public void update(Task task){
        
        String sql = "UPDATE FROM tasks SET "
                + "idProject = ? ,"
                + "name = ? ," 
                + "descrition = ? ,"
                + "notes = ? ,"
                + "completed = ? ,"
                + "deadline = ? ,"
                + "createdAt = ? ,"
                + "updatedAt = ? ,"
                + "WHERE id = ? " ;
        
        //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1,task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3,task.getDescription());
            statement.setInt(4,task.getNotes());
            statement.setBoolean(5, task.isCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate( 8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            statement.execute();
            
            
        } catch (Exception e) {
            
            throw new RuntimeException("Erro  ao atualizar os dados ");
            
        } finally{
            
            ConnectionFactory.closeConnection(conn, statement);
     
        }
                               
  }
    public void removeById(int taskId) throws SQLException{
    
        String sql ="DELETE FROM tasks WHERE id = ? ";
        
        //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1,taskId);
            statement.execute();
                
            
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao deletar a tarefa ");
        
        } finally{
        
            //Fechando a conexão com banco e a query
            ConnectionFactory.closeConnection(conn,statement);
        
        }
    }
    public List <Task> getAll(int idProject){
        
        String sql = "SELECT * FROM tasks WHERE idProject";
        
        //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        //Criando uma variavel para armazenar o resultado da busca no banco
        ResultSet resultado = null;
        
        //lista de tarefas que será devolvida quando for chamado o método getAll
        List <Task> tasks = new ArrayList<Task>();
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            
            resultado = statement.executeQuery();
            
            while(resultado.next()){
                
                Task task = new Task();
                task.setId(resultado.getInt("id"));
                task.setIdProject(resultado.getInt("idProject"));
                task.setName(resultado.getString("name"));
                task.setDescription(resultado.getString("description"));
                task.setNotes(resultado.getInt("notes"));
                task.setCompleted(resultado.getBoolean("completed"));
                task.setDeadline(resultado.getDate("deadline"));
                task.setCreatedAt(resultado.getDate("createAt"));
                task.setUpdatedAt(resultado.getDate("updatedAt"));
                
                tasks.add(task);
                               
            }
            
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao buscar dados no banco de dados ");
            
        } finally {
            
            ConnectionFactory.closeConnection(conn, statement, resultado);
        }
        
        System.out.println("Lista de tarefas criada e carregada do banco de dados : ");
        
        //lista de tarefas devolvida quando for chamado o método getAll
        return tasks;
        
    }
}
