/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DusanIMilos
 */
public class DBBroker {

    Connection konekcija;

    public void ucitajDrajver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarski", "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) {

        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + odo.vratiNazivTabele();
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                OpstiDomenskiObjekat odo1 = odo.napuni(rs);
                System.out.println(odo1);
                lista.add(odo1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean sacuvaj(OpstiDomenskiObjekat odo) {

        try {
            String sql = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES (" + odo.vratiVrednostiAtributa() + ")";
            Statement st = konekcija.createStatement();
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int vratiID(OpstiDomenskiObjekat odo) {
        int max = 0;
        String sql = "SELECT max(" + odo.vratiNazivID() + ")" + " as max from " + odo.vratiNazivTabele();
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                max = rs.getInt("max");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++max;
    }

    public boolean obrisi(OpstiDomenskiObjekat odo) {
        String sql = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiNazivID() + " = " + odo.vratiID();
        try {
            PreparedStatement ps = konekcija.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean izmeni(OpstiDomenskiObjekat odo) {

        String sql = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiNaziveAtributa() + " WHERE " + odo.vratiUslovZaUpdate();
        System.out.println(sql);
        try {
            PreparedStatement ps = konekcija.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean obrisiSaUslov(OpstiDomenskiObjekat odo) {
        String sql = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiUslovZaUpdate();
        try {
            PreparedStatement ps = konekcija.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
