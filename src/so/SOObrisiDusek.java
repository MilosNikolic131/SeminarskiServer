/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.Dimenzije;
import domen.Dusek;
import domen.Narucen;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Master
 */
public class SOObrisiDusek extends OpstaSo {

    private final DBBroker db;

    public SOObrisiDusek() {
        db = new DBBroker();
    }

    @Override
    protected boolean izvrsi(OpstiDomenskiObjekat odo) {
        Dusek d = (Dusek) odo;
        boolean flag = db.obrisi(d);
        boolean flag2 = db.obrisiSaUslov(new Dimenzije(0, d.getDusekID()));
        if (flag && flag2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean proveriUslov(OpstiDomenskiObjekat odo) {
        otvoriBazu();
        Dusek d = (Dusek) odo;
        ArrayList<Narucen> lista = (ArrayList<Narucen>) (List<?>) db.vratiListu(new Narucen());
        for (Narucen narucen : lista) {
            if (narucen.getDusek() == d.getDusekID()) {
                return false;
            }
        }
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
