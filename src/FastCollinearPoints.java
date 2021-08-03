import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FastCollinearPoints {

    private List<LineSegment> lineSegments;
    private int numOfSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int m = 0; m < points.length; m++) {
            if (points[m] == null) {
                throw new IllegalArgumentException();
            }
            for (int y = m + 1; y < points.length; y++) {
                if (points[m].slopeTo(points[y]) == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
            }
        }

        Point[] slopePoints = new Point[points.length];
        for (int k = 0; k < points.length; k++) {
            slopePoints[k] = points[k];
        }
        lineSegments = new ArrayList<>();
        Arrays.sort(points);
        for (int i = 0; i < slopePoints.length; i++) {
            Arrays.sort(slopePoints);
            Arrays.sort(slopePoints, points[i].slopeOrder());
            for (int j = 1; j < slopePoints.length - 2; j++) {
                if ((points[i].slopeTo(slopePoints[j]) == points[i].slopeTo(slopePoints[j + 1]))
                        && (points[i].slopeTo(slopePoints[j]) == points[i].slopeTo(slopePoints[j + 2]))
                        && points[i].compareTo(slopePoints[j]) < 1
                        && slopePoints[j].compareTo(slopePoints[j + 1]) < 1
                        && slopePoints[j + 1].compareTo(slopePoints[j + 2]) < 1)
                    lineSegments.add(new LineSegment(points[i], slopePoints[j + 2]));
            }

        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return numOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}