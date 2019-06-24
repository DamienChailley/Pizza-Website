/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DataConnect;
import entityServer.Ingredient;
import entityServer.Pizza;
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
public class PizzaDAO {

    public static Pizza get_pizza_by_id(int idPizza) {
        Pizza unePizza = new Pizza();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();

            String SQL = " SELECT `pizza`.`id_pizza`, "
                    + " `pizza`.`Nom_pizza`, "
                    + " `pizza`.`Prix`, "
                    + " `pizza`.`image` "
                    + " FROM `pizza`  "
                    + " WHERE id_pizza = '" + idPizza + "' ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_pizza = rs.getInt("id_pizza");
                String Nom_pizza = rs.getString("Nom_pizza");
                double Prix = rs.getDouble("Prix");
                String image = rs.getString("image");
                unePizza = new Pizza(id_pizza, Nom_pizza, Prix, image);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_pizza_by_id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unePizza;

    }
    
    public static List<Pizza> get_all_pizza() {
        List<Pizza> lesPizzas = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();

            String SQL = " SELECT `pizza`.`id_pizza`, "
                    + " `pizza`.`Nom_pizza`, "
                    + " `pizza`.`Prix`, "
                    + " `pizza`.`image` "
                    + " FROM `pizza`  ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_pizza = rs.getInt("id_pizza");
                String Nom_pizza = rs.getString("Nom_pizza");
                double Prix = rs.getDouble("Prix");
                String image = rs.getString("image");
                Pizza unePizza = new Pizza(id_pizza, Nom_pizza, Prix, image);
                lesPizzas.add(unePizza);
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_all_pizza error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesPizzas;

    }
    
     public static String get_Pizza_ingredient_by_Pizza_Id(int id_pizza) {
        String lesIngredients = "";

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
                //id_ing, nom_Ingre
            String SQL = " SELECT p.id_pizza, nom_pizza, prix, image, nom_Ingre "+
                         " FROM Pizza p "+
                         " INNER JOIN Ingredients_pizza ip ON ip.id_pizza = p.id_pizza "+
                         " INNER JOIN ingredients i ON i.id_ing = ip.id_ing "+
                         " WHERE p.id_pizza = "+id_pizza+" ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                String nom_Ingre = rs.getString("nom_Ingre");  
                if(i == 0)
                {
                    lesIngredients = lesIngredients+" "+nom_Ingre;
                }
                else{
                     lesIngredients = lesIngredients+", "+nom_Ingre;
                }
               i++;
            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_Pizza_ingredient_by_Pizza_Id error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return lesIngredients;

    }
     
         
      public static Pizza get_best_pizza() {
        Pizza unePizza = new Pizza();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();

            String SQL = " SELECT p.id_pizza, nom_pizza, p.prix, image, count(id_commande) as nbCommande "+
                        " FROM Commandes c "+
                        " INNER JOIN Pizza P on P.id_pizza = C.id_pizza "+
                        " GROUP BY (C.id_pizza) "+
                        " ORDER BY nbCommande DESC "+
                        " limit 1 ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_pizza = rs.getInt("id_pizza");
                String Nom_pizza = rs.getString("Nom_pizza");
                double Prix = rs.getDouble("Prix");
                String image = rs.getString("image");
                unePizza = new Pizza(id_pizza, Nom_pizza, Prix, image);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_best_pizza error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unePizza;

    }
      
      public static Pizza get_Pire_Pizza() {
        Pizza unePizza = new Pizza();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();

            String SQL = " SELECT p.id_pizza, nom_pizza, p.prix, image, count(id_commande) as nbCommande "+
                        " FROM Commandes c "+
                        " INNER JOIN Pizza P on P.id_pizza = C.id_pizza "+
                        " GROUP BY (C.id_pizza) "+
                        " ORDER BY nbCommande "+
                        " limit 1 ; ";

            ps = con.prepareStatement(
                    SQL
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_pizza = rs.getInt("id_pizza");
                String Nom_pizza = rs.getString("Nom_pizza");
                double Prix = rs.getDouble("Prix");
                String image = rs.getString("image");
                unePizza = new Pizza(id_pizza, Nom_pizza, Prix, image);

            }

        } catch (SQLException ex) {
            System.out.println("Exception e get_Pire_Pizza error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return unePizza;

    }
     
}
