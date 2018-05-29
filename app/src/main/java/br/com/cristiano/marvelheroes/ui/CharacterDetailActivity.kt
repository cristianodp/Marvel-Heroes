package br.com.cristiano.marvelheroes.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.cristiano.marvelheroes.R
import br.com.cristiano.marvelheroes.http.apimodel.Character
import br.com.cristiano.marvelheroes.http.apimodel.Thumbnail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*
import java.lang.Exception

class CharacterDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val type = object : TypeToken<Character>() {}.type
        val character = Gson().fromJson<Character>(intent.extras.getString("characterJson"), type)

        loadFields(character)


    }

    fun loadFields(character: Character){
        progressBar.visibility = View.VISIBLE
        Picasso.get()
                .load(character.thumbnail.getUrl(Thumbnail.Variants.portrait_uncanny))
                .into(thumbnail_image_view,object :Callback{
                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.INVISIBLE
                    }

                    override fun onSuccess() {
                        progressBar.visibility = View.INVISIBLE
                    }

                })


        name_text_view.text = character.name
        description_text_view.text = character.description

    }

}
