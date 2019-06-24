/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.PizzaDAO;

/**
 *
 * @author yan20
 */
public class Pizza implements Serializable {
    private int id_pizza;
    private String nom_pizza;
    private double prix;
    private String image;    
    private String lesIngredients;
    

    public Pizza(int id_pizza, String nom_pizza, double prix, String image) {
        this.id_pizza = id_pizza;
        this.nom_pizza = nom_pizza;
        this.prix = prix;
        this.image = image;
        lesIngredients = PizzaDAO.get_Pizza_ingredient_by_Pizza_Id(id_pizza);
    }

    public Pizza() {
        
    }

    public int getId_pizza() {
        return id_pizza;
    }

    public void setId_pizza(int id_pizza) {
        this.id_pizza = id_pizza;
    }

    public String getNom_pizza() {
        return nom_pizza;
    }

    public void setNom_pizza(String nom_pizza) {
        this.nom_pizza = nom_pizza;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLesIngredients() {
        return lesIngredients;
    }

    public void setLesIngredients(String lesIngredients) {
        this.lesIngredients = lesIngredients;
    }

    
    
    
    
    
    
}
