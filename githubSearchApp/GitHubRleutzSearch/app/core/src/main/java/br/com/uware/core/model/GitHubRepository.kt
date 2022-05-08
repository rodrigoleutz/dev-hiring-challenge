package br.com.uware.core.model


/**
 * GitHub
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 02/05/2022 - Initial release.
 */
data class GitHubRepository(
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
)