/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityServer;

import java.io.Serializable;

public class Livreur implements Serializable{
    private int id_liv;
    private String nom_livreur;
    private String prenom_livreur;
    private int totalRetards;
    private int TempsTotalRetards;

    public Livreur() {
    }

    public Livreur(int id_liv, String nom_livreur, String prenom_livreur) {
        this.id_liv = id_liv;
        this.nom_livreur = nom_livreur;
        this.prenom_livreur = prenom_livreur;
    }

    public int getId_liv() {
        return id_liv;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    public String getNom_livreur() {
        return nom_livreur;
    }

    public void setNom_livreur(String nom_livreur) {
        this.nom_livreur = nom_livreur;
    }

    public String getPrenom_livreur() {
        return prenom_livreur;
    }

    public void setPrenom_livreur(String prenom_livreur) {
        this.prenom_livreur = prenom_livreur;
    }

    public int getTotalRetards() {
        return totalRetards;
    }

    public void setTotalRetards(int totalRetards) {
        this.totalRetards = totalRetards;
    }

    public int getTempsTotalRetards() {
        return TempsTotalRetards;
    }

    public void setTempsTotalRetards(int TempsTotalRetards) {
        this.TempsTotalRetards = TempsTotalRetards;
    }
    
    
    
    
    
}
