package org.stanc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.stanc.utils.Result;
import org.stanc.utils.ResultType;
import org.stanc.utils.TestDataProvider;
import org.stanc.utils.TestItem;

import java.util.List;
import java.util.stream.Collectors;

public class ProblemTest {

    private static TestDataProvider testDataProvider;

    @BeforeAll
    public static void initializeUtilities() {
        testDataProvider = new TestDataProvider();
    }

    @TestFactory
    public Iterable<DynamicTest> equivalencePartitionTest() {
        return prepareDynamicTestData(testDataProvider.getEquivalencePartitioningTestData());
    }

    @TestFactory
    public Iterable<DynamicTest> getBoundaryAnalysisTestData() {
        return prepareDynamicTestData(testDataProvider.getBoundaryAnalysisTestData());
    }

    @TestFactory
    public Iterable<DynamicTest> getCategoryPartitioningTestData() {
        return prepareDynamicTestData(testDataProvider.getCategoryPartitioningTestData());
    }

    @TestFactory
    public Iterable<DynamicTest> getStatementCoverageTests() {
        return prepareDynamicTestData(testDataProvider.getStatementCoverageTests());
    }

    @TestFactory
    public Iterable<DynamicTest> getDecisionCoverageTests() {
        return prepareDynamicTestData(testDataProvider.getDecisionCoverageTests());
    }

    @TestFactory
    public Iterable<DynamicTest> getConditionalCoverageTests() {
        return prepareDynamicTestData(testDataProvider.getConditionalCoverageTests());
    }

    @TestFactory
    public Iterable<DynamicTest> getIndependentCircuitTests() {
        return prepareDynamicTestData(testDataProvider.getIndependentCircuitTests());
    }
    @TestFactory
    public Iterable<DynamicTest> getMutationCoverageTests() {
        return prepareDynamicTestData(testDataProvider.getMutationCoverageTests());
    }

    private Iterable<DynamicTest> prepareDynamicTestData(List<TestItem> testData) {
        return testData.stream().map(testItem -> {
            Problem p = testItem.getProblem();
            Result r = testItem.getResult();
            String description = testItem.getDescription();
            return DynamicTest.dynamicTest(description, () -> {
                if (r.getResultType() == ResultType.EXCEPTION) {
                    Exception exception = Assertions.assertThrows(RuntimeException.class, p::solve);
                    Assertions.assertEquals(r.getErrorMessage(), exception.getMessage());
                } else {
                    List<String> expected = r.getValue();
                    List<String> actual = p.solve();
                    Assertions.assertEquals(expected.size(), actual.size(), "Expected number of results is different than the actual one");
                    for (int i = 0; i < expected.size(); i++) {
                        Assertions.assertEquals(expected.get(i), actual.get(i), "Difference at subproblem " +
                                i + ". Expected: " + expected.get(i) + ". Actual: " + actual.get(i));
                    }
                }
            });
        }).collect(Collectors.toList());
    }

}
