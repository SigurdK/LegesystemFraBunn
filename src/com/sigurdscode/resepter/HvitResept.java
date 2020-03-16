package com.sigurdscode.resepter;

import com.sigurdscode.legemiddler.Legemiddel;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.pasienter.Pasient;

public class HvitResept extends Resept{
    public HvitResept(Legemiddel legeMiddel, Lege utskrevenLege, Pasient pasient, int reit){
        super(legeMiddel,  utskrevenLege,  pasient,  reit);
    }
    public String farge(){
        return "Hvit";
    }
    public double prisAaBetale(){
        return legemiddel.hentPris();
    }
    public String toString(){

        return "\nResepten er "+this.farge() +" og gjelder legemiddelet: \n"+legemiddel+"\n\npris per legemiddel med denne resepten: "+this.prisAaBetale()+"\nUtskrivende " +utskrivendeLege.typeLege()+" er: "+utskrivendeLege.hentNavn()+"\nPasientens ID: "+pasient+"\nAntall ganger igjen(reit): "+reit+"\nReseptens ID: "+Id+"\n";
    }
}
