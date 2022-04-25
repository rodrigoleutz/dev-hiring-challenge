package com.githubsearchapp.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GithubSearchAppApplication

fun main(args: Array<String>) {
	runApplication<GithubSearchAppApplication>(*args)
}
