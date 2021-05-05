package org.stanc.utils;

import org.stanc.Point;
import org.stanc.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class purpose is to provide test data for the main test class: ProblemTest
 * <p>
 * Should be able to provide test data based on the following techniques:
 * a) equivalence partitioning
 * b) boundary value analysis
 * c) category partitioning
 */

public class TestDataProvider {

    /**
     * Input values:
     * T - between 1 and 5 -> therefore, there are 4 partitions:
     * T0 -> subproblems array is null
     * T1 -> T < 1, where T is the number of subproblems
     * T2 -> 5 < T, where T is the number of subproblems
     * T3 -> 1 <= T <= 5,where T is the number of subproblems
     * <p>
     * N - between 1 and 40, where N may be the length of any subproblem. Because testing the case when T = 1 is
     * representative for the cases when T is 2,3,4 or 5, we will consider the case with a single subproblem (T = 1)
     * N0 -> the subproblem is null
     * N1 -> N < 1, where N is the number of points of the subproblem
     * N2 -> 40 < N, where N is the number of points of the subproblem
     * N3 -> 1 <= N <= 40, where N is the number of points of the subproblem
     * <p>
     * Point coordinates values - between 0, 50. We will consider that the subproblem has a singl e point (same analysis),
     * therefore, there are 3 partitions:
     * P0 -> the point has the coordinates < 0
     * P1 -> the point has the coordinates > 50
     * P2 -> the point has the coordinates inside 0 and 50
     * <p>
     * The subproblem result can be either "TRUE" either "FALSE", therefore, there are 2 partitions:
     * R0 -> the result is "FALSE"
     * R1 -> the result is "TRUE"
     * <p>
     * <p>
     * Considering the above possibilities, there are 10 possible cases:
     * T0,
     * T1,
     * T2,
     * T3_N0,
     * T3_N1,
     * T3_N2,
     * T3_N3_P0,
     * T3_N3_P1,
     * T3_N3_P2_R0
     * T3_N3_P2_R1
     */
    public List<TestItem> getEquivalencePartitioningTestData() {

        List<TestItem> testItems = new ArrayList<>();

        /**  T0. */

        Problem problem0 = new Problem(null);
        Result result0 = Result.makeErrorResult(Problem.INVALID_SUBPROBLEMS_BOUNDS);
        testItems.add(new TestItem(problem0, result0, "T0"));

        /**  T1. */

        Problem problem1 = new Problem(new ArrayList<>());
        Result result1 = Result.makeErrorResult(Problem.INVALID_SUBPROBLEMS_BOUNDS);
        testItems.add(new TestItem(problem1, result1, "T1"));

        /** T2.  */

        Point dummyPoint = new Point(0, 0);
        List<Point> list2 = Arrays.asList(dummyPoint);

        Problem problem2 = new Problem(Arrays.asList(list2, list2, list2, list2, list2, list2));
        Result result2 = Result.makeErrorResult(Problem.INVALID_SUBPROBLEMS_BOUNDS);
        testItems.add(new TestItem(problem2, result2, "T2"));

        /** 3. T3_N0.  */
        List<Point> pointList3 = null;
        Problem problem3 = new Problem(Arrays.asList(pointList3));
        Result result3 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(problem3, result3, "T3_N0"));

        /** 4. T3_N1.  */

