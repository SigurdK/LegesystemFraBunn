package com.sigurdscode.exceptions;

public class UgyldigPasientException extends Exception {
    public UgyldigPasientException (String id) {
        super("Det var ikke plass til aa sette inn " + id);
    }
}
