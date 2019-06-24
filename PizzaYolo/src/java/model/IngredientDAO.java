/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DataConnect;
import entityServer.Ingredient;
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
public class IngredientDAO {
    public static Ingredient get_ingredient_by_id(int id_ing) {
        Ingredient unIngredient = new Ingredient();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_ing, nom_Ingre
            String SQL = " SELECT id_ing, "
                    + " nom_Ingre "
                    + " FROM `Ingrédients`  "
                    + " WHERE id_ing = '" + id_ing + "' ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 id_ing = rs.getInt("id_ing");
                String nom_Ingre = rs.getString("nom_Ingre");                  
                
                unIngredient = new Ingredient(id_ing, nom_Ingre);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_ingredient_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unIngredient;

    }
    
    
    public static List<Ingredient> get_all_ingredient() {
        List<Ingredient> lesIngredient = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_ing, nom_Ingre
            String SQL = " SELECT id_ing, "
                    + " nom_Ingre "
                    + " FROM `Ingrédients`  ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_ing = rs.getInt("id_ing");
                String nom_Ingre = rs.getString("nom_Ingre");                  
                
                Ingredient unIngredient = new Ingredient(id_ing, nom_Ingre);
                lesIngredient.add(unIngredient);
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_ingredient_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesIngredient;

    }
    
   
}
