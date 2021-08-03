import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private int size;
    private List<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        //if the input collection is null throw exception
        if (points == null) {
            throw new IllegalArgumentException();
        }
        //if the input collection's element is null throw exception
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        //if the input collection's element is repeat throw exception
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        Point[] ps = points.clone();
        segments = new ArrayList<>();
        size = 0;


        Arrays.sort(ps);

        for (int i = 0; i < ps.length; i++) {
            for (int j = i + 1; j < ps.length; j++) {
                for (int k = j + 1; k < ps.length; k++) {
                    for (int m = k + 1; m < ps.length; m++) {
                        double slope1 = ps[i].slopeTo(ps[j]);
                        double slope2 = ps[i].slopeTo(ps[k]);
                        double slope3 = ps[i].slopeTo(ps[m]);
                        if (slope1 == slope2 && slope1 == slope3) {
                            if (ps[i].compareTo(ps[j]) < 1
                                    && ps[j].compareTo(ps[k]) < 1
                                    && ps[k].compareTo(ps[m]) < 1) {
                                segments.add(new LineSegment(ps[i], ps[m]));
                                size++;
                            }

                        }
                    }
                }
            }
        }

        // segments=segments();
    }

    // the number of line segments
    public int numberOfSegments() {
        return size;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

}