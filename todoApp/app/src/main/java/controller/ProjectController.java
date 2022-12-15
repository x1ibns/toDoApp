/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Project;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;
/**
 *
 * @author ADM
 */
public class ProjectController {
    public static void save(Project project) throws SQLException {
        String sql = "INSERT INTO projects (name ,"
                + "description ,"
                + "dateCreatedAt , "
                + "dateUpdatedAt) VALUES (?,?,?,?)";
        
         //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getDateCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getDateUpdatedAt().getTime()));
            
            statement.execute();
            System.out.println("Projeto salvo com sucesso !  ");
        } catch (SQLException e) {
            
            throw new SQLException("Erro ao salvar os dados do projeto",e);
        
        } finally {
            
            ConnectionFactory.closeConnection(conn, statement);
            System.out.println("Fechando conexão com o banco de dados ! ...");
        }
        
    }
    public static void update(Project project) {
        
        String sql = "UPDATE FROM projects SET ("
                + "name = ? ,"
                + "description = ? ,"
                + "dateCreatedAt = ? ,"
                + "dateUpdatedAt = ?)"
                + "WHERE id = ?";
        
         //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        try {
            
            statement.setString(1, project.getName());
            statement.setString(2 , project.getDescription());
            statement.setDate(3, new Date(project.getDateCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getDateUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            statement.execute();
             System.out.println("Projeto atualizado com sucesso !  ");
             
        } catch (Exception e) {

            throw new RuntimeException("Erro ao atualizar os dados ",e);
        
        } finally {
            
            ConnectionFactory.closeConnection(conn, statement);
            System.out.println("Fechando conexão com o banco de dados ! ...");
        }
    }
    public static void deleteById(int  projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";
        
         //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();
            System.out.println("Projeto deletado com sucesso !  ");
                    
        } catch (Exception e) {
            
            throw new RuntimeException("Erro deletar o projeto do banco ",e);
            
        } finally {
            
            ConnectionFactory.closeConnection(conn, statement);
            System.out.println("Fechando conexão com o banco de dados ! ...");
        }
    }
    public static List<Project> list() {
        
        String sql = "SELECT * FROM projects";
        //Estabelecendo a conexão com o banco de dados        
        Connection conn = null ;
        //Preparando a query
        PreparedStatement statement = null;
        
        //Criando a variavel que armazenara ao resuçlta da consulta ao banco
        ResultSet resultado =  null ; 
        
        List <Project> projects = new ArrayList<Project>();
        
        try {
            
            conn = ConnectionFactory.getConnection();            
            statement = conn.prepareStatement(sql);
           
                 
            resultado =  statement.executeQuery();
            
            while(resultado.next()){
                
                Project project = new Project();
                project.setId(resultado.getInt("id"));
                project.setDescription(resultado.getString("description"));
                project.setDateCreatedAt(resultado.getDate("dateCreatedAt"));
                project.setDateUpdatedAt(resultado.getDate("dateUpdatedAt"));
               
                projects.add(project);
            }
            
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao listar os projetos do banco",e);
            
        } finally {
            
            ConnectionFactory.closeConnection(conn, statement, resultado);
            System.out.println("Fechando conexão com o banco de dados ! ...");
        }
         System.out.println("Lista de projetos criada e carregada do banco de dados : ");
        
        //lista de tarefas devolvida quando for chamado o método getAll
        return projects;
    }
    
}
