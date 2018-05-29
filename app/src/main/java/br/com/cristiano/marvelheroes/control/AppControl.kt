package br.com.cristiano.marvelheroes.control

import android.content.Context
import android.util.Log
import br.com.cristiano.marvelheroes.http.ApiModule
import br.com.cristiano.marvelheroes.http.IMarvelAPI
import br.com.cristiano.marvelheroes.http.apimodel.ErrorAPI
import br.com.cristiano.marvelheroes.http.apimodel.ResponseAPI
import br.com.cristiano.marvelheroes.utils.IListenerResult
import br.com.cristiano.marvelheroes.utils.hashMD5
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import java.io.IOException

import java.util.*


class AppControl(var context: Context) {

    private var apiModule: ApiModule = ApiModule()
    private var marvelAPI: IMarvelAPI




    init {

        marvelAPI = apiModule.provideApiService()

    }

    fun getCharacters(nameStartWith: String?,offset:Int, listenerResult: IListenerResult<ResponseAPI> ) {

        var query:MutableMap<String,String> = mutableMapOf("limit" to "10","offset" to offset.toString())
        if (nameStartWith!=null && nameStartWith.isNotEmpty()){
            query["nameStartsWith"] = nameStartWith
        }

        val call = marvelAPI.getCharacters(query)
        call.enqueue(createCallbackResponse(listenerResult))
    }



    fun getCharacter(characterId: Int,listenerResult: IListenerResult<ResponseAPI> ) {

        val call = marvelAPI.getCharacter(characterId)
        call.enqueue(createCallbackResponse(listenerResult))
    }


    fun ExtractError(response: Response<ResponseAPI>): ErrorAPI? {

        if (response.code() != 200 || response.body() == null) {
            if (response.errorBody() != null) {
                try {
                    val errJson = response.errorBody()!!.string()
                    Log.v("tag", errJson)
                    return ErrorAPI.jsonErroToErrorAPI(errJson)
                } catch (e: IOException) {
                    return ErrorAPI(response.code().toString(),response.toString())
                }

            } else {
                return ErrorAPI(response.code().toString(),response.toString())
            }
        }
        return null
    }

    fun createCallbackResponse(listenerResult: IListenerResult<ResponseAPI>) = object: Callback<ResponseAPI> {

        override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
            val erro = ExtractError(response)
            if (erro != null) {
                listenerResult.onError(call.hashCode(),erro)
            } else {
                val ret = response.body()!!
                listenerResult.onResult(call.hashCode(),ret)
            }
        }

        override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
            listenerResult.onError(call.hashCode(), ErrorAPI(t.message?:"",t.message?:"Erro generico"))
        }
    }




}



