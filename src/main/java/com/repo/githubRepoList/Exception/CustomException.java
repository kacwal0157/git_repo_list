package com.repo.githubRepoList.Exception;

public class CustomException {
    private int responseCode;
    private String whyHasItHappened;

    public CustomException() {
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getWhyHasItHappened() {
        return whyHasItHappened;
    }

    public void setWhyHasItHappened(String whyHasItHappened) {
        this.whyHasItHappened = whyHasItHappened;
    }
}
