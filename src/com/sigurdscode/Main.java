package com.sigurdscode;

import com.sigurdscode.legemiddler.Narkotisk;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.leger.Spesialist;
import com.sigurdscode.pasienter.Pasient;
import com.sigurdscode.resepter.PResept;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Lege hans = new Lege("Hans");
        Spesialist per = new Spesialist("Per",5);
        Narkotisk heroin = new Narkotisk("Heroin",10000,5,100);
        Pasient mari = new Pasient("Mari Mykleblomst", "123456");
        PResept presept = new PResept(heroin,hans,mari);
        mari.leggTilResept(presept);
        mari.leggTilResept(presept);
        System.out.println(mari.taUtResept());//skriver ut resepten og fjerner det fra stabelen til mari.


        System.out.println(heroin);

        System.out.println(hans.hentNavn());
        //System.out.println(presept);
        //mari.hentReseptListe(); //Nå printer denne funksjonen ut resepten på listen til Mari
    }
}
