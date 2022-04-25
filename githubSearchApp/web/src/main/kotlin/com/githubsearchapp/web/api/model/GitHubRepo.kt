package com.githubsearchapp.web.api.model

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.vdurmont.emoji.EmojiParser
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class GitHubRepo(
    @Id
    var id: String = "",
    var name: String = "",
    @Lob
    var description: String? = null,
    var language: String = "",
    var stars: Int = 0,
    var owner: String = "",
    var url: String = "",
) {
    companion object {
        fun fromJsonElement(jsonElement: JsonElement): GitHubRepo =
            GitHubRepo(
                id = jsonElement.asJsonObject.get("id").asString,
                name = jsonElement.getStringFromJsonElement("name"),
                owner = jsonElement.asJsonObject.get("owner").asJsonObject.get("login").asString,
                description = jsonElement.getStringFromJsonElement("description"),
                language = jsonElement.getStringFromJsonElement("language"),
                url = jsonElement.getStringFromJsonElement("html_url"),
                stars = jsonElement.asJsonObject.get("stargazers_count").asInt
            )

        private fun JsonElement.getStringFromJsonElement(name: String) =
            if (this.asJsonObject.get(name) is JsonNull) ""
            else EmojiParser.removeAllEmojis(this.asJsonObject.get(name).asString)


    }
}