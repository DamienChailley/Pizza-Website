/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DataConnect;
import entityServer.Livreur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yan20
 */
public class LivreurDAO {
     public static Livreur get_livreur_by_id(int id_livreur) {
        Livreur unLivreur = new Livreur();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_liv, nom_livreur, prenom_livreur, 
           String SQL = " SELECT id_liv, "
                    + " nom_livreur, "
                    + " prenom_livreur "
                    + " FROM `livreur`  "
                    + " WHERE id_liv = '" + id_livreur + "' ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 id_livreur = rs.getInt("id_liv");
                String nom_livreur = rs.getString("nom_livreur");
                String prenom_livreur = rs.getString("prenom_livreur");
                
                unLivreur = new Livreur(id_livreur, nom_livreur, prenom_livreur);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_livreur_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unLivreur;

    }
     
     public static List<Livreur> get_all_livreur() {
        List<Livreur> lesLivreur = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_liv, nom_livreur, prenom_livreur, 
           String SQL = " SELECT id_liv, "
                    + " nom_livreur, "
                    + " prenom_livreur "
                    + " FROM `livreur` ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_livreur = rs.getInt("id_liv");
                String nom_livreur = rs.getString("nom_livreur");
                String prenom_livreur = rs.getString("prenom_livreur");
                
                Livreur unLivreur = new Livreur(id_livreur, nom_livreur, prenom_livreur);
                lesLivreur.add(unLivreur);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_all_livreur error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesLivreur;

    }
}
