/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entityServer.Utilisateur;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.UtilisateurDAO;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author yan20
 */
@ManagedBean(name="IndexView")
@SessionScoped
public class IndexView {
     
    public IndexView() {
        
        


    }
    
     // Utilisateur connecté à travers les pages
    
    private Utilisateur current_connected_user ;
    private static Utilisateur currentConnectedUser;
    
    // Savoir si un utilisateur est connecté pour afficher les menus correspondants
    private boolean loggedIn;
    
    // Gerer la visibilité des boutons Se Connecter et Deconnecter
    private boolean visible = true;
    
    private String username;     
    private String password;    
    private String orientation = "horizontal"; 
    
    // Renvoi le nom du pannel de connexion
    private String titleLoginPannel = "Se Connecter";
    
    public void login() {
//        FacesMessage message = null;
        loggedIn = false;
        
        current_connected_user = UtilisateurDAO.check_User_Connection(username, password);
        
        if(current_connected_user !=null) {
            if(current_connected_user.getLogin().equalsIgnoreCase(username) && current_connected_user.getMotDePasse().equalsIgnoreCase(password)) {
                loggedIn = true;
            currentConnectedUser = current_connected_user;
            visible = false;
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue", username);
            
            System.out.println("loggedIn "+loggedIn+ " User name : "+currentConnectedUser.getLogin()+" User id : "+currentConnectedUser.getId_user());
            }
            
        } else {
            loggedIn = false;
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur d'identification", "Identifiants erronés");
        }
         
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }  
    
    public void deconnect() {
        FacesMessage message = null;
        loggedIn = false;
        visible = true;
        current_connected_user = null;
        currentConnectedUser = current_connected_user;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aurevoir", username);
        username = null;
        
        show();
         
         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }  
    
    
    public void show(){
        titleLoginPannel="Se connecter";
    }
    
    public void hide(){
        visible = false;        
        titleLoginPannel="Deconnexion";
    }
    
    public String getOrientation() {
        return orientation;
    }
 
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getTitleLoginPannel() {
        return titleLoginPannel;
    }

    public void setTitleLoginPannel(String titleLoginPannel) {
        this.titleLoginPannel = titleLoginPannel;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Utilisateur getCurrent_connected_user() {
        return current_connected_user;
    }

    public void setCurrent_connected_user(Utilisateur current_connected_user) {
        this.current_connected_user = current_connected_user;
    }
    
     
    public static Utilisateur get_static_currentConnectedUser() {
        return currentConnectedUser;
    }
    
    
    // Menu View Part 
    
    private MenuModel model;
 
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
 
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
 
        DefaultMenuItem item = new DefaultMenuItem("External");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("pi pi-home");
        firstSubmenu.addElement(item);
 
        model.addElement(firstSubmenu);
 
        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
 
        item = new DefaultMenuItem("Save");
        item.setIcon("pi pi-save");
        item.setCommand("#{menuView.save}");
        item.setUpdate("messages");
        secondSubmenu.addElement(item);
 
        item = new DefaultMenuItem("Delete");
        item.setIcon("pi pi-times");
        item.setCommand("#{menuView.delete}");
        item.setAjax(false);
        secondSubmenu.addElement(item);
 
        item = new DefaultMenuItem("Redirect");
        item.setIcon("pi pi-search");
        item.setCommand("#{menuView.redirect}");
        secondSubmenu.addElement(item);
 
        model.addElement(secondSubmenu);
    }
 
    public MenuModel getModel() {
        return model;
    }
 
    public void save() {
        addMessage("Success", "Data saved");
    }
 
    public void update() {
        addMessage("Success", "Data updated");
    }
 
    public void delete() {
        addMessage("Success", "Data deleted");
    }
 
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static Utilisateur getCurrentConnectedUser() {
        return currentConnectedUser;
    }

    public static void setCurrentConnectedUser(Utilisateur currentConnectedUser) {
        IndexView.currentConnectedUser = currentConnectedUser;
    }
    
    
    
    
    
}