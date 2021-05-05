package org.stanc;

import java.util.*;

/**
 * class Problem:
 * - data is a list of list of points: {l1, l2, l3 ..., lt} where t is an integer between 1 and 5
 * - data contains list l = {p1, p2 ..., pn} where n is an integer between 1 and 40
 * - we won't store the integers T and N1..Nt, since we can get that information calling ArrayList::size()
 */
public class Problem {

    private List<List<Point>> subProblems;
    public static final String INVALID_SUBPROBLEMS_BOUNDS = "The given subproblems array is either null or is outside " +
            "of bounds [1, 5]";
    public static final String INVALID_POINTS_BOUNDS = "The given points array for subproblem number %d is either null " +
            "or is outside of bounds [1, 40]";
    public static final String INVALID_COORDINATES_BOUNDS = "The given points coordinates for subproblem number %d, point " +
            "number %d are outside of bounds [0, 50]";

    public Problem(List<List<Point>> subProblems) {
        this.subProblems = subProblems;
    }

    public List<String> solve() {

        if (subProblems == null || subProblems.size() < 1 || subProblems.size() > 5) {
            throw new RuntimeException(INVALID_SUBPROBLEMS_BOUNDS);
        }

        List<String> results = new ArrayList<>();

        for (int i = 0; i < subProblems.size(); i++) {
            results.add(solveOne(subProblems.get(i), i));
        }

        return results;
    }

    private String solveOne(List<Point> points, int subProblemIndex) {

        if (points == null || points.size() < 1 || points.size() > 40) {
            throw new RuntimeException(String.format(INVALID_POINTS_BOUNDS, subProblemIndex));
        }

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() < 0 || points.get(i).getX() > 50 || points.get(i).getY() < 0 || points.get(i).getY() > 50) {
                throw new RuntimeException(String.format(INVALID_COORDINATES_BOUNDS, subProblemIndex, i));
            }
        }

        Map<LineData, Integer> lineDataCounter = new HashMap<>();
        int n = points.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                LineData ld = new LineData(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
                if (lineDataCounter.containsKey(ld)) {
                    Integer oldVal = lineDataCounter.get(ld);
                    if (oldVal == 2) {
                        return "TRUE";
                    }
                    lineDataCounter.put(ld, oldVal + 1);
                } else {
                    lineDataCounter.put(ld, 1);
                }
            }
        }

        return "FALSE";
    }

    //@Override
    /*
    public String toString() {

        String display = "{";
        if (subProblems != null) {
            display = display + "[";
            for (List<Point> subProblem : subProblems) {
                for (Point p : subProblem) {
                    display = display + p + ", ";
                }
                if (display.endsWith(", ")) {
                    display = display.substring(0, display.length() - 2);
                }
            }
            display = display + "], ";

            if (display.endsWith(", ")) {
                display = display.substring(0, display.length() - 2);
            }
        }
        display = display + "}";
        return display;
    }
     */

    private class LineData {

        int dx;
        int dy;

        public LineData(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LineData lineData = (LineData) o;
            return dx == lineData.dx && dy == lineData.dy;
        }

        @Override
        public int hashCode() {
            return Objects.hash(dx, dy);
        }
    }

//    public static void main(String[] args) {
//
//        // SP 1
//        List<Point> ptList1 = Arrays.asList(
//                new Point(1, 1),
//                new Point(2, 2),
//                new Point(1, 2),
//                new Point(0, 0),
//                new Point(35, 35));
//
//        // SP 2
//        List<Point> ptList2 = Arrays.asList(
//                new Point(1, 1),
//                new Point(2, 2),
//                new Point(1, 2),
//                new Point(0, 0),
//                new Point(0, 1));
//
//
//        Problem p = new Problem(Arrays.asList(ptList1, ptList2));
//        List<String> res = p.solve();
//        for (String s : res) {
//            System.out.println(s);
//        }
//    }



}
