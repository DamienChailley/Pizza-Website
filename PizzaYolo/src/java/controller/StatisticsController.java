/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.IndexView;
import entityServer.Livreur;
import entityServer.Pizza;
import entityServer.Utilisateur;
import entityServer.Vehicule;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import model.PizzaDAO;
import model.StatisticsDAO;
import model.VehiculeDAO;
import org.primefaces.event.ToggleEvent;


// was this class FieldsetView

@ManagedBean
public class StatisticsController {
    
    
    private Utilisateur currentConnectedUser;
    
    double chiffreDaffaires;    
    int nombreDeClients;    
    
    Utilisateur meilleurClient_argent;
    Utilisateur meilleurClient_commandes;
    
    Livreur pireLivreur;
    List<Vehicule> vehiculesMoinsUtilises;    
    
    
    Pizza meilleurePizza;
    Pizza pirePizza;
    
    int nbNonFacturation_fidelite;
    int nbNonFacturation_retards;
     
     @PostConstruct
    public void init() {
        currentConnectedUser = IndexView.get_static_currentConnectedUser();
        
        // ------- Entreprise --------
        chiffreDaffaires = StatisticsDAO.get_ChiffreDaffaires();        
        nombreDeClients = StatisticsDAO.get_NombreClient();
        
        // ------- Ressources --------
        meilleurClient_argent = StatisticsDAO.get_MeilleurClient_by_ArgentDepense();
        meilleurClient_commandes = StatisticsDAO.get_MeilleurClient_by_NombreCommande();        
        pireLivreur = StatisticsDAO.get_PireLivreur_by_NombreRetards();        
        vehiculesMoinsUtilises = VehiculeDAO.get_all_vehicules_non_Utilises();
                
        // ------- Produits --------
        meilleurePizza = PizzaDAO.get_best_pizza();
        pirePizza = PizzaDAO.get_Pire_Pizza();
        
        // ------- Divers --------
        nbNonFacturation_fidelite = StatisticsDAO.get_NombreNonFacturation_Fidelite();
        nbNonFacturation_retards = StatisticsDAO.get_NombreNonFacturation_Retards();
        
    }
    
    public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Toggled", "Visibility:" + event.getVisibility());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public double getChiffreDaffaires() {
        return chiffreDaffaires;
    }

    public void setChiffreDaffaires(double chiffreDaffaires) {
        this.chiffreDaffaires = chiffreDaffaires;
    }

    public int getNombreDeClients() {
        return nombreDeClients;
    }

    public void setNombreDeClients(int nombreDeClients) {
        this.nombreDeClients = nombreDeClients;
    }

    public Utilisateur getMeilleurClient_argent() {
        return meilleurClient_argent;
    }

    public void setMeilleurClient_argent(Utilisateur meilleurClient_argent) {
        this.meilleurClient_argent = meilleurClient_argent;
    }

    public Utilisateur getMeilleurClient_commandes() {
        return meilleurClient_commandes;
    }

    public void setMeilleurClient_commandes(Utilisateur meilleurClient_commandes) {
        this.meilleurClient_commandes = meilleurClient_commandes;
    }

    public Livreur getPireLivreur() {
        return pireLivreur;
    }

    public void setPireLivreur(Livreur pireLivreur) {
        this.pireLivreur = pireLivreur;
    }

    public List<Vehicule> getVehiculesMoinsUtilises() {
        return vehiculesMoinsUtilises;
    }

    public void setVehiculesMoinsUtilises(List<Vehicule> vehiculesMoinsUtilises) {
        this.vehiculesMoinsUtilises = vehiculesMoinsUtilises;
    }

    public Pizza getMeilleurePizza() {
        return meilleurePizza;
    }

    public void setMeilleurePizza(Pizza meilleurePizza) {
        this.meilleurePizza = meilleurePizza;
    }

    public Pizza getPirePizza() {
        return pirePizza;
    }

    public void setPirePizza(Pizza pirePizza) {
        this.pirePizza = pirePizza;
    }

    public int getNbNonFacturation_fidelite() {
        return nbNonFacturation_fidelite;
    }

    public void setNbNonFacturation_fidelite(int nbNonFacturation_fidelite) {
        this.nbNonFacturation_fidelite = nbNonFacturation_fidelite;
    }

    public int getNbNonFacturation_retards() {
        return nbNonFacturation_retards;
    }

    public void setNbNonFacturation_retards(int nbNonFacturation_retards) {
        this.nbNonFacturation_retards = nbNonFacturation_retards;
    }

    public  Utilisateur getCurrentConnectedUser() {
        return currentConnectedUser;
    }

    public  void setCurrentConnectedUser(Utilisateur currentConnectedUser) {
         currentConnectedUser = currentConnectedUser;
    }
    
    
}
