/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Dimenzije;
import domen.Dusek;
import domen.Materijal;
import domen.Narucen;
import domen.Porudzbina;
import domen.Prodavac;
import domen.Tvrdoca;
import domen.Velicina;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konst.Konstante;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author DusanIMilos
 */
public class KlijentNit extends Thread {

    Socket s;

    public KlijentNit() {
    }

    public KlijentNit(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case (Konstante.LOGIN): {
                    boolean valid = Kontroler.getInstance().login((Prodavac) kz.getParametar());
                    if (valid) {
                        so.setPoruka("OK");
                        posaljiOdgovor(so);
                    } else {
                        so.setPoruka("NOT OK");
                        posaljiOdgovor(so);
                    }
                    break;
                }
                case (Konstante.DODAJ_MATERIJAL): {
                    String naziv = (String) kz.getParametar();
                    boolean flag = Kontroler.getInstance().dodajMaterijal(naziv);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.VRATI_MATERIJAL): {
                    ArrayList<Materijal> lista = Kontroler.getInstance().vratiMaterijale();
                    so.setParametar(lista);
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.VRATI_DUSEK): {
                    ArrayList<Dusek> lista = Kontroler.getInstance().vratiDuseke();
                    so.setParametar(lista);
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.VRATI_TVRDOCE): {
                    ArrayList<Tvrdoca> lista = Kontroler.getInstance().vratiTvrdoce();
                    so.setParametar(lista);
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.VRATI_VELICINE): {
                    ArrayList<Velicina> lista = Kontroler.getInstance().vratiVelicine();
                    so.setParametar(lista);
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.SACUVAJ_DUSEK): {
                    Dusek d = (Dusek) kz.getParametar();
                    boolean flag = Kontroler.getInstance().sacuvajDusek(d);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.VRATI_ID_PORUDZBINE): {
                    int id = Kontroler.getInstance().vratiIDPorudzbine();
                    so.setParametar(id);
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.VRATI_ID_DUSEKA): {
                    int id = Kontroler.getInstance().vratiIDDuseka();
                    so.setParametar(id);
                    posaljiOdgovor(so);
                    break;
                }
                
                case (Konstante.SACUVAJ_PORUDZBINU): {
                    Porudzbina p = (Porudzbina) kz.getParametar();
                    boolean flag = Kontroler.getInstance().sacuvajPorudzbinu(p);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.SACUVAJ_STAVKU): {
                    Narucen n = (Narucen) kz.getParametar();
                    boolean flag = Kontroler.getInstance().sacuvajStavku(n);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                
                case (Konstante.SACUVAJ_DIMENZIJE): {
                    Dimenzije d = (Dimenzije) kz.getParametar();
                    boolean flag = Kontroler.getInstance().sacuvajDimenzije(d);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.IZMENI_DIMENZIJE): {
                    Dimenzije d = (Dimenzije) kz.getParametar();
                    boolean flag = Kontroler.getInstance().izmeniDimenzije(d);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.IZMENI_DUSEK): {
                    Dusek d = (Dusek) kz.getParametar();
                    boolean flag = Kontroler.getInstance().izmeniDusek(d);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.OBRISI_DUSEK): {
                    int id = (int) kz.getParametar();
                    boolean flag = Kontroler.getInstance().obrisiDusek(id);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.VRATI_PORUDZBINE): {
                    ArrayList<Porudzbina> lista = Kontroler.getInstance().vratiPorudzbine();
                    so.setParametar(lista);
                    posaljiOdgovor(so);
                    break;
                }
                case (Konstante.STORNIRAJ): {
                    Porudzbina p = (Porudzbina) kz.getParametar();
                    boolean flag = Kontroler.getInstance().stornirajPorudzbinu(p);
                    if (flag) {
                        so.setPoruka("OK");
                    } else {
                        so.setPoruka("NOT OK");
                    }
                    posaljiOdgovor(so);
                    break;
                }
            }
        }
    }

    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
