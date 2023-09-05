package com.repo.githubRepoList.Repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RepositoryService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String GITHUB_API_URL = "https://api.github.com/users/";

    public ResponseEntity<?> getRepositories(String username) {
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

            List<Map<String, Object>> branchesForRepository = getBranchesForRepository(username, repositoryName);
            Map<String, String> branches = new HashMap<>();

            for (Map<String, Object> branchData : branchesForRepository) {
                String branchName = (String) branchData.get("name");
                String commitSHA = (String) ((Map<?, ?>) branchData.get("commit")).get("sha");

                branches.put(branchName, commitSHA);
            }

            Repository repository = new Repository(repositoryName, ownerLogin, branches);
            repositories.add(repository);
        }

        return ResponseEntity.ok(repositories);
    }

    private List<Map<String, Object>> getBranchesForRepository(String username, String repositoryName) {
        String apiUrl = GITHUB_API_URL + username + "/" + repositoryName + "/branches";

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return Objects.requireNonNull(response.getBody());
    }
}
