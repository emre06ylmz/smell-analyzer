package com.eylmz.master.sonar.client.exception;

public class GithubException extends Exception {
    public GithubException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}