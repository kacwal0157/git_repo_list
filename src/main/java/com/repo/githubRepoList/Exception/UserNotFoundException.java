package com.repo.githubRepoList.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String name) {
        super(name);
    }
}
