package com.sigurdscode;

import com.sigurdscode.legemiddler.Legemiddel;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.leger.UlovligUtskrift;
import com.sigurdscode.menyer.MenySamling;
import com.sigurdscode.pasienter.Pasient;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Legesystemet system = new Legesystemet();
        system.leseFraFil("InformasjonStor.txt");
        MenySamling meny = new MenySamling();

        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------\n" +
                "VELKOMMEN TIL DENNE JOURNALEN\n" +
                "------------------------------");

        int valg = 10;
        while (valg != 5){
            try{
                meny.hovedMeny();
                valg = input.nextInt(); // velger her hva som skal skje: velges 5 så avsluttes programmet.

                switch (valg){ //Husk å fange alle InputMismatchException

                    case 0: //Skriv ut Journal.
                        int valg2 = 10;
                        while (valg2 != 4){
                            meny.journalMeny();
                            valg2 = input.nextInt();
                            switch (valg2){
                                case 0: //Pasienter
                                    system.skrivUtPasienter();
                                    break;
                                case 1: //Leger
                                    system.skrivUtLeger();
                                    break;
                                case 2: //Legemidler
                                    system.skrivUtLegemiddler();
                                    break;
                                case 3: //Resepter
                                    system.skrivUtResepter();
                                    break;
                            }
                        }
                        break;

                    case 1: //Opprett og legge til nye elementer i systemet.
                        int valg3 = 10;
                        while (valg3 != 4) {
                            meny.opprettNyMeny();
                            valg3 = input.nextInt();
                            switch (valg3) {
                                case 0: //Lege
                                    int valg4 = 10;
                                    meny.legeSpesialistMeny();
                                    valg4 = input.nextInt();
                                    switch(valg4){
                                        case 0: //Lege
                                            system.leggTilLege();//Husk å legge til exceptions
                                            break;
                                        case 1: //Spesialist
                                            system.leggTilSpesialist();//Husk å legge til exceptions
                                            break;
                                    }
                                    break;

                                case 1: //Pasient
                                    system.leggTilPasient();
                                    break;
                                case 2: //Resept

                                    system.skrivUtLeger();
                                    System.out.println("\nHva heter den utskrivende legen/spesialisten? (Se listen over)");
                                    Lege reseptensSkrivendeLege = system.velgLege(); //velge lege


                                    system.skrivUtPasienterLiten();
                                    System.out.println("\nHva er ID til pasienten som skal ha resept? (velg ID fra listen over)");
                                    Pasient reseptensPasient = system.velgPasient();     //velge Pasient
                                    if (reseptensPasient == null){
                                        break;
                                    }
                                    system.skrivUtLegemiddlerLiten();
                                    System.out.println("\nHvilket legemiddel gjelder resepten for? (Velg ID nummer mellom 0 - "+ (system.antallLegemiddler()-1)+")");
                                    Legemiddel reseptensLegemiddel = system.velgLegemiddel();    //velge Legemiddel

                                    int valg5=10;

                                    meny.reseptTypeMeny();
                                    valg5 = input.nextInt();
                                    switch(valg5){ //Type resept
                                        case 0: //Hvit
                                            System.out.println("Hvor mange? (Reit)");
                                            int reit = input.nextInt();
                                            try{//Ikke sikkert legen kan skriv eut da legemidde kan være narkotisk
                                                //Legger til respten som opprettes av legen til pasientens respt stabel.
                                                reseptensPasient.leggTilResept(reseptensSkrivendeLege.skrivHvitResept(reseptensLegemiddel,reseptensPasient,reit));
                                                System.out.println("Godkjent!");
                                            }catch (UlovligUtskrift e){
                                                System.out.println("misslykket! "+e.getMessage());
                                            }
                                            break;
                                        case 1://Millitaer
                                            System.out.println("Hvor mange? (Reit)");
                                            reit = input.nextInt();
                                            try{//Ikke sikkert legen kan skriv eut da legemidde kan være narkotisk
                                                reseptensPasient.leggTilResept(reseptensSkrivendeLege.skrivMillitaerResept(reseptensLegemiddel,reseptensPasient,reit));
                                                System.out.println("Godkjent!");
                                            }catch (UlovligUtskrift e){
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 2: //P

                                            try{//Ikke sikkert legen kan skriv eut da legemidde kan være narkotisk
                                                reseptensPasient.leggTilResept(reseptensSkrivendeLege.skrivPResept(reseptensLegemiddel,reseptensPasient));
                                                System.out.println("Godkjent!");
                                            }catch (UlovligUtskrift e){
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 3://Blaa
                                            System.out.println("Hvor mange? (Reit)");
                                            reit = input.nextInt();
                                            try{//Ikke sikkert legen kan skriv eut da legemidde kan være narkotisk
                                                reseptensPasient.leggTilResept(reseptensSkrivendeLege.skrivBlaaResept(reseptensLegemiddel,reseptensPasient,reit));
                                                System.out.println("Godkjent!");
                                            }catch (UlovligUtskrift e){
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                    }
                                    break;
                                case 3: //Legemiddel
                                    meny.lageLegemiddelMeny();
                                    system.leggTilLegemiddel();
                                    break;
                            }
                        }
                        break;

                    case 2: //Bruke en gitt resept fra listen til en pasient.
                        system.skrivUtPasienterLiten();
                        System.out.println("\nHvilken pasient vil du se resepter for? (Velg ID fra listen over)");
                        Pasient pasient = system.velgPasient();
                        system.skrivUtResepterLitenPasient(pasient);
                        system.velgResept(pasient);
                        //Over skrives så en enkel verson av pasientens resepter. Velg hvilken resept ogsp med ID.
                        break;

                        case 3: //Skrive ut statistikk.
                            System.out.println("--- STATISTIKK ---");
                            System.out.println("Antall legemiddler med Vanedannende legemiddel er: "+ system.statistikkAntallResepterVanedannende());
                            System.out.println("Antall legemiddler med Narkotisk legemiddel er: "+ system.statistikkAntallResepterNarkotisk());
                        break;
                    case 4: //Skriv alle data til fil.
                        //lage en fil med navnet som tastes inn.
                        System.out.println("Hva skal filen hete?");
                        input.nextLine();
                        String filnavn= input.nextLine();

                        system.lesTilFil(filnavn);
                        break;

                }
            }catch (InputMismatchException e){
                System.out.println("Ugyldig Input!");
                input.next(); // Må ha for at scanner skal cleares og while loopen ikke skal gå amokk.
            }
        }
    }
}
