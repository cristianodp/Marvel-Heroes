package br.com.cristiano.marvelheroes.http.apimodel

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class ItemsItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("resourceURI")
	val resourceURI: String
)