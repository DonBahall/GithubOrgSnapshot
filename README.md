# GitHub Org Snapshot

A simple full-stack test project: a single-page app that displays the top repositories of a selected GitHub organization.  
The Java backend calls the public GitHub REST API and returns simplified JSON data for the frontend.  
No authentication or API key required.

- Backend exposes:
    - `GET /api/health` → `{ "status": "OK" }`
    - `GET /api/org/{org}/repos?limit=5&sort=stars|updated`

- Frontend:
    - Input for organization (default `vercel` or `spring-projects`)
    - Sort selector (stars / updated)
    - Limit (1–20, default 5)
    - “Load” button, cards for repos

See `backend/README.md` and `frontend/README.md` for run & configuration instructions.
