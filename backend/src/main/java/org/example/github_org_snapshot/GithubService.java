package org.example.github_org_snapshot;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GithubService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<org.example.github_org_snapshot.RepoDto> getRepos(String org, int limit, String sort) {
        if(limit < 0 || limit >= 20) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect value for 'limit' (must be 1–20)");
        String url = "https://api.github.com/orgs/" + org + "/repos?per_page=100";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "github-org-snapshot");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {}
        );

        List<Map<String, Object>> repos = response.getBody();
        if (repos == null) return List.of();

        Comparator<Map<String, Object>> comparator =
                "updated".equalsIgnoreCase(sort)
                        ? Comparator.comparing(r -> (String) r.get("updated_at"))
                        : Comparator.comparingInt(r -> ((Number) r.get("stargazers_count")).intValue());

        return repos.stream()
                .sorted(comparator.reversed())
                .limit(limit)
                .map(r -> new org.example.github_org_snapshot.RepoDto(
                        (String) r.get("name"),
                        (String) r.get("html_url"),
                        ((Number) r.get("stargazers_count")).intValue(),
                        ((Number) r.get("forks_count")).intValue(),
                        (String) r.get("language"),
                        (String) r.get("updated_at"),
                        (String) r.get("description")
                ))
                .collect(Collectors.toList());
    }
}
