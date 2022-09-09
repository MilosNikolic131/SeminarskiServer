/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Prodavac;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Master
 */
public class SOLogin extends OpstaSo {

    private final DBBroker db;

    public SOLogin() {
        db = new DBBroker();
    }

    @Override
    protected boolean izvrsi(OpstiDomenskiObjekat odo) {
        Prodavac prodavac = (Prodavac) odo;
        ArrayList<Prodavac> lista = (ArrayList<Prodavac>) (List<?>) db.vratiListu(prodavac);
        for (Prodavac p : lista) {
            if (p.getKorisnickoIme().equals(prodavac.getKorisnickoIme()) && p.getLozinka().equals(prodavac.getLozinka())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean proveriUslov(OpstiDomenskiObjekat odo) {
        otvoriBazu();
        System.out.println("izvrseno !!!");
        return true;
    }

    @Override
    protected void otvoriBazu() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
    }

    @Override
    protected void potvrdi() {
        db.commit();
        db.zatvoriKonekciju();
    }

    @Override
    protected void ponisti() {
        db.rollback();
        db.zatvoriKonekciju();
    }

    
}
