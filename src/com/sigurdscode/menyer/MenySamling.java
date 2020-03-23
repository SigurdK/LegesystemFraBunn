package com.sigurdscode.menyer;

public class MenySamling {

    public void hovedMeny(){

        System.out.println("\nHOVEDMENY:\n" +
                "0: Skriv ut Journal\n" +
                "1: Opprett og legge til nye elementer i systemet\n" +
                "2: Bruke en gitt resept fra listen til en pasient\n" +
                "3: Skrive ut statistikk\n" +
                "4: Skriv alle data til fil\n" +
                "5: EXIT");
    }
    public void journalMeny(){
        System.out.println("\nSkriv ut:\n" +
                "0: Pasienter\n" +
                "1: Leger\n" +
                "2: Legemiddler\n" +
                "3: Resepter\n" +
                "4: Tilbake");
    }
    public void opprettNyMeny(){
        System.out.println("\nOpprett ny:\n" +
                "0: Lege\n" +
                "1: Pasient\n" +
                "2: Resept\n" +
                "3: Legemiddel\n" +
                "4: Tilbake");
    }
    public void legeSpesialistMeny(){
        System.out.println("\nLege eller Spesialist:\n" +
                "0: Lege\n" +
                "1: Spesialist\n");
    }
    public void reseptTypeMeny(){
        System.out.println("\nType resept:\n" +
                "0: Hvit Resept\n" +
                "1: Millitaer Resept\n" +
                "2: P Resept\n" +
                "3: Blaa Resept\n");
    }
    public void lageLegemiddelMeny(){
        System.out.println("\nType legemiddel:\n" +
                "0: Narkotisk\n" +
                "1: Vanedannende\n" +
                "2: Vanlig\n" +
                "3: tilbake");
    }
}
