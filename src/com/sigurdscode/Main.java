package com.sigurdscode;

import com.sigurdscode.legemiddler.Narkotisk;
import com.sigurdscode.legemiddler.Vanlig;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.leger.Spesialist;
import com.sigurdscode.lenkelister.Lenkeliste;
import com.sigurdscode.lenkelister.LenkelisteTo;
import com.sigurdscode.lenkelister.Stabel;
import com.sigurdscode.pasienter.Pasient;
import com.sigurdscode.resepter.PResept;

public class Main {

    public static void main(String[] args) throws Exception {

        /**
        System.out.println("Hello World");

        Lege hans = new Lege("Hans");
        Spesialist per = new Spesialist("Per",5);
        Narkotisk heroin = new Narkotisk("Heroin",10000,5,100);
        Vanlig paracett = new Vanlig("Paracett",50,10);
        Pasient mari = new Pasient("Marihøne Mykleblomst", "123456");
        PResept presept = new PResept(heroin,hans,mari);
        mari.leggTilResept(presept);
        System.out.println(mari.taUtResept());//skriver ut resepten og fjerner det fra stabelen til mari.

        System.out.println(heroin);

        System.out.println(hans.hentNavn());
        //System.out.println(presept);
        //mari.hentReseptListe(); //Nå printer denne funksjonen ut resepten på listen til Mari

        //LISTER

        Lenkeliste<Narkotisk> minListe = new Lenkeliste<>();
        LenkelisteTo<Narkotisk> minListeTo = new LenkelisteTo<>();

        for (int i = 0; i<10;i++){
            minListe.leggTil(heroin);
        }
        for (int i = 0; i<10;i++){
            minListeTo.settInn(heroin);
        }

        System.out.println("NYLINJEUNDERHER:\n");

        /* for (Iterator it = minListe.iterator(); it.hasNext(); ) {
            Object i = it.next();
            System.out.println(i);
        }
        for (Narkotisk e : minListe){
            System.out.println(e);
        }


//NY stabel test med for each loop:

        Stabel<String> stabel = new Stabel<>();

        for (int i = 0; i<10;i++){
            stabel.leggPaa("B"+i);
        }
        for (String item : stabel) {
            System.out.println(item);
        }

        hans.skrivBlaaResept(paracett,mari,5);
        per.skrivBlaaResept(heroin,mari,4);
        hans.skrivReseptListe();
        per.skrivReseptListe();
***/
        Legesystemet system = new Legesystemet();
        system.leseFraFil("InformasjonStor.txt");
        system.skrivUtPasienter();
        system.skrivUtLegemiddler();
        system.skrivUtLeger();


    }
}
