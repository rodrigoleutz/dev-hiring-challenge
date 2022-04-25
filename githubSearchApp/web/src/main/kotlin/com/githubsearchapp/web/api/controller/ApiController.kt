package com.githubsearchapp.web.api.controller


import com.githubsearchapp.web.api.consumer.ApiConsumer
import com.githubsearchapp.web.api.model.GitHubSearch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * ApiController
 * Api for search in github and save in database.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 22 - Initial release.
 */
@RestController
@RequestMapping("/api")
final class ApiController {

    @Autowired
    private lateinit var apiConsumer: ApiConsumer

    /**
     * search
     * Function to search in github.
     * @param search String to search.
     * @return String
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 22 - Initial release.
     */
    @GetMapping("/{search}")
    fun search(@PathVariable("search") search: String): ResponseEntity<GitHubSearch> {
        val searchResult = apiConsumer.getSearch(search)
        return ResponseEntity(searchResult, getStatus(searchResult))
    }

    private fun getStatus(obj: Any?) = if (obj == null) HttpStatus.NOT_FOUND else HttpStatus.OK
}