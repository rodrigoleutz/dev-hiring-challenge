package br.com.uware.core.model


/**
 * GitHubSearch
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 07/05/2022 - Initial release.
 */
data class GitHubSearch(
    val total_count: Int?,
    val incomplete_results: Boolean?,
    val items: ArrayList<GitHubRepository>?
)