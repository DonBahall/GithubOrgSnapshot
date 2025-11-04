package org.example.github_org_snapshot;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RepoController {

    private final GithubService githubService;

    public RepoController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK");
    }

    @GetMapping("/org/{org}/repos")
    public List<RepoDto> getRepos(
            @PathVariable String org,
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "stars") String sort
    ) {
        return githubService.getRepos(org, limit, sort);
    }
}
