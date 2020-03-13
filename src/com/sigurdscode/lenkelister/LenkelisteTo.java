package com.sigurdscode.lenkelister;
import java.util.NoSuchElementException;

import java.util.Iterator;

public class LenkelisteTo<T> implements Iterable<T> {
    private class Node {
        T data;
        Node neste;

        public Node(T data) {
            this.data = data;
            neste = null;
        }
    }

    Node start;

    public LenkelisteTo() {
        start = null;
    }

    public void skrivUt() {
        Node tempNode = start;

        while (tempNode != null) {
            System.out.println(tempNode.data);
            tempNode = tempNode.neste;
        }
    }

    //Sett inn element
    public void settInn(T data) {
        Node nyNode = new Node(data);

        if (start == null) {
            start = nyNode;
            return;
        }

        Node tempNode = start;
        while(tempNode.neste != null) {
            tempNode = tempNode.neste;
        }

        tempNode.neste = nyNode;
    }

    //Fjerne og returnere element
    public T fjernForste() {
        if (start == null) {
            //throw exception
            System.out.println("Listen er tom!");
            return null;
        }
        T data = start.data;
        start = start.neste;
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        return new HjelpeLenkeIterator();
    }

    class HjelpeLenkeIterator implements Iterator<T> {
        Node denne = start;

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (denne == null) {
                throw new NoSuchElementException("next");
            }

            Node tmp = denne;
            denne = denne.neste;
            return tmp.data;
        }
    }
}