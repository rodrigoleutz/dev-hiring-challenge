package br.com.uware.retrofit.service

import br.com.uware.core.model.GitHubSearch
import retrofit2.Response


/**
 * ApiHelper
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 02/05/2022 - Initial release.
 */
class ApiHelper(private val apiService: ApiService) {
    suspend fun getGitHubRepository(
        search: String,
        languages: List<String>,
        sort: String,
        order: String
    ): Response<GitHubSearch> {
        var query = search.replace(" ", "+")
        var languageString = "+language:"
        for (language in languages) {
            languageString +=
                if (languages.last() == language) language
                else "$language+"
        }
        query += languageString
        return apiService.getGitHubRepository(query, sort, order)
    }
}