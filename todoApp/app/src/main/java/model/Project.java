package model;


import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADM
 */
public class Project {
    private int id;
    private String name;
    private String description;
    private Date dateCreatedAt;
    private Date dateUpdatedAt;

    public Project(int id, String name, String description, Date dateCreatedAt, Date dateUpdatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreatedAt = dateCreatedAt;
        this.dateUpdatedAt = dateUpdatedAt;
    }

    public Project() {
        this.dateCreatedAt = new Date();
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreatedAt() {
        return dateCreatedAt;
    }

    public void setDateCreatedAt(Date dateCreatedAt) {
        this.dateCreatedAt = dateCreatedAt;
    }

    public Date getDateUpdatedAt() {
        return dateUpdatedAt;
    }

    public void setDateUpdatedAt(Date dateUpdatedAt) {
        this.dateUpdatedAt = dateUpdatedAt;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", dateCreatedAt=" + dateCreatedAt + ", dateUpdatedAt=" + dateUpdatedAt + '}';
    }
    
    
    
}
