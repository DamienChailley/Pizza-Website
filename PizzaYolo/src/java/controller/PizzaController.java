/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Tools.DateConversion;
import entityServer.Commande;
import entityServer.Pizza;
import entityServer.Utilisateur;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import model.CommandeDAO;
import model.LivreurDAO;
import model.PizzaDAO;
import model.UtilisateurDAO;
import org.primefaces.PrimeFaces;

/**
 *
 * @author yan20
 */
@ManagedBean(name="PizzaView")
@ViewScoped
public class PizzaController implements Serializable{
    
    private int id_pizza;
    private String nom_pizza;
    private double prix;
    private String taille;
    private String image;
    
    private int selected_id_pizza;
    private String selected_nom_pizza;
    private double selected_prix;
    private String selected_taille;
    private String selected_image;   
    
    private Pizza selected_pizza;
    
    private List<Pizza> lesPizzas;
    
    private List<String> images;
    
    String lesIngredientsSTR = "";
    
    
    private Utilisateur user;
    private int connectedIdUser = 2; // todo
    
    private String selected_Format;
    private double prixFinal;
    private String infoRadioButton;
    
    @PostConstruct
    public void init() {
        
        lesPizzas = PizzaDAO.get_all_pizza();
        
          images = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
        }
        
        this.user = UtilisateurDAO.get_user_by_id(connectedIdUser); 
    }
    
    public List<String> getImages() {
        return images;
    }
 
  
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getSelected_id_pizza() {
        return selected_id_pizza;
    }

    public void setSelected_id_pizza(int selected_id_pizza) {
        this.selected_id_pizza = selected_id_pizza;
    }

    public String getSelected_nom_pizza() {
        return selected_nom_pizza;
    }

    public void setSelected_nom_pizza(String selected_nom_pizza) {
        this.selected_nom_pizza = selected_nom_pizza;
    }

    public double getSelected_prix() {
        return selected_prix;
    }

    public void setSelected_prix(double selected_prix) {
        this.selected_prix = selected_prix;
    }

    public String getSelected_taille() {
        return selected_taille;
    }

    public void setSelected_taille(String selected_taille) {
        this.selected_taille = selected_taille;
    }

    public String getSelected_image() {
        return selected_image;
    }

    public void setSelected_image(String selected_image) {
        this.selected_image = selected_image;
    }

    public List<Pizza> getLesPizzas() {
        return lesPizzas;
    }

    public void setLesPizzas(List<Pizza> lesPizzas) {
        this.lesPizzas = lesPizzas;
    }

    public Pizza getSelected_pizza() {
        return selected_pizza;
    }

    public void setSelected_pizza(Pizza selected_pizza) {
        this.selected_pizza = selected_pizza;
    }

    public String getLesIngredientsSTR() {
        return lesIngredientsSTR;
    }

    public void setLesIngredientsSTR(String lesIngredientsSTR) {
        this.lesIngredientsSTR = lesIngredientsSTR;
    }

    public double getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(double prixFinal) {
        this.prixFinal = prixFinal;
    }

    public String getInfoRadioButton() {
        return infoRadioButton;
    }

    public void setInfoRadioButton(String infoRadioButton) {
        if(infoRadioButton != null){
            System.out.println("controller.PizzaController.setInfoRadioButton()" + this.infoRadioButton);
            this.infoRadioButton = infoRadioButton;    
            getFullPrice(); // on calcul le prix reél
        }
    }
    
    
    public void getFullPrice(){
        String[] info = null;
        info = infoRadioButton.split("_");
          
        int id_Pizza = Integer.parseInt(info[0]);
                    
        this.selected_pizza = PizzaDAO.get_pizza_by_id(id_Pizza);
        
        double currentPrix = this.selected_pizza.getPrix();
        
        //double currentPrix = Double.parseDouble(info[0]);
        this.selected_Format = info[1];
        
        
        System.out.println("controller.PizzaController.getFullPrice : Selected Format : " + this.selected_Format);
         System.out.println("controller.PizzaController.getFullPrice " + getPrix());
        if( this.selected_Format.equals("Humaine")){
            this.prixFinal =  currentPrix * 2;
        }
        else if( this.selected_Format.equals("Ogresse")) {
            this.prixFinal = currentPrix *3;
        }
        else {
            this.prixFinal = currentPrix;
        }
        System.out.println("controller.PizzaController.getFullPrice() : fin fonction");
    }
    
    //Fonction qui déclenche l'achat où non 
    public void buttonBuy() {
        getFullPrice();
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Achat effectué avec succes !", "Votre nouveau solde est de "+ this.user.getSolde());
         
        PrimeFaces.current().dialog().showMessageDynamic(message1);
        
        if(this.user.getSolde() < this.prixFinal)
        {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous n'avez pas assez d'argent", "Veuillez recharger votre solde.");
         
        PrimeFaces.current().dialog().showMessageDynamic(message);
        }
        else {
            //On a assez pour acheter la pizza 
            
            //Probalité d'avoir un retard
            Random rand = new Random();
            int borne_max = 100;
            int borne_min = 0;
            int rng_retard = rand.nextInt(borne_max - borne_min + 1) + borne_min;
            
            
            int min_de_retard =0;
            
            
            
            if( rng_retard > 90){
                int max = 60;
                int min = 0;
                min_de_retard = rand.nextInt(max - min + 1) + min;
                
                if(min_de_retard >= 30 ) {
                    this.prixFinal = 0;
                }
            }

            System.out.println("controller.PizzaController.buttonBuy() : Get Nb Pizzas : " + this.user.getNb_pizzas());
            if( this.user.getNb_pizzas() %10 == 0 ) {
                this.prixFinal = 0;
            }
            
            // On créé la commande 
            
            //création de la Date
            Date actuelle = new Date();
            // DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            // String Date_de_commande = dateFormat.format(actuelle);
            
            //création de la date de livraison
            
            Date Date_de_livraison = DateConversion.DatePlusXMin(min_de_retard);
            
            System.out.println("controller.PizzaController.buttonBuy() : Date de commande : " + actuelle);
            System.out.println("controller.PizzaController.buttonBuy() : Date de livraison : " + Date_de_livraison);
            
            // recupération du prix 
            //mettre This.FinalPrix;
            
            //id_user 
            // utilsier ça this.user.getId_user();
            
            // id pizza
            // utiliser this.selectedPizza.id
            
            
            // id_liv
            
            int nb_livreur = LivreurDAO.get_all_livreur().size();
            
            int id_livreur_en_charge = rand.nextInt(nb_livreur + 1) + borne_min;
            
            
            
            CommandeDAO.insert_commande(actuelle, Date_de_livraison, this.selected_pizza.getNom_pizza(), this.prixFinal, this.user.getId_user(),  this.selected_pizza.getId_pizza(), id_livreur_en_charge);
            
            //todo enlever argent 
        }
    }
    

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public int getConnectedIdUser() {
        return connectedIdUser;
    }

    public void setConnectedIdUser(int connectedIdUser) {
        this.connectedIdUser = connectedIdUser;
    }

    public String getSelected_Format() {
        return selected_Format;
    }

    public void setSelected_Format(String selected_Format) {
        if(selected_Format != null){
        this.selected_Format = selected_Format;
        }
 
    }
    
    
    
}
