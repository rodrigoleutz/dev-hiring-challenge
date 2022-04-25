package com.githubsearchapp.web.web.controller

import com.githubsearchapp.web.api.consumer.ApiConsumer
import com.githubsearchapp.web.api.controller.ApiController
import com.githubsearchapp.web.api.model.GitHubSearch
import com.githubsearchapp.web.api.service.GitHubSearchService
import com.githubsearchapp.web.web.util.defaultLoad
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class WebController {

    @Autowired
    private lateinit var apiConsumer: ApiConsumer

    @GetMapping
    fun index(model: Model): String {
        return model.defaultLoad("main")
    }

    @PostMapping("/search")
    fun search(@ModelAttribute("searchField") search: String, model: Model): String {
        val gitHubSearch = apiConsumer.getSearch(search)
        model.addAttribute("gitHubSearch", gitHubSearch)
        return model.defaultLoad("search")
    }

    @GetMapping("/best")
    fun best(model: Model): String {
        return model.defaultLoad("best")
    }

}