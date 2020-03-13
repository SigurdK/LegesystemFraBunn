package com.sigurdscode.leger;

import com.sigurdscode.legemiddler.Legemiddel;

public class UlovligUtskrift extends Exception {
    UlovligUtskrift(Lege l, Legemiddel lm){
        super("Legen "+l.hentNavn()+ " har ikke lov til å skrive ut " + lm.hentNavn());
    }
}
