package br.com.cristiano.marvelheroes.http.apimodel

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName


@Generated("com.robohorse.robopojogenerator")
data class Thumbnail(

	@field:SerializedName("path")
	val path: String,

	@field:SerializedName("extension")
	val extension: String
){


	fun getUrl(variant: String) = "$path/$variant.$extension"

	class Variants {
		companion object {
			val portrait_small = "portrait_small"
			val portrait_medium = "portrait_medium"
			val portrait_xlarge = "portrait_xlarge"
			val portrait_fantastic = "portrait_fantastic"
			val portrait_uncanny = "portrait_uncanny"
			val portrait_incredible = "portrait_incredible"

			val standard_small = "standard_small"
			val standard_medium = "standard_medium"
			val standard_larg = "standard_larg"
			val standard_xlarg = "standard_xlarg"
			val standard_fantastic = "standard_fantastic"
			val standard_amazing = "standard_amazing"



		}
	}
}


