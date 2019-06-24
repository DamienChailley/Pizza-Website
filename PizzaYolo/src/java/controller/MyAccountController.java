/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entityServer.Commande;
import entityServer.Utilisateur;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import model.CommandeDAO;
import model.UtilisateurDAO;

/**
 *
 * @author RetailAdmin
 */


@ManagedBean
@ViewScoped
public class MyAccountController implements Serializable {
     
    private List<Commande> commandes;
    
    private Commande selectedCommand;

    private int connectedIdUser = 2; //todo

    private Utilisateur user;


     
    @PostConstruct
    public void init() {
        commandes = CommandeDAO.get_all_commands_by_id_user(connectedIdUser);
        this.user = UtilisateurDAO.get_user_by_id(connectedIdUser);      
    }
  
    
    public List<Commande> getCommandes() {
        return commandes;
    }
        
     public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Commande getSelectedCommand() {
        return selectedCommand;
    }

    public void setSelectedCommand(Commande selectedCommand) {
        this.selectedCommand = selectedCommand;
    }
     
    public int getConnectedIdUser() {
        return connectedIdUser;
    }

    public void setConnectedIdUser(int connectedIdUser) {
        this.connectedIdUser = connectedIdUser;
    }

       public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
   
    
  
}
