const API_BASE = import.meta.env.VITE_API_BASE || "http://localhost:8080/api";

export async function fetchRepos(org, limit, sort) {
    const res = await fetch(`${API_BASE}/org/${org}/repos?limit=${limit}&sort=${sort}`);
    if (!res.ok) throw new Error(`Error: ${res.status}`);
    return res.json();
}
