package com.repo.githubRepoList.repository;

import com.repo.githubRepoList.exception.UnsupportedAcceptHeaderException;
import com.repo.githubRepoList.exception.UserNotFoundException;
import com.repo.githubRepoList.model.Branch;
import com.repo.githubRepoList.model.GitHubUser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RepositoryService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String GITHUB_API_URL = "https://api.github.com/users/";
    private final String GITHUB_BRANCH_URL = "https://api.github.com/repos/";

    public ResponseEntity<?> getRepositories(String username) throws UserNotFoundException {
        String apiUrl = GITHUB_API_URL + username + "/repos";

        try {
            ResponseEntity<List<GitHubUser>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            List<Repository> repositories = new ArrayList<>();

            for (GitHubUser repoData : Objects.requireNonNull(response.getBody())) {
                String repositoryName = repoData.getName();
                String ownerLogin = repoData.getOwner().getLogin();
                List<Branch> branchesForRepository = getBranchesForRepository(username, repositoryName);
                Map<String, String> branches = new HashMap<>();

                for (Branch branchData : branchesForRepository) {
                    String branchName = branchData.getName();
                    String commitSHA = branchData.getCommit().getSha();

                    branches.put(branchName, commitSHA);
                }

                Repository repository = new Repository(repositoryName, ownerLogin, branches);
                repositories.add(repository);
            }

            return ResponseEntity.ok(repositories);
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException("User with name " + username + " is not found");
        }
    }

    private List<Branch> getBranchesForRepository(String username, String repositoryName) {
        String apiUrl = GITHUB_BRANCH_URL + username + "/" + repositoryName + "/branches";

        ResponseEntity<List<Branch>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        return Objects.requireNonNull(response.getBody());
    }

    public void validateHeader(String acceptHeader) throws UnsupportedAcceptHeaderException {
        if ("application/xml".equals(acceptHeader)) {
            throw new UnsupportedAcceptHeaderException("The accept header is not supported");
        }
    }
}
