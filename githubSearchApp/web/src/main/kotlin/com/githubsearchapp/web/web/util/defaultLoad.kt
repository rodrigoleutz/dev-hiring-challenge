package com.githubsearchapp.web.web.util

import org.springframework.ui.Model

fun Model.defaultLoad(page: String): String {
    this.addAttribute("content", page)
    return "index"
}