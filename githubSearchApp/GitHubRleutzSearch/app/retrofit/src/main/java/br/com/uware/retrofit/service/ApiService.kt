package br.com.uware.retrofit.service

import br.com.uware.core.Constants
import br.com.uware.core.model.GitHubSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
 * ApiService
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 02/05/2022 - Initial release.
 */
interface ApiService {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("repositories")
    suspend fun getGitHubRepository(
        @Query("q", encoded = true) search: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        ): Response<GitHubSearch>
}