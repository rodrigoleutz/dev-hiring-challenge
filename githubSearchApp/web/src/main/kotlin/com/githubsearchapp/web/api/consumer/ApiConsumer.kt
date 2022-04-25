package com.githubsearchapp.web.api.consumer

import com.githubsearchapp.web.api.model.GitHubSearch
import com.githubsearchapp.web.api.service.GitHubSearchService
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * ApiConsumer
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 22 - Initial release.
 */
@Component
class ApiConsumer {

    @Autowired
    lateinit var gitHubSearchService: GitHubSearchService

    /**
     * getQuery
     * Get search query in github.
     * @param query String
     * @return Json String or null
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 22 - Initial release.
     */
    private fun getQuery(query: String): String? {
        return try {
            val doc = URL("${BASE_URL}$query").openConnection() as HttpURLConnection
            doc.connectTimeout = 5000
            convertStreamToString(doc.inputStream)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * convertStreamToString
     * Convert inputStream in String.
     * @param inputStream InputStream
     * @return String
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 22 - Initial release.
     */
    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(inputStream.reader())
        val content = StringBuilder()
        var line = reader.readLine()
        reader.use { _reader ->
            while (line != null) {
                content.append(line)
                line = _reader.readLine()
            }
        }
        return content.toString()
    }

    /**
     * queryBuilder
     * Returns the query to search.
     * @param sort String to sorted by def=stars
     * @param order String to order by def=desc
     * @return String of query.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 24 - Initial release.
     */
    private fun queryBuilder(
        search: String?,
        sort: String = "stars",
        order: String = "desc"
    ): String? {
        return try {
            var href = search
            href += "&languages=$LANGUAGES"
            href += "&sort=$sort"
            href += "&order=$order"
            getQuery("repositories?q=$href")
        } catch (e: Exception) {
            null
        }
    }

    fun getSearch(search: String): GitHubSearch? {
        val searchGitExist = gitHubSearchService.getBySearchString(search)
        if(searchGitExist != null){
            println("Get from database")
            return searchGitExist
        }
        val jsonString = ApiConsumer().queryBuilder(search)
        val jsonObject = Gson().fromJson(jsonString, JsonObject::class.java)
        val gitHubSearch = GitHubSearch.fromJson(
            search,
            jsonObject
        )
        gitHubSearchService.create(gitHubSearch)
        return gitHubSearch
    }

    companion object {
        /**
         * BASE_URL
         * Constant for api url.
         * @return https://api.github.com/
         * @author Rodrigo Leutz
         * @version 1.0.0 - 2022 04 24 - Initial release.
         */
        private const val BASE_URL = "https://api.github.com/search/"

        /**
         * LANGUAGES
         */
        private const val LANGUAGES = "kotlin"
    }
}