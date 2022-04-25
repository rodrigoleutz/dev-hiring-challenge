package com.githubsearchapp.web.api.repository

import com.githubsearchapp.web.api.model.GitHubSearch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GitHubSearchRepository: JpaRepository<GitHubSearch, Int> {
    fun getBySearchString(searchString: String): GitHubSearch?
}