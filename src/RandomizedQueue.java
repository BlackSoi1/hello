import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rqueue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        rqueue = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
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
        //if reach the capacity resize the array to double
        if (size == rqueue.length) {
            resize(rqueue.length * 2);
        }
        rqueue[size] = item;
        size++;
    }

    private void resize(int capacity) {
        // assert capacity >= size;
        // create a new array whose size is capacity and copy the original array
        Item[] tempQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tempQueue[i] = rqueue[i];
        }
        rqueue = tempQueue;
        //set to null to save memory
        tempQueue = null;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        //get a random number from [0,size)
        int removeIndex = StdRandom.uniform(0, size);
        Item item = rqueue[removeIndex];
        //swap the remove element to the last element
        if (removeIndex != size - 1) {
            rqueue[removeIndex] = rqueue[size - 1];
        }
        //set last element to null
        rqueue[size - 1] = null;
        size--;
        if (size > 0 && size == rqueue.length / 4) {
            resize(rqueue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int sampleIndex = StdRandom.uniform(0, size);
        Item item = rqueue[sampleIndex];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new rqueueIterator();
    }

    private class rqueueIterator implements Iterator<Item> {
        //create a new array and copy the original array
        private int fakeSize = size; // this is the number of element should iterate
        private Item[] iterQueue = (Item[]) new Object[rqueue.length];

        public rqueueIterator() {
            for (int i = 0; i < rqueue.length; i++) {
                iterQueue[i] = rqueue[i];
            }
        }

        public boolean hasNext() {
            return fakeSize > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int iteratorIndex = StdRandom.uniform(fakeSize);
            Item item = iterQueue[iteratorIndex];
            //swap the remove element to the last element
            if (iteratorIndex != fakeSize - 1) {
                iterQueue[iteratorIndex] = iterQueue[fakeSize - 1];
            }
            //set last element to null
            iterQueue[fakeSize - 1] = null;
            //decrement the fake size
            fakeSize--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);
        test.enqueue(5);
        for (Integer i : test) {
            System.out.println(i);
        }
        // System.out.println(test.dequeue());
        // System.out.println(test.dequeue());
        // System.out.println(test.dequeue());
        System.out.println(test.sample());
        System.out.println("Size: " + test.size());
    }
}

