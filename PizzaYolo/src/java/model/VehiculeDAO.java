/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DataConnect;
import entityServer.Vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDAO {
    public static Vehicule get_vehicule_by_id(int id_vehi) {
        Vehicule unVehicule = new Vehicule();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                
           String SQL = " SELECT id_vehi, "
                    + " type_vehi, "
                    + " nom_vehi "
                    + " FROM `VEHICULE`  "
                    + " WHERE id_vehi = '" + id_vehi + "' ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 id_vehi = rs.getInt("id_vehi");
                String type_vehi = rs.getString("type_vehi");
                String nom_vehi = rs.getString("nom_vehi");
                
                unVehicule = new Vehicule(id_vehi, type_vehi, nom_vehi);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_vehicule_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unVehicule;

    }
     
     public static List<Vehicule> get_all_vehicules() {
        List<Vehicule> lesVehicules = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
           String SQL = " SELECT id_vehi, "
                    + " type_vehi, "
                    + " nom_vehi "
                    + " FROM `VEHICULE` ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_vehi = rs.getInt("id_vehi");
                String type_vehi = rs.getString("type_vehi");
                String nom_vehi = rs.getString("nom_vehi");
                
                Vehicule unVehicule = new Vehicule(id_vehi, type_vehi, nom_vehi);
                lesVehicules.add(unVehicule);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_all_vehicules error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesVehicules;

    }
     
     public static List<Vehicule> get_all_vehicules_non_Utilises() {
        List<Vehicule> lesVehicules = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
           String SQL = " SELECT id_vehi, "
                    + " type_vehi, "
                    + " nom_vehi "
                    + " FROM `VEHICULE` "
                    + " WHERE id_vehi NOT IN ( SELECT DISTINCT id_vehi FROM livraisons ) ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_vehi = rs.getInt("id_vehi");
                String type_vehi = rs.getString("type_vehi");
                String nom_vehi = rs.getString("nom_vehi");
                
                Vehicule unVehicule = new Vehicule(id_vehi, type_vehi, nom_vehi);
                lesVehicules.add(unVehicule);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_all_vehicules error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesVehicules;

    }
     
     
}
