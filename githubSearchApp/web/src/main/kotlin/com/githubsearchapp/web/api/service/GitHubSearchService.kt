package com.githubsearchapp.web.api.service

import com.githubsearchapp.web.api.model.GitHubSearch

interface GitHubSearchService {
    fun create(gitHubSearch: GitHubSearch)
    fun getById(id: Int): GitHubSearch?
    fun getBySearchString(searchString: String): GitHubSearch?
}