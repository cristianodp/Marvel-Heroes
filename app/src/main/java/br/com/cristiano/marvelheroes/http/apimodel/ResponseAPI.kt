package br.com.cristiano.marvelheroes.http.apimodel

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class ResponseAPI(

		@field:SerializedName("copyright")
	val copyright: String,

		@field:SerializedName("code")
	val code: Int,

		@field:SerializedName("data")
	val data: Data,

		@field:SerializedName("attributionHTML")
	val attributionHTML: String,

		@field:SerializedName("attributionText")
	val attributionText: String,

		@field:SerializedName("etag")
	val etag: String,

		@field:SerializedName("status")
	val status: String
)