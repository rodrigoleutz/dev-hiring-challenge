package com.githubsearchapp.web.api.model

import com.google.gson.JsonObject
import javax.persistence.*

@Entity
class GitHubSearch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var searchString: String = "",
    var count: Int = 0,
    var incompleteResult: Boolean = true,
    @ElementCollection
    @OneToMany(cascade = [CascadeType.ALL])
    var items: List<GitHubRepo> = ArrayList()
) {
    companion object {
        fun fromJson(searchString: String, jsonObject: JsonObject) = GitHubSearch(
            searchString = searchString,
            count = jsonObject.get("total_count").asInt,
            incompleteResult = jsonObject.get("incomplete_results").asBoolean,
            items = itemsFromJsonOArray(jsonObject)
        )

        private fun itemsFromJsonOArray(jsonObject: JsonObject): ArrayList<GitHubRepo> {
            val arrayItems = ArrayList<GitHubRepo>()
            for (item in jsonObject.get("items").asJsonArray) {
                arrayItems.add(GitHubRepo.fromJsonElement(item))
            }
            return arrayItems
        }
    }
}
