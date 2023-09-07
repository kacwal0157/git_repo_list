package com.repo.githubRepoList.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Commit {
    private String sha;
    private String url;
}
