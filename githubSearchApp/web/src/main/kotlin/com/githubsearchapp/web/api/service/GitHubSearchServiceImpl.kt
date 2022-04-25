package com.githubsearchapp.web.api.service

import com.githubsearchapp.web.api.model.GitHubSearch
import com.githubsearchapp.web.api.repository.GitHubSearchRepository
import org.springframework.stereotype.Component

@Component
class GitHubSearchServiceImpl(private val gitHubSearchRepository: GitHubSearchRepository) : GitHubSearchService {

    override fun create(gitHubSearch: GitHubSearch) { gitHubSearchRepository.save(gitHubSearch) }

    override fun getById(id: Int): GitHubSearch = gitHubSearchRepository.getById(id)

    override fun getBySearchString(searchString: String) = gitHubSearchRepository.getBySearchString(searchString)
}