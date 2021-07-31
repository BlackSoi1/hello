import java.util.Iterator;


import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue2<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
        boolean state = false;
    }

    // construct an empty randomized queue
    public RandomizedQueue2() {
        first = null;
        last = null;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first==null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int removeIndex = StdRandom.uniform(1, size + 1);
        Item item;
        //case 1: remove first item
        if (removeIndex == 1) {
            item = first.item;
            first = first.next;
        }
        //case 2 :remove last item
        else if (removeIndex == size) {
            item = last.item;
            Node temp = first;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            last = temp;
            last.next = null;
        }
        //case 3: general case in between
        else {
            Node temp = first;
            while (removeIndex  != 2) {
                temp = temp.next;
                removeIndex--;
            }
            item = temp.next.item;
            Node temp2 =temp.next.next;
            temp.next = temp2;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int sampleIndex = StdRandom.uniform(1, size + 1);
        Item item;
        //case 1: return first item
        if (sampleIndex == 1) {
            item = first.item;
        }
        //case 2 :return last item
        else if (sampleIndex == size) {
            item = last.item;
        }
        //case 3: general case in between
        else {
            Node temp = first;
            while (sampleIndex  != 1) {
                temp = temp.next;
                sampleIndex--;
            }
            item = temp.item;
        }
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node temp = first;
        private int fakeSize = size;

        public boolean hasNext() {
            // return current != null;
            return fakeSize > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            boolean flag = true;
            while (flag) {
                int iteratorIndex = StdRandom.uniform(1, size + 1);
                temp = first;
                while (iteratorIndex != 1) {
                    temp = temp.next;
                    iteratorIndex--;
                }
                if (temp.state == false) {
                    temp.state = true;
                    fakeSize--;
                    break;
                }
            }
            return temp.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue2<Integer> rq = new RandomizedQueue2<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        System.out.println(rq.size());

        // for (Integer i : rq) {
        //     System.out.println(i);
        // }
        Integer i1=rq.dequeue();
        System.out.println(i1);
        // System.out.println(rq.size());
        // for(int i =0;i<500;i++){
        //     Integer i1=rq.sample();
        //     if(i1==2){
        //         System.out.println("fk!!!!");
        //     }
        //     System.out.println(i1);
        // }

        // System.out.println(rq.size());
    }

}