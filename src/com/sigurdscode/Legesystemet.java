package com.sigurdscod

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
import java.util.Scanner;

public class Legesystemet {

    Lenkeliste<Pasient> pasientene = new Lenkeliste<>();
    Lenkeliste<Legemiddel> legemiddlene = new Lenkeliste<>();
    SortertLenkeliste<Lege> legene = new SortertLenkeliste<>();

    //Sorter legeListen med instanceOf

    public Legesystemet(){
    }

    public void skrivUtPasienter(){
        for(Pasient pasienter : pasientene){
            System.out.println(pasienter);
        }
    }
    public void skrivUtLeger(){
        for(Lege lege : legene){
            System.out.println(lege);
            //lege.skrivReseptListe();
        }
    }
    public void skrivUtLegemiddler(){
        for(Legemiddel legemiddel : legemiddlene){
            System.out.println(legemiddel);
        }
    }

    public void leseFraFil(String filnavn) throws Exception{

        Scanner fil = new Scanner(new File(filnavn));
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
                while (!fil.hasNext("#")){
                    try{
                        String[] linje2 = fil.nextLine().split(",");
                        String navn = linje2[0];

                        String type = linje2[1];
                        double pris = Double.parseDouble(linje2[2]);
                        double virkestoff = Double.parseDouble(linje2[3]);
                        //int styrke = Integer.parseInt(linje2[4]); //Må fikse denne..
                        int styrke = 0;
                        if ((type.equals("narkotisk")) || (type.equals("vanedannende"))){ //opprette en throw ecxeption
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
                    }catch(NumberFormatException e){
                        System.out.println("Feil formatert Legemiddel");
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
                                    legen.skrivHvitResept(legemiddelet, pasienten, reit);
                                    break;
                                case "blaa":
                                    //assert legen != null;
                                    legen.skrivBlaaResept(legemiddelet, pasienten, reit);
                                    break;
                                case "millitaer":
                                    //assert legen != null;
                                    legen.skrivMillitaerResept(legemiddelet, pasienten, reit);
                                    break;
                                case "p":
                                    //assert legen != null;
                                    legen.skrivPResept(legemiddelet, pasienten);
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
