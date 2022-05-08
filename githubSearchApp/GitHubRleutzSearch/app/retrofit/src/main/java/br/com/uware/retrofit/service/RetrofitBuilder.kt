package br.com.uware.retrofit.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * RetrofitBuilder
 *
 * @author Rodrigo Leutz
 * @version 1.0.0 - 02/05/2022 - Initial release.
 */
class RetrofitBuilder {

    private val httpClient = OkHttpClient.Builder()
    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        httpClient.addInterceptor(logging)
    }

    private fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}