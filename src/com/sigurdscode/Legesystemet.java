package com.sigurdscode;

import com.sigurdscode.exceptions.UgyldigLegemiddelException;
import com.sigurdscode.legemiddler.Legemiddel;
import com.sigurdscode.legemiddler.Narkotisk;
import com.sigurdscode.legemiddler.Vanedannende;
import com.sigurdscode.legemiddler.Vanlig;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.leger.Spesialist;
import com.sigurdscode.leger.UlovligUtskrift;
import com.sigurdscode.lenkelister.Lenkeliste;
import com.sigurdscode.lenkelister.SortertLenkeliste;
import com.sigurdscode.lenkelister.Stabel;
import com.sigurdscode.pasienter.Pasient;
import com.sigurdscode.resepter.Resept;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Legesystemet {

    Lenkeliste<Pasient> pasientene = new Lenkeliste<>();
    Lenkeliste<Legemiddel> legemiddlene = new Lenkeliste<>();
    SortertLenkeliste<Lege> legene = new SortertLenkeliste<>();

    public Legesystemet(){
    }

    public Lege velgLege(){
        Scanner input = new Scanner(System.in);
        while (true){
            String navn = input.nextLine();
            for(Lege lege : legene){
                String navnet = lege.hentNavn();
                if (navn.equals(navnet)){
                    System.out.println("Den oppgitte legen finnes!");
                    return lege;
                }
            }
            System.out.println("Den oppgitte legen finnes ikke! Prøv igjen");
        }
    }
    public Pasient velgPasient(){
        Scanner input = new Scanner(System.in);
        Pasient p = null;
        try{
            while ( p==null) {
                int pasientId = input.nextInt();
                for(Pasient pasient : pasientene){
                    int idPasienten = pasient.hentID();
                    if (pasientId == idPasienten){
                        System.out.println("Den oppgitte pasienten finnes!");
                        p = pasient;
                    }
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Ikke gyldig input! ");
        }
        return p;
    }
    public void velgResept(Pasient p){
        Scanner input = new Scanner(System.in);
        Stabel<Resept> stabel= p.hentResepterTilPasient();
        if (stabel.stoerrelse() == 0){
            System.out.println("Pasienten har ingen resepter!");
            return;
        }
        System.out.println("\nHvilken Resept vil du bruke?(Velg ID)");
        int valgID = input.nextInt();

        for (Resept resept : stabel){
            if (valgID == resept.hentId()){
                boolean v = resept.bruk();
                if (v){
                    System.out.println("Resepten ble brukt vellykket\n antall reit igjen: "+resept.hentReit());
                    return;
                }else {
                    System.out.println("Mislykket: Ingen flere Reit");
                    return;
                }

            }
        }
        System.out.println("Du valgte en resept som ikke finnes!");
        //Velg og bruk en resept til den gitte pasienten.
    }
    public Legemiddel velgLegemiddel(){
        Scanner input = new Scanner(System.in);
        while (true){
            int ide = input.nextInt();
            for(Legemiddel legemiddel : legemiddlene){
                int Id = legemiddel.hentId();
                if (ide == Id){
                    System.out.println("Det oppgitte Legemiddelet finnes! -" +legemiddel);
                    return legemiddel;
                }
            }
            System.out.println("Det oppgitte legemiddelet finnes ikke! Prøv igjen");
        }
    }

    public void leggTilLege(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------\nNY LEGE\n---------");
        System.out.println("Hva heter legen?");
        String navnLege;
        //input.nextLine(); //Må visst ha denne for at nextLine skal fungere." spiser opp \n"
        navnLege = input.nextLine();
        Lege lege = new Lege(navnLege);
        legene.leggTil(lege);
        System.out.println("Spesialisten"+navnLege+"er lagt til.");
    }
    public void leggTilSpesialist(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------\nNY SPESIALIST\n---------");
        System.out.println("Hva heter spesialisten");
        String navnSpesialist;
        //input.nextLine(); //Må visst ha denne for at nextLine skal fungere." spiser opp \n"
        navnSpesialist = input.nextLine();
        System.out.println("Hva er spesialistens kontrollID?");
        int ID;
        ID = input.nextInt();
        Spesialist spesialist = new Spesialist(navnSpesialist,ID);
        legene.leggTil(spesialist);
        System.out.println("Spesialisten"+navnSpesialist+"er lagt til.");
    }
    public void leggTilPasient(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------\nNY PASIENT\n---------");
        System.out.println("Hva heter pasienten?");
        String navnPasient;
        navnPasient = input.nextLine();
        System.out.println("Hva er pasientens fodselsnummer?");
        String fn;
        fn = input.nextLine();
        Pasient pasient = new Pasient(navnPasient,fn);
        System.out.println("Pasienten: "+navnPasient+", Er lagt til i pasient listen.");
        pasientene.leggTil(pasient);



    }

    public int antallLegemiddler(){
        return legemiddlene.stoerrelse();
    }

    public void skrivNyResept(){
        for(Lege lege : legene){

        }
    }
    public void leggTilLegemiddel(){
        Scanner input = new Scanner(System.in);
        int valg = input.nextInt();
        switch (valg){
            case 0: //Narkotisk
                try{
                    input.nextLine();
                    System.out.println("Navn: ");
                    String navn = input.nextLine();
                    System.out.println("Pris: ");
                    float pris = input.nextInt();
                    System.out.println("Virkestoff: ");
                    float virkestoff = input.nextInt();
                    System.out.println("Styrke: ");
                    int styrke = input.nextInt();
                    Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                    legemiddlene.leggTil(narkotisk);
                    System.out.println("Vellykket! legemiddelet :"+narkotisk+"\nBle lagt til i systemet");

                }catch (InputMismatchException e){
                    System.out.println("Feil input!");
                }

                break;
            case 1: //Vanedannende
                try{
                    input.nextLine();
                    System.out.println("Navn: ");
                    String navn = input.nextLine();
                    System.out.println("Pris: ");
                    float pris = input.nextInt();
                    System.out.println("Virkestoff: ");
                    float virkestoff = input.nextInt();
                    System.out.println("Styrke: ");
                    int styrke = input.nextInt();
                    Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                    legemiddlene.leggTil(vanedannende);
                    System.out.println("Vellykket! legemiddelet :"+vanedannende+"\nBle lagt til i systemet");

                }catch (InputMismatchException e){
                    System.out.println("Feil input!");
                }

                break;
            case 2: //Vanlig
                try{
                    input.nextLine();
                    System.out.println("Navn: ");
                    String navn = input.nextLine();
                    System.out.println("Pris: ");
                    float pris = input.nextInt();
                    System.out.println("Virkestoff: ");
                    int virkestoff = input.nextInt();
                    Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                    legemiddlene.leggTil(vanlig);
                    System.out.println("Vellykket! legemiddelet :"+vanlig+"\nBle lagt til i systemet");

                }catch (InputMismatchException e){
                    System.out.println("Feil input!");
                }

                break;
            case 3: // avslutt
                break;
        }
    }

    public void skrivUtPasienterLiten(){
        for (Pasient pasient : pasientene){
            pasient.skrivUtPasientLiten();
        }
    }
    public void skrivUtPasienter(){
        System.out.println("---------\nPASIENTER\n---------");
        for(Pasient pasienter : pasientene){
            System.out.println(pasienter);
            //pasienter.hentReseptListe();

        }
    }
    public void skrivUtResepterLitenPasient(Pasient p){
        for(Pasient pasient : pasientene){
            if (p.hentID() == pasient.hentID()){
                pasient.skrivUtResepterLiten();
            }
        }
    }
    public void skrivUtResepter(){
        System.out.println("---------\nRESEPTER\n---------");
        for(Lege lege : legene){
            lege.skrivReseptListe();
        }
    }
    public void skrivUtLeger(){
        System.out.println("---------\nLEGER\n---------");
        for(Lege lege : legene){
            System.out.println(lege);
        }
    }
    public void skrivUtLegemiddler(){
        System.out.println("---------\nLEGEMIDDLER\n---------");
        for(Legemiddel legemiddel : legemiddlene){
            System.out.println(legemiddel);
        }
    }
    public void skrivUtLegemiddlerLiten(){
        for (Legemiddel l : legemiddlene){
            System.out.println(l.hentId()+": "+l.hentNavn());
        }
    }

    public void statistikkAntallResepterVanedannende(){
        int teller = 0;
        for (Lege l : legene){
            Lenkeliste<Resept> le = l.hentReseptListe();
            for(Resept r : le){
                Legemiddel legemiddel = r.hentLegemiddel();
                if(Vanedannende.class.equals(legemiddel.getClass())){
                    teller++;
                }
            }
        }
        System.out.println("Antall legemiddler med Vanedannende legemiddel er: "+ teller);
    }
    public void leseFraFil(String filnavn) throws Exception{

        File f = new File(filnavn);
        Scanner fil = null;

        try{
            fil = new Scanner(f);

        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen!");
            return;
        }
       // Scanner fil = new Scanner(new File(filnavn));
        String sjanger = "";

        while (fil.hasNextLine()) {
            if (fil.hasNextLine()){
               sjanger =  fil.nextLine().split(" ")[1];
            }

            if (sjanger.equals("Pasienter")) { //Exception: Hvordan?
                while (!fil.hasNext("#")) {

                    String[] linje2 = fil.nextLine().split(",");
                    Pasient pasient = new Pasient(linje2[0], linje2[1]);
                    pasientene.leggTil(pasient);
                }

            }else if (sjanger.equals("Legemidler")) {
                while (!fil.hasNext("#")) {
                    String navn = null;
                    try {
                        String[] linje2 = fil.nextLine().split(",");
                        navn = linje2[0];

                        String type = linje2[1];
                        double pris = Double.parseDouble(linje2[2]);
                        double virkestoff = Double.parseDouble(linje2[3]);
                        int styrke = 0;
                        if ((type.equals("narkotisk")) || (type.equals("vanedannende"))) { //opprette en throw ecxeption
                            styrke = Integer.parseInt(linje2[4]);
                        }

                        switch (type) {
                            case "narkotisk":
                                Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                                legemiddlene.leggTil(narkotisk);
                                break;
                            case "vanedannende":
                                Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                                legemiddlene.leggTil(vanedannende);
                                break;
                            case "vanlig":
                                Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                                legemiddlene.leggTil(vanlig);
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Feil formatert Legemiddel: " + navn);
                        //denne fanger alle som ikke har et tall på pris plassen i teksten.
                    }
                }

            }else if (sjanger.equals("Leger")){
                while (!fil.hasNext("#")){
                    String[] linje2 = fil.nextLine().split(",");
                    String navn = linje2[0];
                    int kontrollID = Integer.parseInt(linje2[1]);
                    if (kontrollID==0){
                        Lege lege = new Lege(navn);
                        legene.leggTil(lege);
                    }else{
                        Spesialist spesialist = new Spesialist(navn,kontrollID);
                        legene.leggTil(spesialist);
                    }
                }

            }else if(sjanger.equals("Resepter")){
                while (fil.hasNextLine()){
                    try{ //Exception der leger uten interface ikke kan skrive ut resepter som krever spsialist.
                        String[] linje2 = fil.nextLine().split(",");
                        String navn = linje2[1];
                        int pasientID = Integer.parseInt(linje2[2]);
                        String type = linje2[3];

                        int reit=0;
                        if (!(type.equals("p"))){ //opprette en throw ecxeption
                            reit = Integer.parseInt(linje2[4]);
                        }
                        int legemiddelNummer = Integer.parseInt(linje2[0]);


                        Lege legen = null;
                        Pasient pasienten = null;
                        Legemiddel legemiddelet = null;

                        for (Lege lege : legene){
                            if (navn.equals(lege.hentNavn())){
                                legen = lege;
                                //System.out.println(legen);
                            }
                        }
                        for (Pasient pasient : pasientene){
                            if (pasientID == pasient.hentID()){
                                pasienten = pasient;
                            }
                        }
                        for (Legemiddel legemiddel : legemiddlene){
                            if (legemiddelNummer == legemiddel.hentId()){
                                legemiddelet = legemiddel;
                            }
                        }
                        //fnger opp at det ikke skapes resepter som er med tomt innhold.
                        if (!(legen == null || pasienten == null || legemiddelet == null)){
                            switch (type) {
                                case "hvit":
                                    //assert legen != null;
                                    pasienten.leggTilResept(legen.skrivHvitResept(legemiddelet, pasienten, reit));

                                    break;
                                case "blaa":
                                    //assert legen != null;
                                    pasienten.leggTilResept(legen.skrivBlaaResept(legemiddelet, pasienten, reit));
                                    break;
                                case "militaer":
                                    //assert legen != null;
                                    pasienten.leggTilResept(legen.skrivMillitaerResept(legemiddelet, pasienten, reit));
                                    break;
                                case "p":
                                    //assert legen != null;
                                    pasienten.leggTilResept(legen.skrivPResept(legemiddelet, pasienten));
                                    break;
                            }
                        }

                    }catch (UlovligUtskrift e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
