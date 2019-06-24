/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DataConnect;
import entityServer.Livreur;
import entityServer.Pizza;
import entityServer.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatisticsDAO {

    public static double get_ChiffreDaffaires() {
        double chiffreDaffaires = 0;

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();

            ps = con.prepareStatement(" SELECT sum(prix) FROM commandes ; ");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                chiffreDaffaires = rs.getDouble("sum(prix)");

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_chiffreDaffaires error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return chiffreDaffaires;

    }

    public static int get_NombreClient() {
        int nbClient = 0;

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();

            ps = con.prepareStatement(" select count(distinct id_user) from commandes ");
            //Pour compter seulement les clients qui ont commandé

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbClient = rs.getInt("count(distinct id_user)");

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_NombreClient error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return nbClient;

    }

    public static Utilisateur get_MeilleurClient_by_NombreCommande() {

        Utilisateur unUtilisateur = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " SELECT U.id_user, U.nom_user, U.prenom_user, count(id_commande) as NbCommande , sum(prix) as Achats, U.login, U.motDePasse, U.role, U.solde, U.nb_pizzas, U.carteBancaire, U.Abonnement   "
                    + " FROM Utilisateur U "
                    + "  INNER JOIN Commandes C on C.id_user = U.id_user "
                    + " Group BY U.id_user "
                    + " Order by NbCommande DESC, Achats DESC "
                    + "  limit 1; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_user = rs.getInt("id_user");
                String prenom_user = rs.getString("prenom_user");
                String Nom_user = rs.getString("nom_user");
                String login = rs.getString("login");
                String motDePasse = rs.getString("motDePasse");
                String role = rs.getString("role");
                double solde = rs.getDouble("solde");
                int nb_pizzas = rs.getInt("NbCommande");   // Car une commande = une pizza on stock le nb de commande dedans           
                String carteBancaire = rs.getString("carteBancaire");
                boolean Abonnement = rs.getBoolean("Abonnement");
                double TotalAchat = rs.getDouble("Achats");

                unUtilisateur = new Utilisateur(id_user, prenom_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement);

                unUtilisateur.setNb_commandes(nb_pizzas); // Car une commande = une pizza 
                unUtilisateur.setTotal_achat(TotalAchat);
                                
                


            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_MeilleurClient_by_NombreCommande error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unUtilisateur;

    }

    public static Utilisateur get_MeilleurClient_by_ArgentDepense() {        
        Utilisateur unUtilisateur = null;

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " SELECT U.id_user, U.nom_user, U.prenom_user, count(id_commande) as NbCommande , sum(prix) as Achats, U.login, U.motDePasse, U.role, U.solde, U.nb_pizzas, U.carteBancaire, U.Abonnement   "
                    + " FROM Utilisateur U "
                    + "  INNER JOIN Commandes C on C.id_user = U.id_user "
                    + " Group BY U.id_user "
                    + " Order by Achats DESC, NbCommande DESC "
                    + "  limit 1; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_user = rs.getInt("id_user");
                String prenom_user = rs.getString("prenom_user");
                String Nom_user = rs.getString("nom_user");
                String login = rs.getString("login");
                String motDePasse = rs.getString("motDePasse");
                String role = rs.getString("role");
                double solde = rs.getDouble("solde");
                int nb_pizzas = rs.getInt("NbCommande");   // Car une commande = une pizza on stock le nb de commande dedans           
                String carteBancaire = rs.getString("carteBancaire");
                boolean Abonnement = rs.getBoolean("Abonnement");

                unUtilisateur = new Utilisateur(id_user, prenom_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement);

                double TotalAchat = rs.getDouble("Achats");

                unUtilisateur = new Utilisateur(id_user, prenom_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement);

                unUtilisateur.setNb_commandes(nb_pizzas); // Car une commande = une pizza 
                unUtilisateur.setTotal_achat(TotalAchat);

                
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_MeilleurClient_by_ArgentDepense error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unUtilisateur;

    }
    
    public static Livreur get_PireLivreur_by_NombreRetards() {        
        Livreur pireLivreur = null;

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " SELECT id_liv, nom_livreur, prenom_livreur, date_commande, date_livraison, count(retard) as TotalRetards, sum(retard) TempsTotalRetards "+
                        " FROM "+
                        " (SELECT L.id_liv, L.nom_livreur, L.prenom_livreur, date_commande, date_livraison, TIMESTAMPDIFF(MINUTE, date_commande, date_livraison ) as retard "+
                        " FROM Livreur L "+
                        " INNER JOIN Commandes C on C.id_liv = L.id_liv "+
                        " WHERE TIMESTAMPDIFF(MINUTE, date_commande, date_livraison ) > 30 "+
                        " GROUP BY (id_commande) ) as T "+
                        " GROUP BY (id_Liv) "+
                        " ORDER BY TotalRetards DESC, TempsTotalRetards DESC"
                    + "  limit 1 ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_liv = rs.getInt("id_liv");
                String prenom_livreur = rs.getString("prenom_livreur");
                String nom_livreur = rs.getString("nom_livreur");
                int TempsTotalRetards = rs.getInt("TempsTotalRetards");
                int TotalRetards = rs.getInt("TotalRetards");
                //System.out.println("model.StatisticsDAO.get_PireLivreur_by_NombreRetards() => SQL "+SQL);
                pireLivreur = new Livreur(id_liv, prenom_livreur, nom_livreur);
                //System.out.println("model.StatisticsDAO.get_PireLivreur_by_NombreRetards() => SQL "+id_liv +" "+prenom_livreur+ " "+ nom_livreur+ " "+ TempsTotalRetards+" "+ TotalRetards);
                
                pireLivreur.setTotalRetards(TotalRetards);
                pireLivreur.setTempsTotalRetards(TempsTotalRetards);

                
                 
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_PireLivreur_by_NombreRetards error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return pireLivreur;

    }
    
     public static int get_NombreNonFacturation_Retards() {        
        int nbNonFacturationRetard = 0;

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            //id_user, Nom_user, login, motDePasse, role, solde, nb_pizzas, carteBancaire, Abonnement
            String SQL = " SELECT count(id_commande) as NbNonFacturationRetard "+
                         " FROM  Commandes C "+
                         " WHERE TIMESTAMPDIFF(MINUTE, date_commande, date_livraison ) > 30 "+
                         " AND prix = 0 ; " ;

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nbNonFacturationRetard = rs.getInt("NbNonFacturationRetard");
                             
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_NombreNonFacturation_Retards error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return nbNonFacturationRetard;

    }
     
     
      public static int get_NombreNonFacturation_Fidelite() {        
        int nbNonFacturationFidelite = 0;

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            
            String SQL = " SELECT count(id_commande) as NbNonFacturationFidelite "+
                         " FROM  Commandes C "+
                         " WHERE TIMESTAMPDIFF(MINUTE, date_commande, date_livraison ) > 30 "+
                         " AND prix = 0 ; " ;

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nbNonFacturationFidelite = rs.getInt("NbNonFacturationFidelite");
                             
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_NombreNonFacturation_Retards error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return nbNonFacturationFidelite;

    }
    
      
      public static double get_Moyenne_Prix_Commandes() {        
        double moyennePrixCommande = 0;

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            
            String SQL =" SELECT TRUNCATE(avg(prix),2) as moyennePrixCommande from commandes ; " ;

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                moyennePrixCommande = rs.getInt("moyennePrixCommande");
                             
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_Moyenne_Prix_Commandes error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return moyennePrixCommande;

    }
    

}
