package com.repo.githubRepoList.Repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> getRepositories(String username, String acceptHeader) {
        String apiUrl = GITHUB_API_URL + username + "/repos";

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            List<Repository> repositories = new ArrayList<>();

            for (Map<String, Object> repoData : response.getBody()) {
                String repositoryName = (String) repoData.get("name");
                Map<String, Object> ownerData = (Map<String, Object>) repoData.get("owner");
                String ownerLogin = (String) ownerData.get("login");

                Repository repository = new Repository(repositoryName, ownerLogin, "branchName", 1L);
                repositories.add(repository);
            }

            return ResponseEntity.ok(repositories);
        } else {
            // Obsługa przypadku niepowodzenia - brak użytkownika
            int responseCode = response.getStatusCodeValue();
            String errorMessage = "An error occurred while fetching repositories";

            if (responseCode == 404) {
                errorMessage = "User not found";
            }

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", responseCode);
            errorResponse.put("Message", errorMessage);

            return ResponseEntity.status(responseCode)
                    .body(errorResponse);
        }
    }
}
