/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityServer;

import java.io.Serializable;
import java.util.Date;
import model.PizzaDAO;

public class Commande implements Serializable {
    private int id_commande;
    private Date date_commande;
    private Date date_Livraison;
    private String taille_pizza;
    private double prix;
    private int id_user;
    private int id_pizza;
    private int id_liv;
    private Pizza pizza;

    public Commande() {
    }

    public Commande(int id_commande, Date date_commande, Date date_Livraison, String taille_pizza, double prix, int id_client, int id_pizza, int id_liv) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.date_Livraison = date_Livraison;
        this.taille_pizza = taille_pizza;
        this.prix = prix;
        this.id_user = id_client;
        this.id_pizza = id_pizza;
        this.id_liv = id_liv;
        this.pizza = PizzaDAO.get_pizza_by_id(id_pizza);
    }
    
    

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public Date getDate_Livraison() {
        return date_Livraison;
    }

    public void setDate_Livraison(Date date_Livraison) {
        this.date_Livraison = date_Livraison;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
   

    public String getTaille_pizza() {
        return taille_pizza;
    }

    public void setTaille_pizza(String taille_pizza) {
        this.taille_pizza = taille_pizza;
    }    

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_pizza() {
        return id_pizza;
    }

    public void setId_pizza(int id_pizza) {
        this.id_pizza = id_pizza;
    }

    public int getId_liv() {
        return id_liv;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", date_commande=" + date_commande + ", date_Livraison=" + date_Livraison + ", taille_pizza=" + taille_pizza + ", prix=" + prix + ", id_user=" + id_user + ", id_pizza=" + id_pizza + ", id_liv=" + id_liv + ", pizza=" + pizza + '}';
    }
    
    
    
}
