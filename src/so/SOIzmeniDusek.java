/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.Dusek;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Master
 */
public class SOIzmeniDusek extends OpstaSo {

    private final DBBroker db;

    public SOIzmeniDusek() {
        db = new DBBroker();
    }

    @Override
    protected boolean izvrsi(OpstiDomenskiObjekat odo) {
        Dusek d = (Dusek) odo;
        boolean flag = db.izmeni(d);
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
