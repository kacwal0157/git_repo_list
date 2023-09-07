package com.repo.githubRepoList.repository;

import com.repo.githubRepoList.exception.UnsupportedAcceptHeaderException;
import com.repo.githubRepoList.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class RepositoryController {
    RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping(path = "{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username, @RequestHeader("Accept") String accept) throws UnsupportedAcceptHeaderException, UserNotFoundException {
        repositoryService.validateHeader(accept);
        return repositoryService.getRepositories(username);
    }
}
