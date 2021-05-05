package org.stanc.utils;

import org.stanc.Problem;

public class TestItem {

    private Problem problem;
    private Result result;
    private String description;

    public TestItem(Problem problem, Result result, String description) {
        this.problem = problem;
        this.result = result;
        this.description = description;
    }

    public Problem getProblem() {
        return problem;
    }

    public Result getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }
}
