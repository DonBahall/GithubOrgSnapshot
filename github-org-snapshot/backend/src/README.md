# GitService – Backend

## Run Locally
```bash
cd ..
mvn spring-boot:run
```
### Overview
The backend provides two endpoints:
- `GET /api/health` → returns `{ "status": "OK" }`
- `GET /api/org/{org}/repos?limit=5&sort=stars|updated`

It fetches repositories from GitHub’s public API:
https://api.github.com/orgs/{org}/repos?per_page=100
and returns simplified JSON containing:
- `name`, `html_url`, `stargazers_count`, `forks_count`, `language`, `updated_at`, `description`

### Features
- Sort by `stars` (default) or `updated`
- Limit results (`limit`, default = 5, max = 20)
- Adds a `User-Agent` header in GitHub requests (required by GitHub)
- 
### Example Response

```json
{
  "org": "vercel",
  "limit": 5,
  "sort": "stars",
  "results": [
    {
      "name": "next.js",
      "html_url": "https://github.com/vercel/next.js",
      "stars": 120000,
      "forks": 20000,
      "language": "TypeScript",
      "updated_at": "2025-11-05T12:34:56Z",
      "description": "The React Framework"
    }
  ]
}
```