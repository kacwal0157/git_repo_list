package com.repo.githubRepoList.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Repository {
    String repositoryName;
    String ownerLogin;
    Map<String, String> branches;
}
