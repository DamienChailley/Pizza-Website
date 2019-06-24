/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityServer;

import java.io.Serializable;

/**
 *
 * @author yan20
 */
public class Ingredient implements Serializable {
    private int id_ing;
    private String nom_Ingre;

    public Ingredient() {
    }

    public Ingredient(int id_ing, String nom_Ingre) {
        this.id_ing = id_ing;
        this.nom_Ingre = nom_Ingre;
    }

    public int getId_ing() {
        return id_ing;
    }

    public void setId_ing(int id_ing) {
        this.id_ing = id_ing;
    }

    public String getNom_Ingre() {
        return nom_Ingre;
    }

    public void setNom_Ingre(String nom_Ingre) {
        this.nom_Ingre = nom_Ingre;
    }
    
    
    
}
