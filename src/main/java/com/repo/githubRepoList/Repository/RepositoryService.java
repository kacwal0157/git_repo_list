package com.repo.githubRepoList.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryService {
    private final String GITHUB_API_URL = "https://api.github.com/users/";
    private final RestTemplate restTemplate;

    @Autowired
    public RepositoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Repository> getRepositories(String username) {
        String apiUrl = GITHUB_API_URL + username + "/repos";

        // Wysyłamy zapytanie GET i oczekujemy na odpowiedź w formie listy obiektów Repository
        ResponseEntity<List<Repository>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Repository>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return new ArrayList<>(); // Zwracamy pustą listę w przypadku niepowodzenia
        }
    }
}
