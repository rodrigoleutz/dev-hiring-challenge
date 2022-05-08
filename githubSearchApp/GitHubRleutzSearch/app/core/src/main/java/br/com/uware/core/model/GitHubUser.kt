package br.com.uware.core.model


/**
 * GitHubUser
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 07/05/2022 - Initial release.
 */
data class GitHubUser(
    val id: String?,
    val login: String?,
    val avatar_url: String?,
    val html_url: String?
)