package com.foodie.controller;

public class QueryResult<T> {
    public boolean success;
    public T data;
    public String message;

    private void setSuccess(boolean value) {
        this.success = value;
    }

    private void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QueryResult(boolean success, T data) {
        setSuccess(success);
        setData(data);
    }

    public QueryResult(boolean success, T data, String message) {
        setSuccess(success);
        setData(data);
        setMessage(message);
    }

    public static <K> QueryResult<K> createFromSuccess(K result) {
        return new QueryResult<K>(true, result);
    }

    public static QueryResult<?> createFromFailure(String exceptionString) {
        return new QueryResult<String>(false, null, exceptionString);
    }

}
