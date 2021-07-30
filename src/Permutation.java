import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rqt = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            rqt.enqueue(str);
            // System.out.println("Add once");
        }
        // System.out.println("Next step");
        int k = Integer.parseInt(args[0]);
        if (k < 0 || k > rqt.size())
            throw new IllegalArgumentException();
        Iterator<String> is = rqt.iterator();
        while (k != 0) {
            String s = is.next();
            System.out.println(s);
            k--;
        }

    }
}