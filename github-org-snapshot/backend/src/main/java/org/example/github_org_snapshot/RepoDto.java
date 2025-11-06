package org.example.github_org_snapshot;

public record RepoDto(
        String name,
        String htmlUrl,
        int stars,
        int forks,
        String language,
        String updatedAt,
        String description
) {}