package br.com.uware.core

import br.com.uware.core.model.GitHubLanguage


/**
 * Constants
 * Constants for app.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 02/05/2022 - Initial release.
 */
class Constants {
    companion object{
        val arrayLanguages = createArrayLanguages()

        private fun createArrayLanguages(): ArrayList<GitHubLanguage> {
            val array = arrayListOf("java", "kotlin", "php", "javascript", "html")
            val arrayGitHubLanguages = ArrayList<GitHubLanguage>()
            for(language in array){
                arrayGitHubLanguages.add(GitHubLanguage(language))
            }
            return arrayGitHubLanguages
        }
    }
}