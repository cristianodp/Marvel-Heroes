package br.com.cristiano.marvelheroes.http.apimodel

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
@Entity(tableName = "characters")
data class Character (

        @field:SerializedName("thumbnail")
		val thumbnail: Thumbnail,

        @field:SerializedName("urls")
		val urls: List<UrlsItem>,

        @field:SerializedName("stories")
		val stories: Stories,

        @field:SerializedName("series")
		val series: Series,

        @field:SerializedName("comics")
		val comics: Comics,

        @field:SerializedName("name")
		val name: String,

        @field:SerializedName("description")
		val description: String,

        @field:SerializedName("modified")
		val modified: String,

		@PrimaryKey @field:SerializedName("id")
		val id: Int,

        @field:SerializedName("resourceURI")
		val resourceURI: String,

        @field:SerializedName("events")
		val events: Events
)