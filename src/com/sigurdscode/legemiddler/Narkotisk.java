package com.sigurdscode.legemiddler;

public class Narkotisk extends Legemiddel {
    protected int styrke;

    public Narkotisk(String navn, double pris, double virkestoff, int s){
        super(navn,pris,virkestoff);
        styrke = s;
    }
    public int hentNarkotiskStyrke(){
        return styrke;
    }
    @Override
    public String printTilFil(){
        return navn+",narkotisk"+","+pris+","+virkestoff+","+styrke;
    }
    @Override
    public String toString(){
        return "\nLEGEMIDDEL\nType: Narkotisk"+"\nNavn: "+navn+"\nID: "+Id+"\nPris: "+pris+"\nVirkestoff (mg): "+virkestoff+"\nStyrke: "+styrke;
    }
}
