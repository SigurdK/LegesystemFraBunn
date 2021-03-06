package com.sigurdscode;

import com.sigurdscode.legemiddler.Legemiddel;
import com.sigurdscode.legemiddler.Narkotisk;
import com.sigurdscode.legemiddler.Vanedannende;
import com.sigurdscode.legemiddler.Vanlig;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.leger.Spesialist;
import com.sigurdscode.leger.UlovligUtskrift;
import com.sigurdscode.lenkelister.Lenkeliste;
import com.sigurdscode.lenkelister.SortertLenkeliste;
import com.sigurdscode.pasienter.Pasient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Legesystemet {

    Lenkeliste<Pasient> pasientene = new Lenkeliste<>();
    Lenkeliste<Legemiddel> legemiddlene = new Lenkeliste<>();
    SortertLenkeliste<Lege> legene = new SortertLenkeliste<>();

    public Legesystemet(){
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
        pasientene.leggTil(pasient);

    }

    public void skrivNyResept(){
        for(Lege lege : legene){

        }
    }
    public void leggTilLegemiddel(){

    }

    public void skrivUtPasienter(){
        System.out.println("---------\nPASIENTER\n---------");
        for(Pasient pasienter : pasientene){
            System.out.println(pasienter);
            pasienter.hentReseptListe();

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
