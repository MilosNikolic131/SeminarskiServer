/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.Dusek;
import domen.Materijal;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Master
 */
public class SODodajMaterijal extends OpstaSo {

    private final DBBroker db;

    public SODodajMaterijal() {
        db = new DBBroker();
    }

    @Override
    protected boolean izvrsi(OpstiDomenskiObjekat odo) {
        int id = db.vratiID(new Materijal());
        Materijal mat = (Materijal) odo;
        Materijal m = new Materijal(id, mat.getNazivMaterijala());
        boolean flag = db.sacuvaj(m);
        System.out.println(flag + " " + " bla bla");
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean proveriUslov(OpstiDomenskiObjekat odo) {
        otvoriBazu();
        Materijal mat = (Materijal) odo;
        ArrayList<Materijal> lista = (ArrayList<Materijal>) (List<?>) db.vratiListu(new Materijal());
        System.out.println(lista);
        for (Materijal materijal : lista) {
            if (materijal.getNazivMaterijala().equals(mat.getNazivMaterijala())) {
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
