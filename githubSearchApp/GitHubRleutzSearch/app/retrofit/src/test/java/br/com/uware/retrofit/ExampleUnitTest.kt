package br.com.uware.retrofit

import br.com.uware.core.model.GitHubSearch
import br.com.uware.retrofit.service.ApiHelper
import br.com.uware.retrofit.service.RetrofitBuilder
import br.com.uware.retrofit.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.Assertions
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun `Get gitHub response`() {
        val search = "imc"
        val coroutine = CoroutineScope(Dispatchers.IO)
        coroutine.launch {
            val currentSearch: Response<GitHubSearch> =
                ApiHelper(RetrofitBuilder().apiService).getGitHubRepository(search)
            Assertions.assertTrue(currentSearch.isSuccessful)
        }
    }
}