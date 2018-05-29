package br.com.cristiano.marvelheroes.http.apimodel

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Events(

	@field:SerializedName("collectionURI")
	val collectionURI: String,

	@field:SerializedName("available")
	val available: Int,

	@field:SerializedName("returned")
	val returned: Int,

	@field:SerializedName("items")
	val items: List<ItemsItem>
)