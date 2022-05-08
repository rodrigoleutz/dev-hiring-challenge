package br.com.uware.githubrleutzsearch

import android.util.Log
import androidx.compose.material.DrawerState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import br.com.uware.core.Constants
import br.com.uware.core.model.GitHubLanguage
import br.com.uware.core.model.GitHubSearch
import br.com.uware.retrofit.service.ApiHelper
import br.com.uware.retrofit.service.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * MainViewModel
 *
 *
 * @author Rodrigo Leutz
 */
class MainViewModel : ViewModel() {

    // NavHostController
    var navHostController: NavHostController? = null

    // Navigation Drawer State
    var drawerState: DrawerState? = null

    // CoroutineScope
    var coroutineScope: CoroutineScope? = null

    private var _repositories: MutableLiveData<GitHubSearch?> = MutableLiveData(null)
    val repositories: LiveData<GitHubSearch?> = _repositories

    // CheckBox Languages
    private var _checkBoxLanguages: MutableLiveData<ArrayList<GitHubLanguage>> =
        MutableLiveData(Constants.arrayLanguages)
    val checkBoxLanguages: LiveData<ArrayList<GitHubLanguage>> = _checkBoxLanguages

    /**
     * checkLanguage
     * Check the language in checkbox
     * @param string String of language to check.
     * @return List of strings
     * @author Rodrigo Leutz
     * @version 1.0.0 - 08/05/2022 - Initial release.
     */
    fun checkLanguage(string: String) =
        _checkBoxLanguages.value?.filter { it.language == string }?.map { it.checked = !it.checked }


    fun getGitHubRepository(
        searchString: String,
        languages: List<GitHubLanguage>? = checkBoxLanguages.value,
        sort: String = "stars",
        order: String = "desc"
    ) {
        val coroutineScopeFunction = CoroutineScope(Dispatchers.IO)
        coroutineScopeFunction.launch {
            try {
                val arrayLanguages = ArrayList<String>()
                for(language in languages!!){
                    if(language.checked) arrayLanguages.add(language.language)
                }
                val currentRepositories =
                    ApiHelper(RetrofitBuilder().apiService)
                        .getGitHubRepository(
                            searchString,
                            arrayLanguages,
                            sort,
                            order
                        )
                _repositories.postValue(currentRepositories.body())
            } catch (e: Exception) {
                Log.e(TAG, "Retrofit error=\n $e")
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}