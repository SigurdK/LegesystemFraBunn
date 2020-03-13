package com.sigurdscode.legemiddler;

public class Vanedannende extends Legemiddel{

    protected int styrke;

    public Vanedannende(String navn, double pris, double virkestoff, int s){
        super(navn,pris,virkestoff);
        styrke = s;
    }
    public int hentVanedannendeStyrke(){
        return styrke;
    }
    public String toString(){
        return "\nLEGEMIDDEL\nType: Vanedannende"+"\nNavn: "+navn+"\nID: "+Id+"\nPris: "+pris+"\nVirkestoff (mg): "+virkestoff+"\nStyrke: "+styrke;
    }
}
