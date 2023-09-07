package com.repo.githubRepoList.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Branch {
    private String name;
    private Commit commit;
}
