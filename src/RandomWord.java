import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int index = 1;
        double prob;
        String animalName = StdIn.readString();
        while (!StdIn.isEmpty()) {
            String selectName = StdIn.readString();
            index++;
            prob = 1.0/index;
            boolean flag;
            flag = StdRandom.bernoulli(prob);
            if (flag) {
                animalName = selectName;
            }
        }
        System.out.println(animalName);

    }
}