        List<Point> list4 = new ArrayList<>();
        Problem prob4 = new Problem(Arrays.asList(list4));
        Result res4 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(prob4, res4, "T3_N1"));

        /** 5. T3_N2.  */

        List<Point> list5 = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            list5.add(new Point(0, 0));
        }
        Problem prob5 = new Problem(Arrays.asList(list5));
        Result res5 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(prob5, res5, "T3_N2"));

        /** 6. T3_N3_P0.  */

        List<Point> list6 = Arrays.asList(new Point(-3, -1));
        Problem problem6 = new Problem(Arrays.asList(list6));
        Result result6 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem6, result6, "T3_N3_P0"));

        /** 7. T3_N3_P1.  */

        List<Point> list7 = Arrays.asList(new Point(51, 51));
        Problem problem7 = new Problem(Arrays.asList(list7));
        Result result7 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem7, result7, "T3_N3_P1"));

        /** 8. T3_N3_P2_R0  */

        List<Point> list8 = Arrays.asList(new Point(0, 0));
        Problem problem8 = new Problem(Arrays.asList(list8));
        Result result8 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem8, result8, "T3_N3_P2_R0"));

        /** 9. T3_N3_P2_R1  */

        List<Point> list9 = Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 2),
                new Point(0, 0),
                new Point(0, 1));

        Problem problem9 = new Problem(Arrays.asList(list9));
        Result result9 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem9, result9, "T3_N3_P2_R1"));

        return testItems;
    }


    /**
     * For generating test data based on boundary analysis, we should consider the following possibilities:
     * T, which should belong to the interval [1, 5]:
     * T0 -> value 0
     * T1 -> value 1
     * T2 -> value 5
     * T3 -> value 6
     * N, which should belong to the interval [1, 40]:
     * N0 -> value 0
     * N1 -> value 1
     * N2 -> value 40
     * N3 -> value 41
     * Point coordinates which should belong to the interval [0, 50]:
     * P0 -> value -1
     * P1 -> value 0
     * P2 -> value 50
     * P3 -> value 51
     * <p>
     * T0
     * T1_N0
     * T1_N1_P0
     * T1_N1_P1
     * T1_N1_P2
     * T1_N1_P3
     * T1_N2_P0
     * T1_N2_P1
     * T1_N2_P2
     * T1_N2_P3
     * T1_N3
     * T2_N0
     * T2_N1_P0
     * T2_N1_P1
     * T2_N1_P2
     * T2_N1_P3
     * T2_N2_P0
     * T2_N2_P1
     * T2_N2_P2
     * T2_N2_P3
     * T2_N3
     * T3
     */
    public List<TestItem> getBoundaryAnalysisTestData() {
        List<TestItem> testItems = new ArrayList<>();

        /** T0. */

        Problem problem0 = new Problem(new ArrayList<>());
        Result result0 = Result.makeErrorResult(Problem.INVALID_SUBPROBLEMS_BOUNDS);
        testItems.add(new TestItem(problem0, result0, "T0. "));

        /** T1_NO. */

        List<Point> oneTest = new ArrayList<>();
        Problem problem1 = new Problem(Arrays.asList(oneTest));
        Result result1 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(problem1, result1, "T1_NO. "));

        /** T1_N1_P0. */

        List<Point> list2 = Arrays.asList(new Point(-1, -1));

        Problem problem2 = new Problem(Arrays.asList(list2));
        Result result2 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem2, result2, "T1_N1_P0. "));

        /** T1_N1_P1.  */

        List<Point> list3 = Arrays.asList(new Point(0, 0));

        Problem problem3 = new Problem(Arrays.asList(list3));
        Result result3 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem3, result3, "T1_N1_P1. "));


        /** T1_N1_P2.  */

        List<Point> list4 = Arrays.asList(new Point(50, 50));

        Problem problem4 = new Problem(Arrays.asList(list4));
        Result result4 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem4, result4, "T1_N1_P2. "));

        /** T1_N1_P3.  */

        List<Point> list5 = Arrays.asList(new Point(51, 51));

        Problem problem5 = new Problem(Arrays.asList(list5));
        Result result5 = Result.makeErrorResult((String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0)));
        testItems.add(new TestItem(problem5, result5, "T1_N1_P3. "));

        /** T1_N2_P0.  */

        List<Point> list6 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list6.add(new Point(i, i));
        }
        list6.get(0).setX(-1);
        list6.get(0).setY(-1);
        Problem problem6 = new Problem(Arrays.asList(list6));
        Result result6 = Result.makeErrorResult((String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0)));
        testItems.add(new TestItem(problem6, result6, "T1_N2_P0. "));


        /** T1_N2_P1.  */

        List<Point> list7 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list7.add(new Point(i, i));
        }
        list7.get(0).setX(0);
        list7.get(0).setY(0);
        Problem problem7 = new Problem(Arrays.asList(list7));
        Result result7 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem7, result7, "T1_N2_P1. "));

        /** T1_N2_P2.  */

        List<Point> list8 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list8.add(new Point(i, i));
        }
        list8.get(0).setX(50);
        list8.get(0).setY(50);
        Problem problem8 = new Problem(Arrays.asList(list8));
        Result result8 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem8, result8, "T1_N2_P2. "));

        /** T1_N2_P3.  */

        List<Point> list9 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list9.add(new Point(i, i));
        }
        list9.get(0).setX(51);
        list9.get(0).setY(51);
        Problem problem9 = new Problem(Arrays.asList(list9));
        Result result9 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem9, result9, "T1_N2_P3. "));

        /** T1_N3.  */

        List<Point> list9_T1_N3 = new ArrayList<>();
        for (int i = 0; i < 41; i++) {
            list9_T1_N3.add(new Point(i, i));
        }
        Problem problem9_T1_N3 = new Problem(Arrays.asList(list9_T1_N3));
        Result result9_T1_N3 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem9_T1_N3, result9_T1_N3, "T1_N3. "));

        /** T2_N0.  */

        List<Point> list9_T2_N0 = new ArrayList<>();
        Problem problem10_T2_N0 = new Problem(Arrays.asList(list9_T2_N0, list9_T2_N0, list9_T2_N0, list9_T2_N0, list9_T2_N0));
        Result result10_T2_N0 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem10_T2_N0, result10_T2_N0, "T2_N0. "));

        /** T2_N1_P0.  */

        List<Point> list10 = Arrays.asList(new Point(-1, -1));
        Problem problem10 = new Problem(Arrays.asList(list10, list10, list10, list10, list10));
        Result result10 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem10, result10, "T2_N1_P0. "));

        /** T2_N1_P1.  */

        List<Point> list11 = Arrays.asList(new Point(0, 0));
        Problem problem11 = new Problem(Arrays.asList(list11, list11, list11, list11, list11));
        Result result11 = Result.makeNormalResult(Arrays.asList("FALSE", "FALSE", "FALSE", "FALSE", "FALSE"));
        testItems.add(new TestItem(problem11, result11, "T2_N1_P1. "));

        /** T2_N1_P2.  */

        List<Point> list12 = Arrays.asList(new Point(50, 50));
        Problem problem12 = new Problem(Arrays.asList(list12, list12, list12, list11, list12));
        Result result12 = Result.makeNormalResult(Arrays.asList("FALSE", "FALSE", "FALSE", "FALSE", "FALSE"));
        testItems.add(new TestItem(problem12, result12, "T2_N1_P2. "));

        /** T2_N1_P3.  */

        List<Point> list13 = Arrays.asList(new Point(51, 51));
        Problem problem13 = new Problem(Arrays.asList(list13, list13, list13, list13, list13));
        Result result13 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem13, result13, "T1_N1_P3. "));

        /** T2_N2_P0.  */

        List<Point> list14 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list14.add(new Point(i, i));
        }
        list14.get(0).setX(-1);
        list14.get(0).setY(-1);
        Problem problem14 = new Problem(Arrays.asList(list14, list14, list14, list14, list14));
        Result result14 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem14, result14, "T2_N2_P0. "));

        /** T2_N2_P1.  */

        List<Point> list15 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list15.add(new Point(i, i));
        }
        list15.get(0).setX(0);
        list15.get(0).setY(0);
        Problem problem15 = new Problem(Arrays.asList(list15, list15, list15, list15, list15));
        Result result15 = Result.makeNormalResult(Arrays.asList("TRUE", "TRUE", "TRUE", "TRUE", "TRUE"));
        testItems.add(new TestItem(problem15, result15, "T2_N2_P1. "));

        /** T2_N2_P2.  */

        List<Point> list16 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list16.add(new Point(i, i));
        }
        list16.get(0).setX(50);
        list16.get(0).setY(50);
        Problem problem16 = new Problem(Arrays.asList(list16, list16, list16, list16, list16));
        Result result16 = Result.makeNormalResult(Arrays.asList("TRUE", "TRUE", "TRUE", "TRUE", "TRUE"));
        testItems.add(new TestItem(problem16, result16, "T2_N2_P2. "));

        /** T2_N2_P3.  */

        List<Point> list17 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list17.add(new Point(i, i));
        }
        list17.get(0).setX(51);
        list17.get(0).setY(51);
        Problem problem17 = new Problem(Arrays.asList(list17, list17, list17, list17, list17));
        Result result17 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem17, result17, "T2_N2_P3. "));

        /** T3.  */

        List<Point> list18 = new ArrayList<>();
        Problem problem18 = new Problem(Arrays.asList(list18, list18, list18, list18, list18, list18));
        Result result18 = Result.makeErrorResult(String.format(Problem.INVALID_SUBPROBLEMS_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem18, result18, "T3. "));


        return testItems;
    }


    /**
     * For generating test data based on category partitioning, we should consider the following possibilities:
     * T, which should belong to the interval [1, 5]:
     * T -> subproblems array is null
     * T0 -> value 0
     * T1 -> value 1
     * T2 -> value 5
     * T3 -> value 6
     * N, which should belong to the interval [1, 40]:
     * N -> the subproblem is null
     * N0 -> value 0
     * N1 -> value 1
     * N2 -> value 40
     * N3 -> value 41
     * Point coordinates which should belong to the interval [0, 50]:
     * P0 -> value -1
     * P1 -> value 0
     * P2 -> value 50
     * P3 -> value 51
     * <p>
     * T0
     * T1_N0
     * T1_N1_P0
     * T1_N1_P1
     * T1_N1_P2
     * T1_N1_P3
     * T1_N2_P0
     * T1_N2_P1
     * T1_N2_P2
     * T1_N2_P3
     * T1_N3
     * T2_N0
     * T2_N1_P0
     * T2_N1_P1
     * T2_N1_P2
     * T2_N1_P3
     * T2_N2_P0
     * T2_N2_P1
     * T2_N2_P2
     * T2_N2_P3
     * T2_N3
     * T3
     */
    public List<TestItem> getCategoryPartitioningTestData() {
        List<TestItem> testItems = new ArrayList<>();

        /**  T. */

        Problem problem = new Problem(null);
        Result result = Result.makeErrorResult(Problem.INVALID_SUBPROBLEMS_BOUNDS);
        testItems.add(new TestItem(problem, result, "T"));

        /** T0. */

        Problem problem0 = new Problem(new ArrayList<>());
        Result result0 = Result.makeErrorResult(Problem.INVALID_SUBPROBLEMS_BOUNDS);
        testItems.add(new TestItem(problem0, result0, "T0. "));

        /** T1_NO. */

        List<Point> oneTest = new ArrayList<>();
        Problem problem1 = new Problem(Arrays.asList(oneTest));
        Result result1 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(problem1, result1, "T1_NO. "));

        /** T1_N1_P0. */

        List<Point> list2 = Arrays.asList(new Point(-1, -1));

        Problem problem2 = new Problem(Arrays.asList(list2));
        Result result2 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem2, result2, "T1_N1_P0. "));

        /** T1_N1_P1.  */

        List<Point> list3 = Arrays.asList(new Point(0, 0));

        Problem problem3 = new Problem(Arrays.asList(list3));
        Result result3 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem3, result3, "T1_N1_P1. "));


        /** T1_N1_P2.  */

        List<Point> list4 = Arrays.asList(new Point(50, 50));

        Problem problem4 = new Problem(Arrays.asList(list4));
        Result result4 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem4, result4, "T1_N1_P2. "));

        /** T1_N1_P3.  */

        List<Point> list5 = Arrays.asList(new Point(51, 51));

        Problem problem5 = new Problem(Arrays.asList(list5));
        Result result5 = Result.makeErrorResult((String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0)));
        testItems.add(new TestItem(problem5, result5, "T1_N1_P3. "));

        /** T1_N2_P0.  */

        List<Point> list6 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list6.add(new Point(i, i));
        }
        list6.get(0).setX(-1);
        list6.get(0).setY(-1);
        Problem problem6 = new Problem(Arrays.asList(list6));
        Result result6 = Result.makeErrorResult((String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0)));
        testItems.add(new TestItem(problem6, result6, "T1_N2_P0. "));


        /** T1_N2_P1.  */

        List<Point> list7 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list7.add(new Point(i, i));
        }
        list7.get(0).setX(0);
        list7.get(0).setY(0);
        Problem problem7 = new Problem(Arrays.asList(list7));
        Result result7 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem7, result7, "T1_N2_P1. "));

        /** T1_N2_P2.  */

        List<Point> list8 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list8.add(new Point(i, i));
        }
        list8.get(0).setX(50);
        list8.get(0).setY(50);
        Problem problem8 = new Problem(Arrays.asList(list8));
        Result result8 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem8, result8, "T1_N2_P2. "));

        /** T1_N2_P3.  */

        List<Point> list9 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list9.add(new Point(i, i));
        }
        list9.get(0).setX(51);
        list9.get(0).setY(51);
        Problem problem9 = new Problem(Arrays.asList(list9));
        Result result9 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem9, result9, "T1_N2_P3. "));

        /** T1_N3.  */

        List<Point> list9_T1_N3 = new ArrayList<>();
        for (int i = 0; i < 41; i++) {
            list9_T1_N3.add(new Point(i, i));
        }
        Problem problem9_T1_N3 = new Problem(Arrays.asList(list9_T1_N3));
        Result result9_T1_N3 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem9_T1_N3, result9_T1_N3, "T1_N3. "));

        /** T2_N0.  */

        List<Point> list9_T2_N0 = new ArrayList<>();
        Problem problem10_T2_N0 = new Problem(Arrays.asList(list9_T2_N0, list9_T2_N0, list9_T2_N0, list9_T2_N0, list9_T2_N0));
        Result result10_T2_N0 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem10_T2_N0, result10_T2_N0, "T2_N0. "));

        /** T2_N1_P0.  */

        List<Point> list10 = Arrays.asList(new Point(-1, -1));
        Problem problem10 = new Problem(Arrays.asList(list10, list10, list10, list10, list10));
        Result result10 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem10, result10, "T2_N1_P0. "));

        /** T2_N1_P1.  */

        List<Point> list11 = Arrays.asList(new Point(0, 0));
        Problem problem11 = new Problem(Arrays.asList(list11, list11, list11, list11, list11));
        Result result11 = Result.makeNormalResult(Arrays.asList("FALSE", "FALSE", "FALSE", "FALSE", "FALSE"));
        testItems.add(new TestItem(problem11, result11, "T2_N1_P1. "));

        /** T2_N1_P2_1.  */

        List<Point> list12_1 = Arrays.asList(new Point(40, 40));
        Problem problem12_1 = new Problem(Arrays.asList(list12_1, list12_1, list12_1, list12_1, list12_1));
        Result result12_1 = Result.makeNormalResult(Arrays.asList("FALSE", "FALSE", "FALSE", "FALSE", "FALSE"));
        testItems.add(new TestItem(problem12_1, result12_1, "T2_N1_P2_1. "));

        /** T2_N1_P2.  */

        List<Point> list12 = Arrays.asList(new Point(50, 50));
        Problem problem12 = new Problem(Arrays.asList(list12, list12, list12, list11, list12));
        Result result12 = Result.makeNormalResult(Arrays.asList("FALSE", "FALSE", "FALSE", "FALSE", "FALSE"));
        testItems.add(new TestItem(problem12, result12, "T2_N1_P2. "));

        /** T2_N1_P3.  */

        List<Point> list13 = Arrays.asList(new Point(51, 51));
        Problem problem13 = new Problem(Arrays.asList(list13, list13, list13, list13, list13));
        Result result13 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem13, result13, "T1_N1_P3. "));

        /** T2_N2_P0.  */

        List<Point> list14 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list14.add(new Point(i, i));
        }
        list14.get(0).setX(-1);
        list14.get(0).setY(-1);
        Problem problem14 = new Problem(Arrays.asList(list14, list14, list14, list14, list14));
        Result result14 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem14, result14, "T2_N2_P0. "));

        /** T2_N2_P1.  */

        List<Point> list15 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list15.add(new Point(i, i));
        }
        list15.get(0).setX(0);
        list15.get(0).setY(0);
        Problem problem15 = new Problem(Arrays.asList(list15, list15, list15, list15, list15));
        Result result15 = Result.makeNormalResult(Arrays.asList("TRUE", "TRUE", "TRUE", "TRUE", "TRUE"));
        testItems.add(new TestItem(problem15, result15, "T2_N2_P1. "));

        /** T2_N2_P2.  */

        List<Point> list16 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list16.add(new Point(i, i));
        }
        list16.get(0).setX(50);
        list16.get(0).setY(50);
        Problem problem16 = new Problem(Arrays.asList(list16, list16, list16, list16, list16));
        Result result16 = Result.makeNormalResult(Arrays.asList("TRUE", "TRUE", "TRUE", "TRUE", "TRUE"));
        testItems.add(new TestItem(problem16, result16, "T2_N2_P2. "));

        /** T2_N2_P2_1.  */

        List<Point> list16_1 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list16_1.add(new Point(i, i));
        }
        list16.get(0).setX(30);
        list16.get(0).setY(30);
        Problem problem16_1 = new Problem(Arrays.asList(list16_1, list16_1, list16_1, list16_1, list16_1));
        Result result16_1 = Result.makeNormalResult(Arrays.asList("TRUE", "TRUE", "TRUE", "TRUE", "TRUE"));
        testItems.add(new TestItem(problem16_1, result16_1, "T2_N2_P2_1. "));

        /** T2_N2_P3.  */

        List<Point> list17 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list17.add(new Point(i, i));
        }
        list17.get(0).setX(51);
        list17.get(0).setY(51);
        Problem problem17 = new Problem(Arrays.asList(list17, list17, list17, list17, list17));
        Result result17 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem17, result17, "T2_N2_P3. "));

        /** T3.  */

        List<Point> list18 = new ArrayList<>();
        Problem problem18 = new Problem(Arrays.asList(list18, list18, list18, list18, list18, list18));
        Result result18 = Result.makeErrorResult(String.format(Problem.INVALID_SUBPROBLEMS_BOUNDS, 0, 0));
        testItems.add(new TestItem(problem18, result18, "T3. "));

        /** 3. T3_N0.  */
        List<Point> pointList19 = null;
        Problem problem19 = new Problem(Arrays.asList(pointList19));
        Result result19 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(problem19, result19, "T3_N0"));

        return testItems;

    }

    public List<TestItem> getStatementCoverageTests() {
        List<TestItem> testItems = new ArrayList<>();

        /** 42 -> 43 */
        List<Point> list1 = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            list1.add(new Point(0, 0));
        }
        Problem prob1 = new Problem(Arrays.asList(list1));
        Result res1 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(prob1, res1, "42 -> 43"));


        /** 42 -> 46 -> 47 -> 48*/
        List<Point> list2 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list2.add(new Point(100, 100));
        }
        Problem prob2 = new Problem(Arrays.asList(list2));
        Result res2 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob2, res2, "42 -> 46 -> 47 -> 48"));


        /** 52, 53 -> 54 -> 71*/
        List<Point> list8 = Arrays.asList(new Point(0, 0));
        Problem problem8 = new Problem(Arrays.asList(list8));
        Result result8 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem8, result8, "52, 53 -> 54 -> 71"));

        /** 52, 53 -> 54, 55 .. 60, 61, 62, 64*/
        List<Point> list9 = Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 2),
                new Point(0, 0),
                new Point(0, 1));

        Problem problem9 = new Problem(Arrays.asList(list9));
        Result result9 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem9, result9, "52, 53 -> 54, 55 .. 60, 61, 62, 64"));

        return testItems;
    }

    public List<TestItem> getDecisionCoverageTests() {

        List<TestItem> testItems = new ArrayList<>();

        /** Conditional line 42 - true */
        List<Point> list1 = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            list1.add(new Point(0, 0));
        }
        Problem prob1 = new Problem(Arrays.asList(list1));
        Result res1 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(prob1, res1, "Conditional line 42 - true"));

        /** Conditional line 42 - false */
        List<Point> list2 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list2.add(new Point(100, 100));
        }
        Problem prob2 = new Problem(Arrays.asList(list2));
        Result res2 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob2, res2, "Conditional line 42 - false"));

        /** Conditional line 47 - true */
        List<Point> list3 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list3.add(new Point(100, 100));
        }

        Problem prob3 = new Problem(Arrays.asList(list3));
        Result res3 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob3, res3, "Conditional line 47 - true"));

        /**  Conditional line 47 - false */
        List<Point> list4 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list4.add(new Point(20, 20));
        }

        Problem prob4 = new Problem(Arrays.asList(list4));
        Result res4 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(prob4, res4, "Conditional line 47 - false"));


        /** Conditional line 54 - false */
        List<Point> list8 = Arrays.asList(new Point(0, 0));
        Problem problem8 = new Problem(Arrays.asList(list8));
        Result result8 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem8, result8, "Conditional line 54 - false"));

        /** Conditional line 54 - true
         *  Conditional line 55 - true, false
         *  Conditional line 59 - true, false
         *  Conditional line 54 - true, false
         *  Conditional line 61 - true, false*/
        List<Point> list9 = Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 2),
                new Point(0, 0),
                new Point(0, 1));

        Problem problem9 = new Problem(Arrays.asList(list9));
        Result result9 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem9, result9, "Conditional line 54 - true\n" +
                "         *  Conditional line 55 - true, false\n" +
                "         *  Conditional line 59 - true, false\n" +
                "         *  Conditional line 54 - true, false\n" +
                "         *  Conditional line 61 - true, false"));

        return testItems;
    }

    public List<TestItem> getConditionalCoverageTests() {

        List<TestItem> testItems = new ArrayList<>();

        /** points.size() > 40 true */
        List<Point> list0 = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            list0.add(new Point(0, 0));
        }
        Problem prob0 = new Problem(Arrays.asList(list0));
        Result res0 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(prob0, res0, "points.size() > 40 true"));

        /** points.size() < 1 true */
        List<Point> list1 = new ArrayList<>();
        Problem prob1 = new Problem(Arrays.asList(list1));
        Result res1 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(prob1, res1, "points.size() < 1 true"));

        /** points.size() is null true */
        List<Point> list2 = null;
        Problem prob2 = new Problem(Arrays.asList(list2));
        Result res2 = Result.makeErrorResult(String.format(Problem.INVALID_POINTS_BOUNDS, 0));
        testItems.add(new TestItem(prob2, res2, "points.size() is null true"));


        /** points is  null, points.size() < 1, points.size() > 40 false */
        List<Point> list3 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list3.add(new Point(100, 100));
        }
        Problem prob3 = new Problem(Arrays.asList(list3));
        Result res3 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob3, res3, "points is  null, points.size() < 1, points.size() > 40 false"));


        /** 46, points.size() always true
         *  47, points.get(i).getX() < 0 true */
        List<Point> list4 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list4.add(new Point(-1, 2));
        }
        Problem prob4 = new Problem(Arrays.asList(list4));
        Result res4 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob4, res4, "47, points.get(i).getX() < 0 tru"));

        /** 47, points.get(i).getX() > 50 true */
        List<Point> list5 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list5.add(new Point(51, 2));
        }
        Problem prob5 = new Problem(Arrays.asList(list5));
        Result res5 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob5, res5, " points.get(i).getX() > 50 true"));

        /** 47, points.get(i).getY() < 0 true */
        List<Point> list6 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list6.add(new Point(2, -1));
        }
        Problem prob6 = new Problem(Arrays.asList(list6));
        Result res6 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob6, res6, "points.get(i).getY() < 0 true"));


        /** 47, points.get(i).getY() > 50 true */
        List<Point> list7 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list7.add(new Point(2, 51));
        }
        Problem prob7 = new Problem(Arrays.asList(list7));
        Result res7 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob7, res7, "points.get(i).getY() > 50 true"));


        /** 47, points.get(i).getX() < 0 true, points.get(i).getY() < 0 true */
        List<Point> list8 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list8.add(new Point(-1, -1));
        }
        Problem prob8 = new Problem(Arrays.asList(list8));
        Result res8 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob8, res8, "points.get(i).getX() < 0 true, points.get(i).getY() < 0 true)"));


        /**  47, points.get(i).getX() > 50 true, points.get(i).getY() < 0 true */
        List<Point> list9 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list9.add(new Point(51, -1));
        }
        Problem prob9 = new Problem(Arrays.asList(list9));
        Result res9 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob9, res9, "points.get(i).getX() > 50 true, points.get(i).getY() < 0 true"));


        /** 47, points.get(i).getX() < 0 true, points.get(i).getY() > 50 true */
        List<Point> list10 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list10.add(new Point(-1, 51));
        }
        Problem prob10 = new Problem(Arrays.asList(list10));
        Result res10 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob10, res10, "points.get(i).getX() < 0 true, points.get(i).getY() > 50 true"));


        /** 47, points.get(i).getX() > 50 true, points.get(i).getY() > 50 true */
        List<Point> list11 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list11.add(new Point(51, 51));
        }
        Problem prob11 = new Problem(Arrays.asList(list11));
        Result res11 = Result.makeErrorResult(String.format(Problem.INVALID_COORDINATES_BOUNDS, 0, 0));
        testItems.add(new TestItem(prob11, res11, "points.get(i).getX() > 50 true, points.get(i).getY() > 50 true"));

        /**   */
        List<Point> list12 = Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 2),
                new Point(0, 0),
                new Point(0, 1));

        Problem problem12 = new Problem(Arrays.asList(list12));
        Result result12 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem12, result12, "52, 53 -> 54, 55 .. 60, 61, 62, 64"));


        /** */
        List<Point> list13 = Arrays.asList(new Point(0, 0));
        Problem problem13 = new Problem(Arrays.asList(list13));
        Result result13 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem13, result13, "52, 53 -> 54 -> 71"));

        return testItems;
    }

    /**
     * Nr de circuite independente este dat de formula:
     * V(G) = e – n + 1
     * e = 19
     * n = 16 => V(G) = 19 - 16 + 1 => V(G) = 4 c.i.
     * <p>
     * Circuitele independente:
     * 46 -> 47 -> 46
     * 54 -> 55 -> 56,57,58 -> 59 -> 66 -> 55 -> 54
     * 54 -> 55 -> 56,57,58 -> 59 -> 60 -> 61 -> 64 -> 55 -> 54
     * <p>
     * Complexitatea folosind formula lui McCabe; pentru un singur program (metodă), P este întotdeauna egal cu 1.
     * V(G) = e − n + 2
     * V(G) = 19 - 16 + 2 => V(G) = 5
     */


    public List<TestItem> getIndependentCircuitTests() {

        /**  Circuitul independent 46 -> 47 -> 46 */

        List<TestItem> testItems = new ArrayList<>();

        List<Point> list1 = Arrays.asList(new Point(0, 0));
        Problem problem1 = new Problem(Arrays.asList(list1));
        Result result1 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(problem1, result1, "Circuitul independent 46 -> 47 -> 46"));

        /**  Circuitul independent 54 -> 55 -> 56,57,58 -> 59 -> 66 -> 55 -> 54 +
         *   + Circuitul independent 54 -> 55 -> 56,57,58 -> 59 -> 60 -> 61 -> 64 -> 55 -> 54 */

        List<Point> list12 = Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 2),
                new Point(0, 0),
                new Point(0, 1));

        Problem problem12 = new Problem(Arrays.asList(list12));
        Result result12 = Result.makeNormalResult(Arrays.asList("TRUE"));
        testItems.add(new TestItem(problem12, result12, "Circuitul independent 54 -> 55 -> 56,57,58 -> 59 -> 66 -> 55 -> 54 +\n" +
                "         *   + Circuitul independent 54 -> 55 -> 56,57,58 -> 59 -> 60 -> 61 -> 64 -> 55 -> 54"));

        return testItems;
    }

    public List<TestItem> getMutationCoverageTests() {

        List<TestItem> testItems = new ArrayList<>();

        /** Line 61: oldVal == 2   ->> oldVal != 2  */
        List<Point> list0 = new ArrayList<>();
        list0.add(new Point(1, 3));
        list0.add(new Point(4, 2));
        list0.add(new Point(3, 6));
        list0.add(new Point(6, 5));
        Problem prob0 = new Problem(Arrays.asList(list0));
        Result res0 = Result.makeNormalResult(Arrays.asList("FALSE"));
        testItems.add(new TestItem(prob0, res0, "Line 61: oldVal == 2  --> oldVal != 2"));

        return testItems;
    }
}
