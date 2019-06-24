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
public class Vehicule implements Serializable{
    private int id_vehi;
    private String type_vehi;
    private String nom_vehi;

    public Vehicule(int id_veh, String type_vehi, String nom_vehi) {
        this.id_vehi = id_veh;
        this.type_vehi = type_vehi;
        this.nom_vehi = nom_vehi;
    }

    public Vehicule() {
    }

    public int getId_vehi() {
        return id_vehi;
    }

    public void setId_vehi(int id_vehi) {
        this.id_vehi = id_vehi;
    }

    public String getType_vehi() {
        return type_vehi;
    }

    public void setType_vehi(String type_vehi) {
        this.type_vehi = type_vehi;
    }

    public String getNom_vehi() {
        return nom_vehi;
    }

    public void setNom_vehi(String nom_vehi) {
        this.nom_vehi = nom_vehi;
    }
    
    
    
    
}
