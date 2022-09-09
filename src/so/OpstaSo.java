/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Master
 */
public abstract class OpstaSo {

    public boolean izvrsiOpstuSO(OpstiDomenskiObjekat odo) {
        boolean signal = proveriUslov(odo);
        if (signal) {
            boolean signal2 = izvrsi(odo);
            if (signal2) {
                potvrdi();
                return true;
            } else {
                ponisti();
                return false;
            }
        } else {
            return false;
        }
    }

    protected abstract boolean izvrsi(OpstiDomenskiObjekat odo);

    protected abstract boolean proveriUslov(OpstiDomenskiObjekat odo);

    protected void potvrdi() {

    }

    protected void ponisti() {

    }

    protected void otvoriBazu() {

    }
}
