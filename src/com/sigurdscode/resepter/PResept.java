package com.sigurdscode.resepter;

import com.sigurdscode.legemiddler.Legemiddel;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.pasienter.Pasient;

public class PResept extends Resept{
    private double pris;

    public PResept(Legemiddel legeMiddel, Lege utskrevenLege, Pasient pasient){
        super(legeMiddel,  utskrevenLege,  pasient,  3);
    }
    public String farge(){
        return "Hvit";
    }
    public double prisAaBetale(){
        pris = legemiddel.hentPris()-108;
        if (pris > 0){
            return pris;
        }else {return 0;}
    }
    @Override
    public String printTilFil(){
        return Id+","+utskrivendeLege.hentNavn()+","+pasient.hentNavn()+","+"p,";

    }
    public String toString(){
        return "\nResepten er "+this.farge() +" og gjelder legemiddelet: \n"+legemiddel+"\n\npris per legemiddel med denne resepten: "+this.prisAaBetale()+ "\nUtskrivende " + utskrivendeLege.typeLege()+ " er: "+utskrivendeLege.hentNavn()+"\nPasientens Navn: "+pasient.hentNavn()+"\nPasientens ID: "+pasient.hentID()+"\nAntall ganger igjen(reit): "+reit+"\nReseptens ID: "+Id+"\n";
    }
}
