/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Dimenzije;
import domen.Dusek;
import domen.Materijal;
import domen.Narucen;
import domen.Porudzbina;
import domen.Prodavac;
import domen.Tvrdoca;
import domen.Velicina;
import java.util.ArrayList;
import java.util.List;
import so.SODodajMaterijal;
import so.SOIzmeniDimenzije;
import so.SOIzmeniDusek;
import so.SOLogin;
import so.SOObrisiDusek;
import so.SOSacuvajDimenzije;
import so.SOSacuvajDusek;
import so.SOSacuvajPorudzbinu;
import so.SOSacuvajStavku;
import so.SOStornirajPorudzinu;

/**
 *
 * @author DusanIMilos
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public boolean login(Prodavac prodavac) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        ArrayList<Prodavac> lista = (ArrayList<Prodavac>) (List<?>) db.vratiListu(prodavac);
//        for (Prodavac p : lista) {
//            if (p.getKorisnickoIme().equals(prodavac.getKorisnickoIme()) && p.getLozinka().equals(prodavac.getLozinka())) {
//                db.zatvoriKonekciju();
//                return true;
//            }
//        }
//        db.zatvoriKonekciju();
//        return false;
        SOLogin soLogin = new SOLogin();
        return soLogin.izvrsiOpstuSO(prodavac);
    }

    public boolean dodajMaterijal(String naziv) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean uslov = proveriUslovZaMaterijal(naziv);
//        if (!uslov) {
//            return false;
//        }
//        int id = db.vratiID(new Materijal());
//        Materijal m = new Materijal(id, naziv);
//        boolean flag = db.sacuvaj(m);
//        if (flag) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SODodajMaterijal SoDodajMaterijal = new SODodajMaterijal();
        Materijal m = new Materijal(0, naziv);
        return SoDodajMaterijal.izvrsiOpstuSO(m);
    }

    public ArrayList<Materijal> vratiMaterijale() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Materijal> lista = (ArrayList<Materijal>) (List<?>) db.vratiListu(new Materijal());
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Tvrdoca> vratiTvrdoce() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Tvrdoca> lista = (ArrayList<Tvrdoca>) (List<?>) db.vratiListu(new Tvrdoca());
        db.zatvoriKonekciju();
        return lista;
    }

    public boolean sacuvajDusek(Dusek d) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean uslov = proveriUslovZaDusek(d);
//        if (!uslov) {
//            return false;
//        }
//        boolean flag = db.sacuvaj(d);
//        if (flag) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SOSacuvajDusek soSacuvajDusek = new SOSacuvajDusek();
        return soSacuvajDusek.izvrsiOpstuSO(d);
    }

    public ArrayList<Dusek> vratiDuseke() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Dusek> lista = (ArrayList<Dusek>) (List<?>) db.vratiListu(new Dusek());
        db.zatvoriKonekciju();
        return lista;
    }

    public int vratiIDPorudzbine() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        int id = db.vratiID(new Porudzbina());
        db.zatvoriKonekciju();
        return id;
    }

    public boolean sacuvajPorudzbinu(Porudzbina p) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean flag = db.sacuvaj(p);
//        if (flag) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SOSacuvajPorudzbinu soSacuvajPorudzbinu = new SOSacuvajPorudzbinu();
        return soSacuvajPorudzbinu.izvrsiOpstuSO(p);
    }

    public boolean sacuvajStavku(Narucen n) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean flag = db.sacuvaj(n);
//        if (flag) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SOSacuvajStavku soSacuvajStavku = new SOSacuvajStavku();
        return soSacuvajStavku.izvrsiOpstuSO(n);
    }

    public boolean obrisiDusek(int id) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean uslov = uslovDusekBrisanje(new Dusek(id, "", 0, 0, 0));
//        if (!uslov) {
//            return false;
//        }
//        boolean flag = db.obrisi(new Dusek(id, "", 0, 0, 0));
//        boolean flag2 = db.obrisiSaUslov(new Dimenzije(0, id));
//        if (flag && flag2) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        Dusek d = new Dusek(id, "", 0, 0, 0);
        SOObrisiDusek soObrisiDusek = new SOObrisiDusek();
        return soObrisiDusek.izvrsiOpstuSO(d);
    }

    public ArrayList<Velicina> vratiVelicine() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Velicina> lista = (ArrayList<Velicina>) (List<?>) db.vratiListu(new Velicina());
        db.zatvoriKonekciju();
        return lista;
    }

    public int vratiIDDuseka() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        int id = db.vratiID(new Dusek());
        db.zatvoriKonekciju();
        return id;
    }

    public boolean sacuvajDimenzije(Dimenzije d) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean flag = db.sacuvaj(d);
//        if (flag) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SOSacuvajDimenzije soSacuvajDimenzije = new SOSacuvajDimenzije();
        return soSacuvajDimenzije.izvrsiOpstuSO(d);
    }

    public boolean izmeniDimenzije(Dimenzije d) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean flag = db.izmeni(d);
//        if (flag) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SOIzmeniDimenzije soIzmeniDimenzije = new SOIzmeniDimenzije();
        return soIzmeniDimenzije.izvrsiOpstuSO(d);
    }

    public boolean izmeniDusek(Dusek d) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean flag = db.izmeni(d);
//        if (flag) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SOIzmeniDusek soIzmeniDusek = new SOIzmeniDusek();
        return soIzmeniDusek.izvrsiOpstuSO(d);
    }

    public ArrayList<Porudzbina> vratiPorudzbine() {
        db.ucitajDrajver();
        db.otvoriKonekciju();
        ArrayList<Porudzbina> lista = (ArrayList<Porudzbina>) (List<?>) db.vratiListu(new Porudzbina());
        db.zatvoriKonekciju();
        return lista;
    }

    public boolean stornirajPorudzbinu(Porudzbina p) {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        boolean flag = db.obrisi(p);
//        boolean flag2 = db.obrisiSaUslov(new Narucen(0, 0, p.getPorudzbinaID()));
//        if (flag && flag2) {
//            db.commit();
//            db.zatvoriKonekciju();
//            return true;
//        } else {
//            db.rollback();
//            db.zatvoriKonekciju();
//            return false;
//        }
        SOStornirajPorudzinu soStornirajPorudzinu = new SOStornirajPorudzinu();
        return soStornirajPorudzinu.izvrsiOpstuSO(p);
    }

    public DBBroker getDb() {
        return db;
    }

    public void setDb(DBBroker db) {
        this.db = db;
    }

//    private boolean proveriUslovZaMaterijal(String naziv) {
//        db.zatvoriKonekciju();
//        ArrayList<Materijal> lista = vratiMaterijale();
//        db.otvoriKonekciju();
//        for (Materijal materijal : lista) {
//            if (materijal.getNazivMaterijala().equals(naziv)) {
//                return false;
//            }
//        }
//        return true;
//    }
//    private boolean proveriUslovZaDusek(Dusek d) {
//        db.zatvoriKonekciju();
//        ArrayList<Dusek> lista = vratiDuseke();
//        db.otvoriKonekciju();
//        for (Dusek dusek : lista) {
//            if (dusek.getNazivDuseka().equals(d.getNazivDuseka())) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean uslovDusekBrisanje(Dusek d) {
//        db.zatvoriKonekciju();
//        ArrayList<Narucen> lista = vratiNarucen();
//        db.otvoriKonekciju();
//        for (Narucen narucen : lista) {
//            if (narucen.getDusek() == d.getDusekID()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private ArrayList<Narucen> vratiNarucen() {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        ArrayList<Narucen> lista = (ArrayList<Narucen>) (List<?>) db.vratiListu(new Narucen());
//        db.zatvoriKonekciju();
//        return lista;
//    }
    

}
