/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Tools.DateConversion;
import database.DataConnect;
import entityServer.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CommandeDAO {
    public static Commande get_commande_by_id(int id_commande) {
        Commande uneCommande = new Commande();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_commande, date_commande, date_Livraison, prix, id_user, id_pizza, id_liv
            String SQL = " SELECT id_commande, "
                    + " date_commande, "
                    + " date_Livraison, "
                    + " taille_Pizza, "
                    + " prix, "
                    + " id_user, "
                    + " id_pizza, "
                    + " id_liv "
                    + " FROM `commandes`  "
                    + " WHERE id_commande = '" + id_commande + "' ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_commande = rs.getInt("id_commande");
                Date date_commande = DateConversion.get_date_from_string_yyyyMMdd_HHmmss_SQL(rs.getString("date_commande"));
                Date date_Livraison = DateConversion.get_date_from_string_yyyyMMdd_HHmmss_SQL(rs.getString("date_Livraison"));
                String taille_Pizza = rs.getString("taille_Pizza");
                double prix = rs.getDouble("prix");
                int id_user = rs.getInt("id_user");
                int id_pizza = rs.getInt("id_pizza");                
                int id_liv = rs.getInt("id_liv");            
                
                uneCommande = new Commande(id_commande, date_commande, date_Livraison, taille_Pizza, prix, id_user, id_pizza, id_liv);

            }

        } catch (SQLException ex) {
            System.out.println("Exception COMMANDEDAO get_commande_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return uneCommande;

    }
    
    public static List<Commande> get_all_commandes() {
        List<Commande> lesCommandes = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_commande, date_commande, date_Livraison, prix, id_user, id_pizza, id_liv
            String SQL = " SELECT id_commande, "
                    + " date_commande, "
                    + " date_Livraison, "
                    + " taille_Pizza, "
                    + " prix, "
                    + " id_user, "
                    + " id_pizza, "
                    + " id_liv "
                    + " FROM `commandes`  "
                    + " ORDER BY date_commande DESC ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_commande = rs.getInt("id_commande");
                Date date_commande = Tools.DateConversion.get_date_from_string_yyyyMMdd_HHmmss_SQL(rs.getString("date_commande"));
                Date date_Livraison = Tools.DateConversion.get_date_from_string_yyyyMMdd_HHmmss_SQL(rs.getString("date_Livraison"));
                String taille_Pizza = rs.getString("taille_Pizza");
                double prix = rs.getDouble("prix");
                int id_user = rs.getInt("id_user");
                int id_pizza = rs.getInt("id_pizza");                
                int id_liv = rs.getInt("id_liv");            
                
                Commande uneCommande = new Commande(id_commande, date_commande, date_Livraison,taille_Pizza, prix, id_user, id_pizza, id_liv);
                lesCommandes.add(uneCommande);

            }

        } catch (SQLException ex) {
            System.out.println("Exception COMMANDEDAO get_all_commandes error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesCommandes;
    }

    public static Commande insert_commande(Date date_commande, Date date_Livraison, String taille_Pizza, double prix, int id_client, int id_pizza, int id_liv) {
        Commande uneCommande = new Commande();
        
        String date_commande_SQL = Tools.DateConversion.get_date_to_simple_format_yyyyMMdd_HHmmss(date_commande);
        System.out.println("model.CommandeDAO.insert_commande()" + date_commande_SQL);
       
        String date_Livraison_SQL = Tools.DateConversion.get_date_to_simple_format_yyyyMMdd_HHmmss(date_Livraison);
         System.out.println("model.CommandeDAO.insert_commande()" + date_Livraison_SQL);
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_commande, date_commande, date_Livraison, prix, id_user, id_pizza, id_liv
            String SQL = "INSERT INTO commandes(date_commande, date_Livraison, taille_Pizza, prix, id_user, id_pizza, id_liv) "
                    + " SELECT '"+date_commande_SQL+"' , "
                    + " '"+date_Livraison_SQL+"', "
                    + " '"+taille_Pizza+"', "                    
                    + " '"+prix+"', "
                    + " '"+id_client+"', "
                    + " '"+id_pizza+"', "
                    + " '"+id_liv+"' ";

            ps = con.prepareStatement(
                    SQL, Statement.RETURN_GENERATED_KEYS
                    
            );
            
            ps.executeUpdate();
            
            System.out.println("model.CommandeDAO.insert_commande() : SQL " + SQL);
            
            ResultSet rs = ps.getGeneratedKeys();
            int commande_id = 0;
            if(rs.next())
            {
               commande_id = rs.getInt(1);
               uneCommande = get_commande_by_id(commande_id);
            }        
                             
        } catch (SQLException ex) {
            System.out.println("Exception COMMANDEDAO insert_commande error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return uneCommande;

    }

  public static List<Commande> get_all_commands_by_id_user(int id_user) {
        List<Commande> lesCommandes = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_commande, date_commande, date_Livraison, prix, id_user, id_pizza, id_liv
            String SQL = " SELECT id_commande, "
                    + " date_commande, "
                    + " date_Livraison, "
                    + " taille_Pizza, "
                    + " prix, "
                    + " id_user, "
                    + " id_pizza, "
                    + " id_liv "
                    + " FROM `commandes`  "
                    + " WHERE id_user = " + id_user + " "
                    + " ORDER BY date_commande DESC ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_commande = rs.getInt("id_commande");
                Date date_commande = Tools.DateConversion.get_date_from_string_yyyyMMdd_HHmmss_SQL(rs.getString("date_commande"));
                Date date_Livraison = Tools.DateConversion.get_date_from_string_yyyyMMdd_HHmmss_SQL(rs.getString("date_Livraison"));
                String taille_Pizza = rs.getString("taille_Pizza");
                double prix = rs.getDouble("prix");
                id_user = rs.getInt("id_user");
                int id_pizza = rs.getInt("id_pizza");                
                int id_liv = rs.getInt("id_liv");            
                
                Commande uneCommande = new Commande(id_commande, date_commande, date_Livraison,taille_Pizza, prix, id_user, id_pizza, id_liv);
                lesCommandes.add(uneCommande);

            }

        } catch (SQLException ex) {
            System.out.println("Exception COMMANDEDAO get_all_commandes error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesCommandes;
    }
    
    
}
