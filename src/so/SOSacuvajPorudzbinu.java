/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Porudzbina;

/**
 *
 * @author Master
 */
public class SOSacuvajPorudzbinu extends OpstaSo {

    private final DBBroker db;

    public SOSacuvajPorudzbinu() {
        db = new DBBroker();
    }

    @Override
    protected boolean izvrsi(OpstiDomenskiObjekat odo) {
        Porudzbina p = (Porudzbina) odo;
        boolean flag = db.sacuvaj(p);
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean proveriUslov(OpstiDomenskiObjekat odo) {
        otvoriBazu();
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
