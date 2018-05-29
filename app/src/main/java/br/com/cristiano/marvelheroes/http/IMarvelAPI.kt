package br.com.cristiano.marvelheroes.http


import br.com.cristiano.marvelheroes.http.apimodel.ResponseAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query
import retrofit2.http.QueryMap

interface IMarvelAPI {

    @GET("characters")
    fun getCharacters(@QueryMap map: Map<String,String>): Call<ResponseAPI>

    @GET("characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int): Call<ResponseAPI>

}
