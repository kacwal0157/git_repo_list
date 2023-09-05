package com.repo.githubRepoList.Repository;

import java.util.Map;

public class Repository {
    String repositoryName;
    String ownerLogin;

    Map<String, String> branches;

    public Repository(String repositoryName, String ownerLogin, Map<String, String> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public Map<String, String> getBranches() {
        return branches;
    }

    public void setBranches(Map<String, String> branches) {
        this.branches = branches;
    }
}
