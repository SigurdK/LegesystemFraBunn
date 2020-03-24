package com.sigurdscode.resepter;

import com.sigurdscode.legemiddler.Legemiddel;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.pasienter.Pasient;

public abstract class Resept {

        //har en static variabel Idcounter, som setter id for objektet som opprettes.
        //tar også inn en id for pasienten.
        protected int Id;
        private static int IdCounter;
        protected int reit;
        protected Legemiddel legemiddel;
        protected Lege utskrivendeLege;
        protected Pasient pasient;

        public Resept (Legemiddel lM, Lege uL, Pasient p, int r){

            legemiddel = lM;
            utskrivendeLege = uL;
            pasient = p;
            reit = r;
            Id = IdCounter++;
        }
        public int hentId(){
            return Id;
        }
        public Legemiddel hentLegemiddel(){
            return legemiddel;
        }
        public Lege hentUtsrivendeLege(){
            return utskrivendeLege;
        }
        public Pasient hentPasient(){
            return pasient;
        }
        public int hentReit(){
            return reit;
        }
        //sjekker om resepten kan brukes, dersom det går så synker reit med 1, eller returerers false.
        public boolean bruk(){
            if (reit == 0){
                return false;
            }else {
                reit --;
                return true;
            }
        }

        public void skrivReseptLiten(){
            System.out.println(Id +": "+legemiddel.hentNavn()+"(reit: "+reit+")");
        }
        //har noen funksjoner som skal implementeres i alle sub-klassene.
        abstract public String farge();
        abstract public double prisAaBetale();
        abstract public String printTilFil();
        abstract public String toString();
}

