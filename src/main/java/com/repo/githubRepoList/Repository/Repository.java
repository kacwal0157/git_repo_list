package com.repo.githubRepoList.Repository;

public class Repository {
    String repositoryName;
    String ownerLogin;
    String branchName;
    Long commitSha;

    public Repository(String repositoryName, String ownerLogin, String branchName, Long commitSha) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branchName = branchName;
        this.commitSha = commitSha;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public String getBranchName() {
        return branchName;
    }

    public Long getCommitSha() {
        return commitSha;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setCommitSha(Long commitSha) {
        this.commitSha = commitSha;
    }
}
