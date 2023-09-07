package com.repo.githubRepoList.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExceptionMessage {
    private int responseCode;
    private String whyHasItHappened;
}
