package com.sigurdscode.legemiddler;

public class Vanlig extends Legemiddel{
    public Vanlig(String navn, double pris, double virkestoff){
        super(navn,pris,virkestoff);
    }
    public String toString(){
        return "\nLEGEMIDDEL\nType: Vanlig"+"\nNavn: "+navn+"\nID: "+Id+"\nPris: "+pris+"\nVirkestoff (mg): "+virkestoff;
    }
}
