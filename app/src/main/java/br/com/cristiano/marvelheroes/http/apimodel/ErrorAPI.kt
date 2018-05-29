package br.com.cristiano.marvelheroes.http.apimodel

import com.google.gson.Gson
import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Generated("com.robohorse.robopojogenerator")
data class ErrorAPI(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("message")
	private val message: String?,

	@field:SerializedName("status")
	val status: String? = null
){
	companion object {
		fun jsonErroToErrorAPI(json: String): ErrorAPI {
			val type = object : TypeToken<ErrorAPI>() {}.type
			return Gson().fromJson(json, type)
		}
	}

	fun getMessage() = message?:status?:"Erro API"
}



