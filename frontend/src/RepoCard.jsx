export default function RepoCard({ repo }) {
    return (
        <div className="border rounded-xl p-4 shadow">
            <a href={repo.htmlUrl} target="_blank" rel="noreferrer"
               className="text-lg font-semibold text-blue-600">{repo.name}</a>
            <p className="text-sm text-gray-500 mb-2">{repo.description}</p>
            <div className="text-sm flex gap-4">
                <span>⭐ {repo.stars}</span>
                <span>🍴 {repo.forks}</span>
                <span>🧠 {repo.language || "N/A"}</span>
                <span>🕓 {new Date(repo.updatedAt).toLocaleDateString()}</span>
            </div>
        </div>
    );
}
