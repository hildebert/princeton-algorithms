import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints
{
    private LineSegment[] segmentsArray;

    public BruteCollinearPoints(Point[] points) 
    {
        checkInput(points);
        
        ArrayList<LineSegment> found = new ArrayList<LineSegment>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int i1 = 0; i1 < pointsCopy.length; i1++)
        {
            for (int i2 = i1 + 1; i2 < pointsCopy.length; i2++)
            {
                for (int i3 = i2 + 1; i3 < pointsCopy.length; i3++)
                {
                    for (int i4 = i3 + 1; i4 < pointsCopy.length; i4++)
                    {
                        Point p1 = pointsCopy[i1];
                        Point p2 = pointsCopy[i2];
                        Point p3 = pointsCopy[i3];
                        Point p4 = pointsCopy[i4];
                        
                        if (p1.slopeTo(p2) == p1.slopeTo(p3) 
                                && p1.slopeTo(p3) == p1.slopeTo(p4))
                        {
                            found.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
        
        segmentsArray = found.toArray(new LineSegment[found.size()]);
    }

    public int numberOfSegments()
    {
        return segmentsArray.length;
    }
    
    public LineSegment[] segments()
    {
        return Arrays.copyOf(segmentsArray, segmentsArray.length);
    }
    
    private void checkInput(Point[] points)
    {
        if (points == null) 
        {
            throw new NullPointerException("Input is null");
        }
        
        for (int i = 0; i < points.length - 1; i++) 
        {
            if (points[i] == null)
            {
                throw new NullPointerException("Input is null");
            }
            
            for (int j = i + 1; j < points.length; j++) 
            {
                if (points[i].compareTo(points[j]) == 0) 
                {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }
    
    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point[] points = {
            new Point(19000, 10000), 
            new Point(18000, 10000),
            new Point(32000, 10000),
            new Point(21000, 10000),
            new Point(1234, 5678),
            new Point(14000, 10000),
        };
        
        BruteCollinearPoints b = new BruteCollinearPoints(points);

        System.out.println(b.numberOfSegments());
        System.out.println(Arrays.toString(b.segments()));

        System.out.println(b.numberOfSegments());
        System.out.println(Arrays.toString(b.segments()));

        System.out.println(b.numberOfSegments());
        System.out.println(Arrays.toString(b.segments()));

        System.out.println(b.numberOfSegments());
        System.out.println(Arrays.toString(b.segments()));
        
        Point[] points2 = {            
            new Point(10000, 0),    
            new Point(0, 10000),
            new Point(3000, 7000),
            new Point(7000, 3000),
            new Point(20000, 21000),
            new Point(3000, 4000),
            new Point(14000, 15000),
            new Point(6000, 7000)
        };
        
        BruteCollinearPoints b2 = new BruteCollinearPoints(points2);

        System.out.println(b2.numberOfSegments());
        System.out.println(Arrays.toString(b2.segments()));
    }
}

