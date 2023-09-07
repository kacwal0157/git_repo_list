package com.repo.githubRepoList.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class GitHubUser {

    private String name;
    private GitHubOwner owner;

}
