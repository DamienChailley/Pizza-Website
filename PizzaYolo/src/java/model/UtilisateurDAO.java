/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import database.DataConnect;
import entityServer.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UtilisateurDAO {
    
      public static Utilisateur get_user_by_id(int id_user) {
        Utilisateur unUtilisateur = new Utilisateur();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " SELECT `utilisateur`.`id_user`, "
                    + " `utilisateur`.`prenom_user`, "
                    + " `utilisateur`.`Nom_user`, "
                    + " `utilisateur`.`login`, "
                    + " `utilisateur`.`motDePasse`, "
                    + " `utilisateur`.`role`, "
                    + " `utilisateur`.`solde`, "
                    + " `utilisateur`.`nb_pizzas`, "
                    + " `utilisateur`.`carteBancaire`, "
                    + " `utilisateur`.`Abonnement` "
                    + " FROM `utilisateur`  "
                    + " WHERE id_user = '" + id_user + "' ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 id_user = rs.getInt("id_user");
                String prenom_user = rs.getString("prenom_user");
                String Nom_user = rs.getString("Nom_user");
                String login = rs.getString("login");
                String motDePasse = rs.getString("motDePasse");
                String role = rs.getString("role");
                double solde = rs.getDouble("solde");                
                int nb_pizzas = rs.getInt("nb_pizzas");                
                String carteBancaire = rs.getString("carteBancaire");
                boolean Abonnement = rs.getBoolean("Abonnement");
                
                unUtilisateur = new Utilisateur(id_user, prenom_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_user_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unUtilisateur;

    }
    
    public static List<Utilisateur> get_all_user() {
        List<Utilisateur> lesUtilisateurs = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " SELECT `utilisateur`.`id_user`, "
                    + " `utilisateur`.`prenom_user`, "
                    + " `utilisateur`.`Nom_user`, "
                    + " `utilisateur`.`login`, "
                    + " `utilisateur`.`motDePasse`, "
                    + " `utilisateur`.`role`, "
                    + " `utilisateur`.`solde`, "
                    + " `utilisateur`.`nb_pizzas`, "
                    + " `utilisateur`.`carteBancaire`, "
                    + " `utilisateur`.`Abonnement` "
                    + " FROM `utilisateur` ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String prenom_user = rs.getString("prenom_user");
                String Nom_user = rs.getString("Nom_user");
                String login = rs.getString("login");
                String motDePasse = rs.getString("motDePasse");
                String role = rs.getString("role");
                double solde = rs.getDouble("solde");                
                int nb_pizzas = rs.getInt("nb_pizzas");                
                String carteBancaire = rs.getString("carteBancaire");
                boolean Abonnement = rs.getBoolean("Abonnement");
                
                Utilisateur unUtilisateur = new Utilisateur(id_user, prenom_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement);
                
                lesUtilisateurs.add(unUtilisateur);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_all_user error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesUtilisateurs;

    }
    
    public static Utilisateur insert_user(String prenom_user, String nom_user, String login, String motDePasse, String role, double solde, int nb_pizzas, String carteBancaire, boolean abonnement) {
        Utilisateur unUtilisateur = new Utilisateur();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " INSERT INTO utilisateur(prenom_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement) "
                    + " SELECT '"+prenom_user+"', "
                    + " '"+nom_user+"', "
                    + " '"+login+"', "
                    + " '"+motDePasse+"', "
                    + " '"+role+"', "
                    + " '"+solde+"', "
                    + " '"+nb_pizzas+"', "
                    + " '"+carteBancaire+"', "
                    + " "+abonnement+" ; ";

            ps = con.prepareStatement(
                    SQL, Statement.RETURN_GENERATED_KEYS
            );
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            int  user_id = 0;
            if (rs.next()) {
            user_id = rs.getInt(1);
            }
            if(user_id>0)
            {
                 unUtilisateur = get_user_by_id(user_id);
            }                    
            

        } catch (SQLException ex) {
            System.out.println("Exception e get_user_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unUtilisateur;

    }
    
     public static void update_user(int user_id, String prenom_user, String nom_user, String login, String motDePasse, String role, double solde, int nb_pizzas, String carteBancaire, boolean abonnement) {
        

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " UPDATE utilisateur "
                    + " SET prenom_user = '"+prenom_user+"', "
                    + " nom_user = '"+nom_user+"', "
                    + " login = '"+login+"', "
                    + " motDePasse = '"+motDePasse+"', "
                    + " role = '"+role+"', "
                    + " solde = '"+solde+"', "
                    + " nb_pizzas = '"+nb_pizzas+"', "
                    + " carteBancaire = '"+carteBancaire+"', "
                    + " abonnement ="+abonnement+" "
                    + " where id_user  ='"+user_id+"' ; ";

            ps = con.prepareStatement(
                    SQL
            );  
            
            ps.executeUpdate();
            

        } catch (SQLException ex) {
            System.out.println("Exception e update_user error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }
     
     public static Utilisateur check_User_Connection(String username, String password) {
        Utilisateur unUtilisateur = new Utilisateur();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " SELECT `utilisateur`.`id_user`, "
                    + " `utilisateur`.`prenom_user`, "
                    + " `utilisateur`.`Nom_user`, "
                    + " `utilisateur`.`login`, "
                    + " `utilisateur`.`motDePasse`, "
                    + " `utilisateur`.`role`, "
                    + " `utilisateur`.`solde`, "
                    + " `utilisateur`.`nb_pizzas`, "
                    + " `utilisateur`.`carteBancaire`, "
                    + " `utilisateur`.`Abonnement` "
                    + " FROM `utilisateur`  "
                    + " WHERE login = '" + username + "'"
                    + " AND motDePasse  = '" + password + "' ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_user = rs.getInt("id_user");
                String prenom_user = rs.getString("prenom_user");
                String Nom_user = rs.getString("Nom_user");
                String login = rs.getString("login");
                String motDePasse = rs.getString("motDePasse");
                String role = rs.getString("role");
                double solde = rs.getDouble("solde");                
                int nb_pizzas = rs.getInt("nb_pizzas");                
                String carteBancaire = rs.getString("carteBancaire");
                boolean Abonnement = rs.getBoolean("Abonnement");
                
                unUtilisateur = new Utilisateur(id_user, prenom_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_user_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unUtilisateur;

    }
    
     
    
}
