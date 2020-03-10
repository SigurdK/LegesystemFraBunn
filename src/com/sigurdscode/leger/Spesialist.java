package com.sigurdscode.leger;

public class Spesialist extends Lege implements Godkjenningsfritak{
    private int kontrollId;

    public Spesialist(String navn, int kI){
        super(navn);
        kontrollId = kI;
    }
    @Override
    public int hentKontrollId(){
        return kontrollId;
    }
    public String typeLege(){
        return "Spesialist";
    }
    public String toString(){
        return "\nSpesialistens navn: "+this.hentNavn()+"\nKontrollId: " + this.hentKontrollId();
    }
}
