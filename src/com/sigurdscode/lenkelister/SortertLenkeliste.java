package com.sigurdscode.lenkelister;

public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

    public SortertLenkeliste(){
        super();
    }

    @Override//Overrider da jeg ønsker å legge til i kun sortert rekkefølge når vi snakker om SortertLenkeListe.
    public void leggTil(T x){

        if (stoerrelse()==0){ //legger til i listen dersom storrlsen til listen er 0.
            super.leggTil(x);
            return;
        }
        for(int i = 0; i < stoerrelse(); i++){ //sjekker om noe er større, såfall, legge inn element foran.
            if(hent(i).compareTo(x) > 0){
                super.leggTil(i,x); //må også ha super her da jeg overrider funksjonen under, men ønsker å bruke superklassens versjon.
                return;
            }
        }
        super.leggTil(x);//hvis elementet er størst, så legg til bakerst.
    }

    @Override //gjør om slik at SortertLenkeliste fjerner som en stabel og ikke kø.
    public T fjern(){
        T temp = fjern(stoerrelse()-1);
        return temp;
    }

    @Override //kaster dersom en prøver å sette inn et nytt objekt
    public void sett(int pos,T x){
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x){
        throw new UnsupportedOperationException();
    }
}
