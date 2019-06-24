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
public class Utilisateur implements Serializable {
    private int id_user;
    private String nom_user;
    private String login;
    private String motDePasse;
    private String role;
    private double solde;
    private int nb_pizzas;
    private String carteBancaire;
    private boolean abonnement;
    private String prenom_user;
    
    public Utilisateur() {
    }

     public Utilisateur(int id_client, String prenom_user, String nom_user, String login, String motDePasse, String role, double solde, int nb_pizzas, String carteBancaire, boolean abonnement) {
        this.id_user = id_client;
        this.prenom_user = prenom_user;
        this.nom_user = nom_user;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
        this.solde = solde;
        this.nb_pizzas = nb_pizzas;
        this.carteBancaire = carteBancaire;
        this.abonnement = abonnement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public int getNb_pizzas() {
        return nb_pizzas;
    }

    public void setNb_pizzas(int nb_pizzas) {
        this.nb_pizzas = nb_pizzas;
    }

    public String getCarteBancaire() {
        return carteBancaire;
    }

    public void setCarteBancaire(String carteBancaire) {
        this.carteBancaire = carteBancaire;
    }

    public boolean isAbonnement() {
        return abonnement;
    }

    public void setAbonnement(boolean abonnement) {
        this.abonnement = abonnement;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }
    
    
    
    
}
