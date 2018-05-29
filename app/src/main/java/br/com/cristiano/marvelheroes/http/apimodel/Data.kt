package br.com.cristiano.marvelheroes.http.apimodel

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Data(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("offset")
	val offset: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<Character>
)