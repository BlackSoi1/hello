import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node temp = new Node();
        temp.item = item;
        temp.prev = null;
        temp.next = first;
        first = temp;
        if (last == null) {
            last = first;
        }
        if (first.next != null) {
            first.next.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node temp = new Node();
        temp.prev = last;
        temp.item = item;
        temp.next = null;
        last = temp;
        if (isEmpty()) {
            first = last;
        }
        if (last.prev != null) {
            last.prev.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        //case when has flowing item
        if (first != null) {
            first.prev = null;
        }
        else {
            //case when only 1 item
            last = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        //case when has previous item
        if(last!=null){
            last.next=null;
        }else{
            //case when only 1 item
            first=null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        System.out.println(test.isEmpty());
        test.addLast(1);
        test.removeLast();
        test.addLast(3);
        test.addFirst(2);
        for(Integer i:test){
            System.out.println(i);
        }
        // // Integer i1 =test.removeFirst();
        // // System.out.println(i1);
        // Integer i2 = test.removeLast();
        // System.out.println(i2);
        // System.out.println(test.size());
    }

}