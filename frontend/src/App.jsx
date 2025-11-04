import { useState } from "react";
import { fetchRepos } from "./api";
import RepoCard from "./RepoCard";

export default function App() {
    const [org, setOrg] = useState("vercel");
    const [sort, setSort] = useState("stars");
    const [limit, setLimit] = useState(5);
    const [repos, setRepos] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    async function load() {
        setLoading(true);
        setError("");
        try {
            const data = await fetchRepos(org, limit, sort);
            setRepos(data);
        } catch (e) {
            setError(e.message);
        } finally {
            setLoading(false);
        }
    }

    const handleChange = (e) => {
        const value = e.target.value;
        const num = Number(value);

        if (isNaN(num)) {
            setError("Set number");
            setLimit("");
            return;
        }

        if (num < 1 || num > 20) {
            setError("Value should be from 1 to 20");
        } else {
            setError("");
        }

        setLimit(value);
    };

    return (
        <div className="container">
            <h1>🚀 GitHub Org Snapshot</h1>

            <div className="controls">
                <input
                    value={org}
                    onChange={(e) => setOrg(e.target.value)}
                    placeholder="Organization"
                />
                <select value={sort} onChange={(e) => setSort(e.target.value)}>
                    <option value="stars">⭐ Stars</option>
                    <option value="updated">🕓 Updated</option>
                </select>
                <input
                    type="number"
                    value={limit}
                    min="1"
                    max="20"
                    onChange={handleChange}
                />
                <button onClick={load} disabled={!!error || loading}>Load</button>
            </div>

            {loading && <p className="info">Loading...</p>}
            {error && <p className="error">❌ {error}</p>}
            {!loading && !error && repos.length === 0 && (
                <p className="info">No repositories found.</p>
            )}

            <div className="repo-grid">
                {repos.map((repo) => (
                    <RepoCard key={repo.name} repo={repo} />
                ))}
            </div>
        </div>
    );
}

