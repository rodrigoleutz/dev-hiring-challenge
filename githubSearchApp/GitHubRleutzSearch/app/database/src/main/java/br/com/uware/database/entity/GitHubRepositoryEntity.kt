package br.com.uware.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.uware.core.model.GitHubRepository
import br.com.uware.core.model.GitHubUser


/**
 * GitHubRepositoryEntity
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 08/05/2022 - Initial release.
 */
@Entity(tableName = "github_repositories")
class GitHubRepositoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String?,
    val name: String?,
    val full_name: String?,
    val description: String?,
    val author: String?,
    val url: String?,
    val owner: GitHubUser?,
    val language: String?,
    val stargazers_count: Int?,
    val watchers_count: Int?,
) {
    companion object {
        fun fromGitHubRepository(gitHubRepository: br.com.uware.githubrleutzsearch.framework.repository.GitHubRepository) =
            GitHubRepositoryEntity(
                gitHubRepository.id,
                gitHubRepository.name,
                gitHubRepository.full_name,
                gitHubRepository.description,
                gitHubRepository.author,
                gitHubRepository.url,
                gitHubRepository.owner,
                gitHubRepository.language,
                gitHubRepository.stargazers_count,
                gitHubRepository.watchers_count
            )
    }

    fun toGitHubRepository() = GitHubRepository(
        id,
        name,
        full_name,
        description,
        author,
        url,
        owner,
        language,
        stargazers_count,
        watchers_count
    )
}