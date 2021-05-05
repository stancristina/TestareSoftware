package org.stanc.utils;

import java.util.List;

public class Result {

    private ResultType resultType;
    private List<String> value;
    private String errorMessage;

    private Result() {};

    public ResultType getResultType() {
        return resultType;
    }

    public List<String> getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static Result makeErrorResult(String errorMessage) {
        Result result = new Result();
        result.resultType = ResultType.EXCEPTION;
        result.value = null;
        result.errorMessage = errorMessage;
        return result;
    }

    public static Result makeNormalResult(List<String> value) {
        Result result = new Result();
        result.resultType = ResultType.VALUE;
        result.value = value;
        result.errorMessage = null;
        return result;
    }


}
