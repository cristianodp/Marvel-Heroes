package br.com.cristiano.marvelheroes.http

import br.com.cristiano.marvelheroes.utils.hashMD5
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.*


class ApiModule {

    var BASE_URL: String = "https://gateway.marvel.com:443/v1/public/"
    private val apiPublicKey:String = "b5f36a77d872d77ce5c172896b6a7ec7"
    private val apiPrivateKey:String = "6fd270fc85e1cf77d93e5cea2d5d9197e887511c"
    private fun getHash(ts:String) = "$ts$apiPrivateKey$apiPublicKey".hashMD5()

    fun provideClient(): OkHttpClient {
        val interceptorLog = HttpLoggingInterceptor()
        interceptorLog.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()

        builder.addInterceptor( object :Interceptor{
            override fun intercept(chain: Interceptor.Chain?): Response {
                    val ts = Date().time.toString()
                    var request = chain!!.request()
                    val url = request.url().newBuilder()
                            .addQueryParameter("ts",ts)
                            .addQueryParameter("apikey",apiPublicKey)
                            .addQueryParameter("hash",getHash(ts))
                            .build();
                    request = request.newBuilder().url(url).build()
                    return chain.proceed(request)



            }

        })

        return builder.addInterceptor(interceptorLog).build()
    }


    fun provideRetrofit(baseURL: String, client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }



    fun provideApiService(): IMarvelAPI {
        return provideRetrofit(BASE_URL, provideClient()).create<IMarvelAPI>(IMarvelAPI::class.java)
    }

}
