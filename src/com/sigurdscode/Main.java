package com.sigurdscode;

import com.sigurdscode.legemiddler.Narkotisk;
import com.sigurdscode.legemiddler.Vanlig;
import com.sigurdscode.leger.Lege;
import com.sigurdscode.leger.Spesialist;
import com.sigurdscode.lenkelister.Lenkeliste;
import com.sigurdscode.lenkelister.LenkelisteTo;
import com.sigurdscode.lenkelister.Stabel;
import com.sigurdscode.menyer.MenySamling;
import com.sigurdscode.pasienter.Pasient;
import com.sigurdscode.resepter.PResept;

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
                                while (valg4 != 2){
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
                                }
                                break;

                            case 1: //Pasient
                                system.leggTilPasient();
                                break;
                            case 2: //Resept
                                //velge lege
                                //velge resepttype
                                int valg5=10;
                                while(valg5 != 4){
                                    meny.reseptTypeMeny();
                                    valg5 = input.nextInt();
                                    switch(valg5){ //Type resept
                                        case 0: //Hvit
                                            break;
                                        case 1://Millitaer
                                            break;
                                        case 2: //P
                                            break;
                                        case 3://Blaa
                                            break;
                                    }
                                }
                                system.skrivNyResept();
                                break;
                            case 3: //Legemiddel
                                system.leggTilLegemiddel();
                                break;
                        }
                    }
                    break;

                case 2: //Bruke en gitt resept fra listen til en pasient.
                    break;
                case 3: //Skrive ut statistikk.
                    break;
                case 4: //Skriv alle data til fil.
                    break;

            }
        }
    }
}
