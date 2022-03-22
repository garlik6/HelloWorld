package ch02.n1617;

import java.util.LinkedList;

public class Queue {
    private Node head = null;
    private Node last = null;
    private int size = 0;

    private static class Node {
        private final String string;
        private Node next;
        public String getString() {
            return string;
        }
        public Node(String string) {
            this.string = string;
            this.next = null;
        }

    }
        public void add(String string){
            Node newNode = new Node(string);
            if (size == 0) {
                head = newNode;
                last = newNode;
            } else {
                last.next = newNode;
                last = last.next;
            }
            size++;
        }
        public void remove(){
            if(size != 0 ){
                if(size == 1){
                    head = null;
                    last = null;
                } else {
                    head = head.next;
                }
                size--;
            }
        }

    public int getSize() {
        return size;
    }

    public Iterator iterator(){
        return new Iterator(this.head);
    }
    public static class Iterator{
        public String getCurrentString() {
            return current.getString();
        }
        private Node current;
        public Iterator(Node node){
            current = node;
        }
        public boolean hasNext(){
            return current.next != null;
        }
        public Node next(){
            return current.next;
        }
        public void setCurrent(Node node) {current = node;}
    }
}
