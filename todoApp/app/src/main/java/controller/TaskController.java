/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        Connection conn = null;
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
            
            ConnectionFactory.closeConnection(conn, statement);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir os dados ");
        }
    }
    public void update(Task task){
        String sql = "ALTER FROM tasks  ";
    }
    public void removeById(int taskId) throws SQLException{
        String sql ="DELETE FROM tasks WHERE id = ? ";
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1,taskId);
            statement.execute();
            
            ConnectionFactory.closeConnection(conn, statement);
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa ");
        } finally{
            ConnectionFactory.closeConnection(conn);
        }
    }
    public List <Task> getAll(int idProject){
        
        return null;
        
    }
}
