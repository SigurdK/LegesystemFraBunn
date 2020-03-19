package com.sigurdscode.pasienter;

import com.sigurdscode.lenkelister.Stabel;
import com.sigurdscode.resepter.Resept;

public class Pasient {

    protected String fodselsnummer;
    protected int ID;
    private static int IdCounter = 0;
    protected String navn;
    Stabel<Resept> stabel = new Stabel<Resept>();

    public Pasient(String n, String FN){
        navn = n;
        fodselsnummer = FN;
        ID = IdCounter++;
    }

    public String hentNavn() {
        return navn;
    }
    public int hentID() {
        return ID;
    }

    public void leggTilResept(Resept r){
            stabel.leggPaa(r);
    }
    public Resept taUtResept(){
        return stabel.taAv();
    }
    public  Stabel<Resept> hentResepterTilPasient(){
        return stabel;
    }
    public void hentReseptListe(){
        stabel.print();
    }
    public int antallResepter(){
        return stabel.stoerrelse();
    }
    public void skrivUtPasientLiten(){
        System.out.println(ID +": "+navn+" (fdr: "+fodselsnummer+")"+stabel.stoerrelse());
    }
    public void skrivUtResepterLiten(){
        for (Resept resept : stabel){
            resept.skrivReseptLiten();
        }
    }
    public String toString(){
        return "\nPasientens navn: "+navn+"\nPasientens fodselsnummer: "+fodselsnummer+"\nPasientens ID: "+ ID+"\nAntall Resepter: "+ this.antallResepter();
    }
}
