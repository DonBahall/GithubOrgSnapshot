package org.example.gitservice;

public record RepoDto(
        String name,
        String htmlUrl,
        int stars,
        int forks,
        String language,
        String updatedAt,
        String description
) {}