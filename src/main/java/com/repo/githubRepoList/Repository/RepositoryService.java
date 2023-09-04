package com.repo.githubRepoList.Repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RepositoryService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> getRepositories(String username) {
        String GITHUB_API_URL = "https://api.github.com/users/";
        String apiUrl = GITHUB_API_URL + username + "/repos";

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        List<Repository> repositories = new ArrayList<>();

        for (Map<String, Object> repoData : Objects.requireNonNull(response.getBody())) {
            String repositoryName = (String) repoData.get("name");
            String ownerLogin = (String) ((Map<?, ?>) repoData.get("owner")).get("login");
            //branch name
            //commitSHA

            Repository repository = new Repository(repositoryName, ownerLogin, "branchName", 1L);
            repositories.add(repository);
        }

        return ResponseEntity.ok(repositories);
    }
}
